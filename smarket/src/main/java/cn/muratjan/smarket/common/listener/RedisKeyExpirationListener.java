package cn.muratjan.smarket.common.listener;

import cn.muratjan.smarket.pojo.Order;
import cn.muratjan.smarket.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 主要作用就是:接收过期的redis消息,获取到key,key就是订单号,然后去更新订单号的状态(说明一下:用户5分钟不支付的话取消用户的订单)
 * @author 17543
 */
@Transactional
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    @Resource
    private OrderService orderServiceImpl;

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }


    @Override
    public void onMessage(Message message, byte[] pattern) {

        String orderId = message.toString();
        System.out.println(orderId+"过期了");
        if (!StringUtils.isBlank(orderId)) {
            QueryWrapper<Order> qorder = new QueryWrapper<>();
            qorder.eq("order_id", orderId);
            Order order = orderServiceImpl.getOne(qorder);
            if (order != null) {
                UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("order_id", orderId);
                if (order.getOrderStatus() == 0) {
                    updateWrapper.set("order_status", 3);
                }else if (order.getOrderStatus() == 1) {
                    updateWrapper.set("order_status", 2);
                }else if (order.getOrderStatus() == 21 || order.getOrderStatus() == 22) {
                    updateWrapper.set("order_status", 2);
                }else if (order.getOrderStatus() == 31 || order.getOrderStatus() == 32) {
                    updateWrapper.set("order_status", 3);
                }
                orderServiceImpl.update(updateWrapper);
            }
        }



    }
}