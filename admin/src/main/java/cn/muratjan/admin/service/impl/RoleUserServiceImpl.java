package cn.muratjan.admin.service.impl;

import cn.muratjan.admin.mapper.RoleMapper;
import cn.muratjan.admin.pojo.Role;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.muratjan.admin.pojo.RoleUser;
import cn.muratjan.admin.service.RoleUserService;
import cn.muratjan.admin.mapper.RoleUserMapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 17543
* @description 针对表【role_user】的数据库操作Service实现
* @createDate 2022-07-07 15:58:32
*/
@Service
public class RoleUserServiceImpl extends MPJBaseServiceImpl<RoleUserMapper, RoleUser>
    implements RoleUserService{
    @Resource
    private RoleMapper roleMapper;

    /**
     * 根据用户id查询用户角色
     *
     * @param userId 用户id
     * @return 角色列表
     */
    @Override
    public List<Long> getUserRolesList(Long userId) {
        QueryWrapper<RoleUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<RoleUser> list = this.list(queryWrapper);
        List<Long> roleIdList = list.stream().map(RoleUser::getRoleId).collect(java.util.stream.Collectors.toList());
        return roleIdList;
    }

    @Override
    public List<String> getUserRolesNameList(Long userId) {
        QueryWrapper<RoleUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<RoleUser> list = this.list(queryWrapper);
        List<Long> roleIdList = list.stream().map(RoleUser::getRoleId).collect(java.util.stream.Collectors.toList());
        QueryWrapper<Role> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.in("role_id", roleIdList);
        List<Role> list1 = roleMapper.selectList(queryWrapper1);
        return list1.stream().map(Role::getRoleName).collect(java.util.stream.Collectors.toList());
    }
}




