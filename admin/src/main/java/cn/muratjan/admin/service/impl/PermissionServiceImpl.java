package cn.muratjan.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.muratjan.admin.pojo.Permission;
import cn.muratjan.admin.service.PermissionService;
import cn.muratjan.admin.mapper.PermissionMapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author 17543
* @description 针对表【permission】的数据库操作Service实现
* @createDate 2022-07-07 14:04:15
*/
@Service
public class PermissionServiceImpl extends MPJBaseServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

}




