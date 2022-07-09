package cn.muratjan.admin.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.muratjan.admin.common.AjaxResult;
import cn.muratjan.admin.common.excaption.AdminServiceException;
import cn.muratjan.admin.pojo.Login;
import cn.muratjan.admin.pojo.Order;
import cn.muratjan.admin.pojo.Tuser;
import cn.muratjan.admin.service.*;
import cn.muratjan.admin.vo.GeneralInfo;
import cn.muratjan.admin.vo.LoginVO;
import cn.muratjan.admin.vo.UserInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.PublicKey;
import java.util.List;

/**
 * @author MRT
 * @date 2022/7/5 17:13
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private TuserService tuserServiceImpl;
    @Resource
    private RoleService roleServiceImpl;
    @Resource
    private RoleUserService roleUserServiceImpl;
    @Resource
    private LoginService loginServiceImpl;
    @Resource
    private OrderService orderServiceImpl;
    @Resource
    private ProductService productServiceImpl;

    @GetMapping("/hello")
    @SaCheckRole("super-admin")
    public String hello() {
        return "hello";
    }

    /**
     * 登录
     *
     * @param loginVO 登录信息
     * @return 登录结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody @Validated LoginVO loginVO) {
        QueryWrapper<Tuser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", loginVO.getUsername());
        Tuser tuser = tuserServiceImpl.getOne(queryWrapper);
        if (tuser == null) {
           throw  new AdminServiceException("用户不存在");
        }
        Login login = loginServiceImpl.getById(tuser.getUserId());
        if (login == null) {
            throw  new AdminServiceException("用户不存在");
        }
        String password = encodePassword(loginVO.getPassword(), login.getLoginSalt());
        if (!login.getLoginToken().equals(password)) {
            throw  new AdminServiceException("密码错误");
        }
        StpUtil.login(tuser.getUserId());
        boolean b = StpUtil.hasRoleOr("super-admin","admin");
        if (!b) {
            StpUtil.logout(tuser.getUserId());
            throw  new AdminServiceException("权限不足");
        }
        return AjaxResult.success(StpUtil.getTokenInfo());
    }


    /**
     * 登出
     *
     * @return
     */
    @GetMapping("/logout")
    public AjaxResult logout() {
        StpUtil.logout();
        return AjaxResult.success();
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/info")
    @SaCheckLogin
    public AjaxResult getUserInfo() {
        Tuser tuser = tuserServiceImpl.getById(StpUtil.getLoginIdAsLong());
        tuser.setRoles(roleUserServiceImpl.getUserRolesNameList(StpUtil.getLoginIdAsLong()));
        return AjaxResult.success(tuser);
    }

    /**
     *  大概数据获取
     * @return
     */
    @GetMapping("/generalInfo")
    @SaCheckLogin
    @SaCheckRole("super-admin")
    public AjaxResult generalInfo(){
        long userCount  = tuserServiceImpl.count();
        long orderCount = orderServiceImpl.count();
        long productCount = productServiceImpl.count();
        List<Order> orderList = orderServiceImpl.listDeep();
        double totalPrice = 0;
        for (Order order : orderList) {
            totalPrice += order.getTotalMoney();
        }
        return AjaxResult.success(new GeneralInfo(userCount,productCount,orderCount,totalPrice));
    }

    /**
     * 获取用户列表
     * @return
     */
    @GetMapping("/getUserTable")
    @SaCheckLogin
    @SaCheckRole("super-admin")
    public AjaxResult getUserList(@RequestParam int page,@RequestParam int limit) {
        return AjaxResult.success(tuserServiceImpl.page(new Page<>(page,limit)));
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
