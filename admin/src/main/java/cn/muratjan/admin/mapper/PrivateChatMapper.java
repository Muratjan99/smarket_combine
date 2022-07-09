package cn.muratjan.admin.mapper;

import cn.muratjan.admin.common.utils.MybatisPlusRedisCache;
import cn.muratjan.admin.pojo.PrivateChat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
* @author 17543
* @description 针对表【private_chat】的数据库操作Mapper
* @createDate 2022-07-07 13:38:56
* @Entity cn.muratjan.admin.pojo.PrivateChat
*/
@CacheNamespace(implementation= MybatisPlusRedisCache.class,eviction=MybatisPlusRedisCache.class)
public interface PrivateChatMapper extends BaseMapper<PrivateChat> {

}




