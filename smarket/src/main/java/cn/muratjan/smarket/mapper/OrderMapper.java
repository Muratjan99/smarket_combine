package cn.muratjan.smarket.mapper;

import cn.muratjan.smarket.common.utils.MybatisPlusRedisCache;
import cn.muratjan.smarket.pojo.Order;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
* @author 17543
* @description 针对表【order】的数据库操作Mapper
* @createDate 2022-07-05 17:05:59
* @Entity cn.muratjan.smarket.pojo.Order
*/
@CacheNamespace(implementation= MybatisPlusRedisCache.class,eviction=MybatisPlusRedisCache.class)
public interface OrderMapper extends MPJBaseMapper<Order> {

}




