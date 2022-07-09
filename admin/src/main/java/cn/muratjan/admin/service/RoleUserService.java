package cn.muratjan.admin.service;

import cn.muratjan.admin.pojo.RoleUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
* @author 17543
* @description 针对表【role_user】的数据库操作Service
* @createDate 2022-07-07 15:58:32
*/
public interface RoleUserService extends MPJBaseService<RoleUser> {
    /**
     * 根据用户id查询用户角色
     * @param userId 用户id
     * @return 角色列表
     */
    List<Long> getUserRolesList(Long userId);


    List<String> getUserRolesNameList(Long userId);
}
