package cn.muratjan.smarket.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import cn.muratjan.smarket.common.excaption.UserException;
import cn.muratjan.smarket.common.utils.IPUtils;
import cn.muratjan.smarket.mapper.LoginMapper;
import cn.muratjan.smarket.mapper.TuserMapper;
import cn.muratjan.smarket.pojo.Login;
import cn.muratjan.smarket.pojo.LoginHistory;
import cn.muratjan.smarket.pojo.Tuser;
import cn.muratjan.smarket.service.ITuserService;
import cn.muratjan.smarket.vo.LoginVO;
import cn.muratjan.smarket.vo.RegisterVO;
import cn.muratjan.smarket.vo.UpdateUserVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MRT
 * @date 2022/6/28 10:54
 */
@Service
public class TuserServiceImpl  extends ServiceImpl<TuserMapper, Tuser> implements ITuserService {

    @Resource
    private TuserMapper tuserMapper;
    @Resource
    private LoginMapper loginMapper;
    @Resource
    private LoginHistoryServiceImpl loginHistoryService;

    /**
     * 查询所有用户
     *
     * @return 返回
     */
    @Override
    public List<Tuser> getAll() {
        return tuserMapper.selectList(null);
    }

    /**
     * 注册时检验用户名是否存在
     *
     * @param username 用户名
     * @return 返回
     */
    @Override
    public Long checkUserName(String username) {
        QueryWrapper<Tuser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return tuserMapper.selectCount(queryWrapper);
    }

    /**
     * 注册
     *
     * @param registerVO 用户信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(RegisterVO registerVO) {
        // 查询用户名是否存在
        if(tuserMapper.selectCount(new QueryWrapper<Tuser>().eq("username", registerVO.getUsername())) > 0) {
            throw new UserException("用户名已存在");
        }
        Tuser user = new Tuser();
        // TODO : 查询用户名是否合法 --> 是否是学号
        user.setUsername(registerVO.getUsername());
        user.setSex(registerVO.getSex());
        user.setNickname("用户"+IdUtil.simpleUUID().substring(0,8));
        if (registerVO.getPhone() != null) {
            user.setPhone(registerVO.getPhone());
        }
        if (registerVO.getEmail() != null) {
            user.setEmail(registerVO.getEmail());
        }
        int insert = tuserMapper.insert(user);
        if (insert != 1) {
            throw new UserException("数据库插入异常");
        }
        // TODO : 密码加密
        Login login = new Login();
        login.setLoginUser(user.getUserId());
        login.setLoginSalt(IdUtil.simpleUUID());
        login.setLoginToken(encodePassword(registerVO.getPassword(), login.getLoginSalt()));
        insert = loginMapper.insert(login);
        if (insert != 1) {
            throw new UserException("数据库插入异常");
        }
    }

    /**
     * 登录
     * 登录方式：
     * 1. 用户名+密码
     * 2. 手机号+密码
     * 3. 邮箱+密码
     * 4.微信扫码
     * 5.QQ扫码
     *
     * @param loginVO 登录信息
     * @param request 请求
     */
    @Override
    @Transactional(noRollbackFor = UserException.class)
    public void login(LoginVO loginVO, HttpServletRequest request) {
        // TODO：暂时只支持用户名+密码登录
        // 通过用户名查询用户信息
        Tuser tuser = tuserMapper.selectOne(new QueryWrapper<Tuser>().eq("username", loginVO.getUsername()));
        if (tuser == null ) {
            throw new UserException("用户不存在");
        }
        Login login = loginMapper.selectOne(new QueryWrapper<Login>().eq("login_user", tuser.getUserId()));
        if (login == null) {
            throw new UserException("登录信息不存在");
        }
        // TODO :数据脱敏
        String ipAddress = IPUtils.getIpAddress(request);
        LoginHistory loginHistory = new LoginHistory();
        loginHistory.setLoginIp(ipAddress);
        loginHistory.setLoginUser(tuser.getUserId());
        boolean save;
        String password = encodePassword(loginVO.getPassword(), login.getLoginSalt());
        if (!password.equals(login.getLoginToken())) {
            loginHistory.setLoginResult(0);
            save = loginHistoryService.save(loginHistory);
            if (!save) {
                throw new UserException("登录失败");
            }
            throw new UserException("密码错误");
        }
        loginHistory.setLoginResult(1);
        save = loginHistoryService.save(loginHistory);
        if (!save) {
            throw new UserException("登录历史信息保存失败");
        }
        StpUtil.login(tuser.getUserId());
    }

