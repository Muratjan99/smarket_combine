package cn.muratjan.smarket.service.impl;


import cn.muratjan.smarket.mapper.OrderMapper;
import cn.muratjan.smarket.pojo.Order;
import cn.muratjan.smarket.service.OrderService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author 17543
* @description 针对表【order】的数据库操作Service实现
* @createDate 2022-07-05 17:05:59
*/
@Service
public class OrderServiceImpl extends MPJBaseServiceImpl<OrderMapper, Order>
    implements OrderService {

}




