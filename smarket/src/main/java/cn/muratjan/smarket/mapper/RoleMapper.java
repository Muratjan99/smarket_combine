package cn.muratjan.smarket.mapper;


import cn.muratjan.smarket.common.utils.MybatisPlusRedisCache;
import cn.muratjan.smarket.pojo.Role;
import com.github.yulichang.base.MPJBaseMapper;
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




