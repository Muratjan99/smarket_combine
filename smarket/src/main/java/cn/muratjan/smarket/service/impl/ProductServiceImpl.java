package cn.muratjan.smarket.service.impl;


import cn.muratjan.smarket.mapper.ProductMapper;
import cn.muratjan.smarket.pojo.Product;
import cn.muratjan.smarket.service.ProductService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author 17543
* @description 针对表【product】的数据库操作Service实现
* @createDate 2022-07-04 13:48:49
*/
@Service
public class ProductServiceImpl extends MPJBaseServiceImpl<ProductMapper, Product>
    implements ProductService {

}




