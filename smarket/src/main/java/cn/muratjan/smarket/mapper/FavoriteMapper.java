package cn.muratjan.smarket.mapper;


import cn.muratjan.smarket.common.utils.MybatisPlusRedisCache;
import cn.muratjan.smarket.pojo.Favorite;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
* @author 17543
* @description 针对表【favorite】的数据库操作Mapper
* @createDate 2022-07-04 23:07:47
* @Entity cn.muratjan.smarket.pojo.Favorite
*/
@CacheNamespace(implementation= MybatisPlusRedisCache.class,eviction=MybatisPlusRedisCache.class)
public interface FavoriteMapper extends MPJBaseMapper<Favorite> {

}