    /**
     * 退出登录
     */
    @Override
    public void logout() {
        StpUtil.logout();
    }

    /**
     * 获取当前登录用户基本信息
     *
     * @return 返回
     */
    @Override
    public Tuser getCurrentUserInfo() {
        Long loginId = StpUtil.getLoginIdAsLong();
        return tuserMapper.selectById(loginId);
    }

    /**
     * 获取当前登录用户的token信息
     *
     * @param updateUserVO 更新用户信息
     */
    @Override
    public void updateCurrentUserInfo(UpdateUserVO updateUserVO) {
        Long loginId = StpUtil.getLoginIdAsLong();
        Tuser tuser = tuserMapper.selectById(loginId);
        if (tuser == null) {
            throw new UserException("用户不存在");
        }
        UpdateWrapper<Tuser> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", loginId);
        updateWrapper.set(StringUtils.isNotEmpty(updateUserVO.getNickname()), "nickname", updateUserVO.getNickname());
        updateWrapper.set(updateUserVO.getSex() != null, "sex",updateUserVO.getSex());
        updateWrapper.set(updateUserVO.getBirthdate() != null, "birthdate",updateUserVO.getBirthdate());
        updateWrapper.set(StringUtils.isNotEmpty(updateUserVO.getAvatar()), "avatar", updateUserVO.getAvatar());
        int update = tuserMapper.update(null, updateWrapper);
        if (update != 1) {
            throw new UserException("更新失败");
        }
    }

    /**
     * 更新用户token
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Long loginId =StpUtil.getLoginIdAsLong();
        Tuser tuser = tuserMapper.selectById(loginId);
        if (tuser == null) {
            throw new UserException("用户不存在");
        }
        Login login = loginMapper.selectOne(new QueryWrapper<Login>().eq("login_user", tuser.getUserId()));
        if (login == null) {
            throw new UserException("登录信息不存在");
        }
        String oldPasswordEncode = encodePassword(oldPassword, login.getLoginSalt());
        if (!oldPasswordEncode.equals(login.getLoginToken())) {
            throw new UserException("旧密码错误");
        }
        if (!StringUtils.isEmpty(newPassword)) {
            String newPasswordEncode = encodePassword(newPassword, login.getLoginSalt());
            login.setLoginToken(newPasswordEncode);
            UpdateWrapper<Login> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("login_user", login.getLoginUser());
            updateWrapper.set("login_token", newPasswordEncode);
            int update = loginMapper.update(login, updateWrapper);
            if (update != 1) {
                throw new UserException("更新失败");
            }
        }
    }


    /**
     * 通过id查询用户
     *
     * @param id 用户id
     * @return 返回
     */
    @Override
    public Tuser selectById(Long id) {
        return tuserMapper.selectById(id);
    }

    /**
     * 通过用户名模糊查询用户
     *
     * @param userName 用户名
     * @return 返回
     */
    @Override
    public List<Tuser> selectByUserNameLike(String userName) {
        return tuserMapper.selectList(new QueryWrapper<Tuser>().like("username", userName));
    }

    /**
     * 通过ID踢出用户
     *
     * @param id 用户id
     */
    @Override
    public void kickout(Long id) {
        StpUtil.kickout(id);
    }

    /**
     * 获取在线用户
     *
     * @return 返回
     */
    @Override
    public List<Tuser> getOnlineUsers(String username) {
        List<String> sessionsList = StpUtil.searchSessionId(null,-1,-1);
        List<Long> idList = new ArrayList<>();
        for (String str : sessionsList) {
            idList.add(Long.parseLong(str.substring(25)));
        }
        QueryWrapper<Tuser> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("user_id", idList);
        if (username != null) {
            queryWrapper.like("username", username);
        }
        return tuserMapper.selectList(queryWrapper);
    }

    /**
     * 密码加密
     * @param password 密码
     * @param salt 盐值
     * @return 加密后密码
     */
    private String encodePassword(String password, String salt){
        for(int i = 0 ; i < 3 ;i++){
            password = SaSecureUtil.md5(salt+password+salt);
        }
        return password;
    }
}
