package cn.muratjan.admin.service.impl;

import cn.muratjan.admin.service.RoleUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.muratjan.admin.pojo.Role;
import cn.muratjan.admin.service.RoleService;
import cn.muratjan.admin.mapper.RoleMapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 17543
* @description 针对表【role】的数据库操作Service实现
* @createDate 2022-07-07 14:04:15
*/
@Service
public class RoleServiceImpl extends MPJBaseServiceImpl<RoleMapper, Role>
    implements RoleService{

    @Resource
    private RoleUserService roleUserService;
    @Override
    public List<String> getRoleActionList(Long userId) {
        List<Long> userRolesList = roleUserService.getUserRolesList(userId);
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", userRolesList);
        List<Role> list = this.listDeep(queryWrapper);
        List<String> actionList = list.stream().map(Role::getPermissionActions).flatMap(List::stream).distinct().collect(java.util.stream.Collectors.toList());
        return actionList;
    }
}




