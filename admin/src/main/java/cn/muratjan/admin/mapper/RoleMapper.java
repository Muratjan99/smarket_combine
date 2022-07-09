package cn.muratjan.admin.mapper;

import cn.muratjan.admin.common.utils.MybatisPlusRedisCache;
import cn.muratjan.admin.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import com.github.yulichang.base.MPJBaseService;
import org.apache.ibatis.annotations.CacheNamespace;

/**
* @author 17543
* @description 针对表【role】的数据库操作Mapper
* @createDate 2022-07-07 14:04:15
* @Entity cn.muratjan.admin.pojo.Role
*/
@CacheNamespace(implementation= MybatisPlusRedisCache.class,eviction=MybatisPlusRedisCache.class)
public interface RoleMapper extends MPJBaseMapper<Role> {

}




