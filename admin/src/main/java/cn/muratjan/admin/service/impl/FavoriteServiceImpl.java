package cn.muratjan.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.muratjan.admin.pojo.Favorite;
import cn.muratjan.admin.service.FavoriteService;
import cn.muratjan.admin.mapper.FavoriteMapper;
import org.springframework.stereotype.Service;

/**
* @author 17543
* @description 针对表【favorite】的数据库操作Service实现
* @createDate 2022-07-07 13:38:56
*/
@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite>
    implements FavoriteService{

}




