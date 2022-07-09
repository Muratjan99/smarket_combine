package cn.muratjan.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.muratjan.admin.pojo.Category;
import cn.muratjan.admin.service.CategoryService;
import cn.muratjan.admin.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author 17543
* @description 针对表【category】的数据库操作Service实现
* @createDate 2022-07-07 13:38:56
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




