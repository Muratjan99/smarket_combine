package cn.muratjan.admin.controller;

import cn.muratjan.admin.common.AjaxResult;
import cn.muratjan.admin.common.excaption.RolePermissionException;
import cn.muratjan.admin.pojo.Permission;
import cn.muratjan.admin.pojo.Role;
import cn.muratjan.admin.pojo.RoleUser;
import cn.muratjan.admin.service.PermissionService;
import cn.muratjan.admin.service.RoleService;
import cn.muratjan.admin.service.RoleUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MRT
 * @date 2022/7/7 14:35
 */
@RestController
@RequestMapping("/rp")
public class RolePermissionController {
    @Resource
    private RoleService roleServiceImpl;
    @Resource
    private PermissionService permissionServiceImpl;
    @Resource
    private RoleUserService roleUserServiceImpl;


    /**
     * 添加角色
     * @param role
     * @return
     */
    @PostMapping("/addRole")
    public AjaxResult add(@RequestBody @Validated Role role) {
        boolean saveOrUpdate = roleServiceImpl.saveOrUpdate(role);
        if(!saveOrUpdate){
            throw new RolePermissionException("添加失败");
        }
        return AjaxResult.success();
    }

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    @GetMapping("/deleteRole")
    public AjaxResult delete(@RequestParam Long roleId) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        boolean remove = roleServiceImpl.remove(queryWrapper);
        if(!remove){
            throw new RolePermissionException("删除失败");
        }
        return AjaxResult.success();
    }

    /**
     * 获取角色列表
     * @return
     */
    @GetMapping("/getAllRoles")
    public AjaxResult getAllRoles(@RequestParam Integer page,
                                  @RequestParam Integer size) {
        return AjaxResult.success(roleServiceImpl.page(new Page<>(page,size)));
    }

    /**
     *  添加权限
     * @param permission 权限
     * @return AjaxResult
     */
    @PostMapping("/addPermission")
    public AjaxResult addPermission(@RequestBody @Validated Permission permission) {
        boolean saveOrUpdate = permissionServiceImpl.saveOrUpdate(permission);
        if(!saveOrUpdate){
            throw new RolePermissionException("添加失败");
        }
        return AjaxResult.success();
    }

    /**
     * 删除权限
     * @param permissionId 权限id
     * @return AjaxResult
     */
    @GetMapping("/deletePermission")
    public AjaxResult deletePermission(@RequestParam Long permissionId) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("permission_id", permissionId);
        boolean remove = permissionServiceImpl.remove(queryWrapper);
        if(!remove){
            throw new RolePermissionException("删除失败");
        }
        return AjaxResult.success();
    }

    /**
     * 获取权限列表
     * @return AjaxResult
     */
    @GetMapping("/getAllPermissions")
    public AjaxResult getAllPermissions(@RequestParam Integer page,
                                        @RequestParam Integer size) {
        return AjaxResult.success(permissionServiceImpl.page(new Page<>(page,size)));
    }

    /**
     * 添加角色用户
     * @param roleId 角色id
     * @param userId 用户id
     * @return AjaxResult
     */
    @GetMapping("/addRoleUser")
    public AjaxResult addRoleUser(@RequestParam Long roleId,
                                  @RequestParam Long userId) {
        RoleUser roleUser = new RoleUser();
        roleUser.setRoleId(roleId);
        roleUser.setUserId(userId);
        boolean saveOrUpdate = roleUserServiceImpl.saveOrUpdate(roleUser);
        if(!saveOrUpdate){
            throw new RolePermissionException("添加失败");
        }
        return AjaxResult.success();
    }

    /**
     * 删除角色用户
     * @param roleId 角色id
     * @param userId 用户id
     * @return AjaxResult
     */
    @GetMapping("/deleteRoleUser")
    public AjaxResult deleteRoleUser(@RequestParam Long roleId,
                                     @RequestParam Long userId) {
        QueryWrapper<RoleUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        queryWrapper.eq("user_id", userId);
        boolean remove = roleUserServiceImpl.remove(queryWrapper);
        if(!remove){
            throw new RolePermissionException("删除失败");
        }
        return AjaxResult.success();
    }

    /**
     * 获取角色用户
     * @param roleId 角色id
     * @return AjaxResult
     */
    @GetMapping("/getRoleUsers")
    public AjaxResult getRoleUsers(@RequestParam Long roleId) {
        QueryWrapper<RoleUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        return AjaxResult.success(roleUserServiceImpl.list(queryWrapper));
    }



}
