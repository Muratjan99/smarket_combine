package cn.muratjan.admin.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.muratjan.admin.common.AjaxResult;
import cn.muratjan.admin.service.*;
import cn.muratjan.admin.vo.TablesVO;
import cn.muratjan.admin.dto.UserTableDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MRT
 * @date 2022/7/9 0:00
 */
@RestController
@RequestMapping("table")
public class TableController {

    @Resource
    private TableService tableServiceImpl;
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

    /**
     * 获取用户表格数据
     * @return 用户表格数据
     */
    @GetMapping("/getTables")
    @SaCheckRole("super-admin")
    public AjaxResult getUserTable() {
        return AjaxResult.success("获取成功",tableServiceImpl.getTables());
    }
}
