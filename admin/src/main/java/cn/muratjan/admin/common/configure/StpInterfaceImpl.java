package cn.muratjan.admin.common.configure;

import cn.dev33.satoken.stp.StpInterface;
import cn.muratjan.admin.service.RoleService;
import cn.muratjan.admin.service.RoleUserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限验证接口扩展
 * @author 17543
 */
@Component    // 保证此类被SpringBoot扫描，完成Sa-Token的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {
    @Resource
    private RoleService roleService;
    @Resource
    private RoleUserService roleUserService;
    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> list = roleService.getRoleActionList(Long.parseLong(loginId.toString()));
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        List<String> list = roleUserService.getUserRolesNameList(Long.parseLong(loginId.toString()));
        return list;
    }

}
