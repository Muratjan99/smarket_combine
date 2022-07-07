package cn.muratjan.smarket.service;

import cn.muratjan.smarket.pojo.Tuser;
import cn.muratjan.smarket.vo.LoginVO;
import cn.muratjan.smarket.vo.RegisterVO;
import cn.muratjan.smarket.vo.UpdateUserVO;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author MRT
 * @date 2022/6/28 10:47
 */
public interface ITuserService extends IService<Tuser> {
    /**
     * 查询所有用户
     * @return 返回
     */
    List<Tuser> getAll();

    /**
     *  注册
     *  @param registerVO 用户信息
     */
    void register(RegisterVO registerVO);
    /**
     * 注册时检验用户名是否存在
     * @param username 用户名
     * @return  返回
     */
    Long checkUserName(String username);


    /**
     * 登录
     *
     * @param loginVO 登录信息
     * @param request
     */
    void login(LoginVO loginVO, HttpServletRequest request);

    /**
     * 退出登录
     */
    void logout();

    /**
     * 获取当前登录用户基本信息
     * @return 返回
     */
    Tuser getCurrentUserInfo();

    /**
     * 获取当前登录用户的token信息
     * @param updateUserVO 更新用户信息
     */
    void updateCurrentUserInfo(UpdateUserVO updateUserVO);

    /**
     *  更新用户token
     * @param oldPassword   旧密码
     * @param newPassword   新密码
     */
    void changePassword(String oldPassword, String newPassword);

    /*************************以下为管理员操作************************************/

    /**
     *  通过id查询用户
     *
     * @param id 用户id
     * @return 返回
     */
    Tuser selectById(Long id);

    /**
     *  通过用户名模糊查询用户
     * @param userName 用户名
     * @return 返回
     */
    List<Tuser> selectByUserNameLike(String userName);

    /**
     * 通过ID踢出用户
     * @param id 用户id
     */
    void kickout(Long id);

    /**
     * 获取在线用户 (管理员)
     * @return 返回
     */
    List<Tuser> getOnlineUsers(String username);


}
