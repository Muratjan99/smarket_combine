package cn.muratjan.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.muratjan.admin.pojo.Order;
import cn.muratjan.admin.service.OrderService;
import cn.muratjan.admin.mapper.OrderMapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author 17543
* @description 针对表【order】的数据库操作Service实现
* @createDate 2022-07-07 13:38:56
*/
@Service
public class OrderServiceImpl extends MPJBaseServiceImpl<OrderMapper, Order>
    implements OrderService{

}




