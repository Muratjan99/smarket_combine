package cn.muratjan.smarket.service.impl;

import cn.muratjan.smarket.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.muratjan.smarket.service.CommentService;
import cn.muratjan.smarket.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author 17543
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2022-07-06 19:18:37
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




