package cn.muratjan.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.muratjan.admin.pojo.Product;
import cn.muratjan.admin.service.ProductService;
import cn.muratjan.admin.mapper.ProductMapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author 17543
* @description 针对表【product】的数据库操作Service实现
* @createDate 2022-07-07 13:38:56
*/
@Service
public class ProductServiceImpl extends MPJBaseServiceImpl<ProductMapper, Product>
    implements ProductService{

}




