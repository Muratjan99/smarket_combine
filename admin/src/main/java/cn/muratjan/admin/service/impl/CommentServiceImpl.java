package cn.muratjan.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.muratjan.admin.pojo.Comment;
import cn.muratjan.admin.service.CommentService;
import cn.muratjan.admin.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author 17543
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2022-07-07 13:38:56
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




