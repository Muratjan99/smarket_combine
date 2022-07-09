package cn.muratjan.smarket.mapper;


import cn.muratjan.smarket.common.utils.MybatisPlusRedisCache;
import cn.muratjan.smarket.pojo.Tuser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * @author MRT
 * @date 2022/6/28 10:46
 */
@CacheNamespace(implementation= MybatisPlusRedisCache.class,eviction=MybatisPlusRedisCache.class)
public interface TuserMapper extends BaseMapper<Tuser> {
}
