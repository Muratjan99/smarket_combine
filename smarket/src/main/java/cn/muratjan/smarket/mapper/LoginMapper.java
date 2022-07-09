package cn.muratjan.smarket.mapper;


import cn.muratjan.smarket.common.utils.MybatisPlusRedisCache;
import cn.muratjan.smarket.pojo.Login;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * @author MRT
 * @date 2022/6/28 14:53
 */
@CacheNamespace(implementation= MybatisPlusRedisCache.class,eviction=MybatisPlusRedisCache.class)
public interface LoginMapper extends BaseMapper<Login> {

}
