package cn.muratjan.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.muratjan.admin.pojo.Question;
import cn.muratjan.admin.service.QuestionService;
import cn.muratjan.admin.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author 17543
* @description 针对表【question】的数据库操作Service实现
* @createDate 2022-07-07 13:38:56
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

}




