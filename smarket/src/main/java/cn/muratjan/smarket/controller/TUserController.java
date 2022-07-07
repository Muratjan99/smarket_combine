package cn.muratjan.smarket.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.muratjan.smarket.common.AjaxResult;
import cn.muratjan.smarket.common.constants.Constants;
import cn.muratjan.smarket.common.excaption.UserException;
import cn.muratjan.smarket.pojo.LoginHistory;
import cn.muratjan.smarket.pojo.Order;
import cn.muratjan.smarket.pojo.Product;
import cn.muratjan.smarket.pojo.Tuser;
import cn.muratjan.smarket.service.ITuserService;
import cn.muratjan.smarket.service.LoginHistoryService;
import cn.muratjan.smarket.service.OrderService;
import cn.muratjan.smarket.service.ProductService;
import cn.muratjan.smarket.vo.LoginVO;
import cn.muratjan.smarket.vo.RegisterVO;
import cn.muratjan.smarket.vo.UpdateUserVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author MRT
 * @date 2022/6/28 15:27
 */
@RestController
@RequestMapping("/user")
public class TUserController {

    @Resource
    private ITuserService tuserServiceImpl;
    @Resource
    private LoginHistoryService loginHistoryServiceImpl;
    @Resource
    private OrderService orderServiceImpl;
    @Resource
    private ProductService productServiceImpl;


    /**
     * 检测用户名是否存在
     * @param username
     * @return
     */
    @GetMapping("/checkUserName")
    public AjaxResult checkUserName(@RequestParam String username) {
        Long count = tuserServiceImpl.checkUserName(username);
        if (count > 0) {
            return AjaxResult.error(Constants.USERNAME_EXIST);
        }
        return AjaxResult.success();
    }

    /**
     * 用户注册
     * @param registerVO 用户信息
     * @return 返回
     */
    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterVO registerVO) {
        tuserServiceImpl.register(registerVO);
        return AjaxResult.success("注册成功");
    }

    /**
     * 用户登录
     * @param loginVO 登录信息
     * @return 返回
     */
    @PostMapping("/login")
    public AjaxResult login(@Validated @RequestBody LoginVO loginVO, HttpServletRequest request) {
        tuserServiceImpl.login(loginVO,request);
        return AjaxResult.success("登录成功")
                .put(Constants.TOKEN_NAME, StpUtil.getTokenName())
                .put(Constants.TOKEN_VALUE, StpUtil.getTokenValue());
    }

    /**
     * 获取登录历史
     * @return  返回
     */
    @GetMapping("/loginHistory")
    @SaCheckLogin
    public AjaxResult getLoginHistory() {
        QueryWrapper<LoginHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", StpUtil.getLoginIdAsLong());
        List<LoginHistory> list = loginHistoryServiceImpl.list();
        return AjaxResult.success(list);
    }

    /**
     * 用户登出
     * @return 返回
     */
    @GetMapping("/logout")
    @SaCheckLogin
    public AjaxResult logout() {
        tuserServiceImpl.logout();
        return AjaxResult.success("登出成功");
    }

    /**
     * 获取当前登录用户基本信息
     * @return 返回
     */
    @GetMapping("/getCurrentUserInfo")
    @SaCheckLogin
    public AjaxResult getCurrentUserInfo() {
        return AjaxResult.success("获取成功").put("user", tuserServiceImpl.getCurrentUserInfo());
    }

    /**
     * 获取当前登录用户的token信息
     * @param updateUserVO 更新用户信息
     * @return 返回
     */
    @PostMapping("/updateCurrentUserInfo")
    @SaCheckLogin
    public AjaxResult updateCurrentUserInfo(@RequestBody UpdateUserVO updateUserVO) {
        tuserServiceImpl.updateCurrentUserInfo(updateUserVO);
        return AjaxResult.success("更新成功");
    }


    /**
     *  更新用户token
     * @param map 参数
     * @return 返回
     */
    @PostMapping("/changePassword")
    @SaCheckLogin
    public AjaxResult changePassword(@RequestBody Map<String, String> map) {
        if (map.size() == 1) {
            tuserServiceImpl.changePassword(map.get("oldPassword"),null);
        }else {
            tuserServiceImpl.changePassword(map.get("oldPassword"),map.get("newPassword"));
        }
        return AjaxResult.success("更新成功");
    }

    /**
     * 通过用户id获取用户信息
     * @param id 用户id
     * @return 返回
     */
    @GetMapping("/getUserById")
    @SaCheckLogin
    public AjaxResult selectById(Long id) {
        return AjaxResult.success("获取成功").put("user", tuserServiceImpl.selectById(id));
    }


    /**
     * 通过用户名 模糊获取用户信息
     * @param name 用户名
     * @return 返回
     */
    @GetMapping("/getUserByName")
    @SaCheckLogin
    public AjaxResult selectByName(String name) {
        return AjaxResult.success("获取成功").put("user", tuserServiceImpl.selectByUserNameLike(name));
    }

    /**
     * 通过用户id踢出用户
     * @param id 用户id
     * @return 返回
     */
    @GetMapping("/getUserByPhone")
    @SaCheckLogin
    public AjaxResult kickout(Long id){
        tuserServiceImpl.kickout(id);
        return AjaxResult.success("获取成功");
    }

    @GetMapping("/getUserMainPage")
    public AjaxResult getUserMainPage(@RequestParam Long userId) {
        // 查询用户
        QueryWrapper<Tuser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        Tuser user = tuserServiceImpl.getOne(queryWrapper);
        if (user == null) {
            throw new UserException("用户不存在");
        }
        // 查询卖出订单
        QueryWrapper<Order> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("seller_id", userId);
        queryWrapper1.eq("order_status", 2);
        List<Order> orders = orderServiceImpl.list(queryWrapper1);
        //查询他卖的
        QueryWrapper<Product> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("vendor_id", userId);
        queryWrapper.eq("status",0);
        List<Product> products = productServiceImpl.list(queryWrapper2);
        return AjaxResult.success("获取成功").put("user", user).put("orders", orders).put("products", products);
    }

    /**
     * 通过用户名查询在线用户
     * @param name 用户名
     * @return 返回
     */
    public AjaxResult getOnlineUsers(String name){
        return AjaxResult.success("获取成功").put("users", tuserServiceImpl.getOnlineUsers(name));
    }

}
