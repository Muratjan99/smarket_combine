package cn.muratjan.smarket.mapper;


import cn.muratjan.smarket.common.utils.MybatisPlusRedisCache;
import cn.muratjan.smarket.pojo.Campus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
* @author 17543
* @description 针对表【campus】的数据库操作Mapper
* @createDate 2022-07-07 11:20:58
* @Entity cn.muratjan.smarket.pojo.Campus
*/
@CacheNamespace(implementation= MybatisPlusRedisCache.class,eviction=MybatisPlusRedisCache.class)
public interface CampusMapper extends BaseMapper<Campus> {

}




