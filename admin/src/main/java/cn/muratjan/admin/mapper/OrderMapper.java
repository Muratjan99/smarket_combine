package cn.muratjan.admin.mapper;

import cn.muratjan.admin.common.utils.MybatisPlusRedisCache;
import cn.muratjan.admin.pojo.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
* @author 17543
* @description 针对表【order】的数据库操作Mapper
* @createDate 2022-07-07 13:38:56
* @Entity cn.muratjan.admin.pojo.Order
*/
@CacheNamespace(implementation= MybatisPlusRedisCache.class,eviction=MybatisPlusRedisCache.class)
public interface OrderMapper extends MPJBaseMapper<Order> {

}




