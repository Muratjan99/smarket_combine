package cn.muratjan.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.muratjan.admin.pojo.Photo;
import cn.muratjan.admin.service.PhotoService;
import cn.muratjan.admin.mapper.PhotoMapper;
import org.springframework.stereotype.Service;

/**
* @author 17543
* @description 针对表【photo】的数据库操作Service实现
* @createDate 2022-07-07 13:38:56
*/
@Service
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo>
    implements PhotoService{

}




