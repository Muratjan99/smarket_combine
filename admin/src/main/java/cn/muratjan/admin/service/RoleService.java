package cn.muratjan.admin.service;

import cn.muratjan.admin.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
* @author 17543
* @description 针对表【role】的数据库操作Service
* @createDate 2022-07-07 14:04:15
*/
public interface RoleService extends MPJBaseService<Role> {


    List<String> getRoleActionList(Long userId);

}
