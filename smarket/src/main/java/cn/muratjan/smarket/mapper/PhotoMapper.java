package cn.muratjan.smarket.mapper;

import cn.muratjan.smarket.common.utils.MybatisPlusRedisCache;
import cn.muratjan.smarket.pojo.Photo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
* @author 17543
* @description 针对表【avatars】的数据库操作Mapper
* @createDate 2022-07-03 00:16:44
* @Entity cn.muratjan.smarket.pojo.Avatars
*/
@CacheNamespace(implementation= MybatisPlusRedisCache.class,eviction=MybatisPlusRedisCache.class)
public interface PhotoMapper extends BaseMapper<Photo> {

}




