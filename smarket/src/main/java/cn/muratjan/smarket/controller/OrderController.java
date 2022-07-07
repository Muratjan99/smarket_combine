package cn.muratjan.smarket.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.muratjan.smarket.common.AjaxResult;
import cn.muratjan.smarket.common.excaption.OrderException;
import cn.muratjan.smarket.common.excaption.ProductException;
import cn.muratjan.smarket.common.excaption.UserException;
import cn.muratjan.smarket.common.utils.RedisUtil;
import cn.muratjan.smarket.pojo.*;
import cn.muratjan.smarket.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

/**
 * @author MRT
 * @date 2022/7/6 9:28
 */

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderServiceImpl;
    @Resource
    private ITuserService tuserServiceImpl;
    @Resource
    private ProductService productServiceImpl;
    @Resource
    private AddressService addressServiceImpl;
    @Resource
    private CommentService commentServiceImpl;

    @Resource
    private RedisUtil redisUtil;

    private int expireTime =1;

    /**
     * 提交订单
     * @param order
     * @return
     */

    @PostMapping("/add")
    @SaCheckLogin
    public AjaxResult addOrder(@RequestBody @Validated Order order) {
        QueryWrapper<Product> qProduct = new QueryWrapper<>();
        qProduct.eq("product_id", order.getProductId());
        Product product = productServiceImpl.getOne(qProduct);

        if (product == null) {
            throw new OrderException("商品不存在");
        }
        if(product.getQuantity() < order.getQuantity()) {
            throw new OrderException("商品库存不足");
        }
        order.setSellerId(product.getVendorId());
        QueryWrapper<Tuser> quser = new QueryWrapper<>();
        quser.eq("user_id", order.getSellerId());
        long count = tuserServiceImpl.count(quser);
        if (count == 0) {
            throw new OrderException("商家不存在");
        }
        if(order.getSellerId() == StpUtil.getLoginIdAsLong()){
            throw new OrderException("不能购买自己的商品");
        }
        QueryWrapper<Address> qAddress = new QueryWrapper<>();
        qAddress.eq("addr_id", order.getAddrId());
        count = addressServiceImpl.count(qAddress);
        if (count == 0) {
            throw new OrderException("地址不存在");
        }
        order.setBuyerId(StpUtil.getLoginIdAsLong());
        order.setOrderStatus(0);
        order.setDatetime(LocalDateTime.now());
        boolean save = orderServiceImpl.save(order);
        if (!save) {
            throw new OrderException("订单添加失败");
        }
        redisUtil.set(order.getOrderId(), order,expireTime);
        UpdateWrapper<Product> uProduct = new UpdateWrapper<>();
        uProduct.eq("product_id", order.getProductId());
        uProduct.set("quantity", product.getQuantity() - order.getQuantity());
        if (product.getQuantity() - order.getQuantity()== 0) {
            uProduct.set("status", 1);
        }
        return AjaxResult.success("订单添加成功");
    }

    /**
     * 订单列表
     * @param page 分页参数
     * @param size 分页参数
     * @return
     */
    @GetMapping("/getOrderList")
    @SaCheckLogin
    public AjaxResult getOrderList(@RequestParam int page,
                                   @RequestParam int size,
                                   @RequestParam int mode,
                                   @RequestParam int status,
                                   String orderId) {
        //status =  1 提出交易 2 接收交易 3 完成交易 4 取消交易
        // mode =   0:全部 1:自己的 2:商家的
        MPJQueryWrapper<Order> qOrder = new MPJQueryWrapper<>();
        qOrder.selectAll(Order.class);
        if (mode == 0) {
            qOrder.eq("buyer_id", StpUtil.getLoginIdAsLong())
                    .or()
                    .eq("seller_id", StpUtil.getLoginIdAsLong());
        } else if (mode == 1) {
            qOrder.eq("buyer_id", StpUtil.getLoginIdAsLong());
        } else if (mode == 2) {
            qOrder.eq("seller_id", StpUtil.getLoginIdAsLong());
        } else {
            throw new OrderException("mode参数错误");
        }

        if (status == 0) {
            qOrder.eq("order_status", 0).orderByDesc("datetime");
        } else if (status == 1) {
            qOrder.eq("order_status", 0).orderByDesc("datetime");
        } else if (status == 2) {
            qOrder.eq("order_status", 1).orderByDesc("datetime");
        } else if (status == 3) {
            qOrder.eq("order_status", 2).orderByDesc("datetime");
        } else if (status == 4) {
            qOrder.orderByDesc("datetime");
        } else {
            throw new OrderException("status参数错误");
        }
        if (StringUtils.isNotEmpty(orderId)) {
            qOrder.like("order_id", orderId);
        }
        Page<Order> orderPage = new Page<>(page, size);
        return AjaxResult.success(orderServiceImpl.pageDeep(orderPage, qOrder));
    }

    /**
     * 更改订单状态
     * @param orderId 订单id
     * @param status 状态
     * @return AjaxResult
     */
    @GetMapping("/updateOrderStatus")
    @SaCheckLogin
    public AjaxResult updateOrderStatus(@RequestParam String orderId,
                                        @RequestParam @Min(value = 1,message = "状态参数不合法") int status) {


        QueryWrapper<Order> qOrder = new QueryWrapper<>();
        UpdateWrapper<Order> uOrder = new UpdateWrapper<>();
        qOrder.eq("order_id", orderId);
        uOrder.eq("order_id", orderId);
        Order order = orderServiceImpl.getOne(qOrder);
        if (order == null) {
            throw new OrderException("订单不存在");
        }
        if (order.getStatus() == 3) {
            throw new OrderException("此订单已删除不能更改状态");
        } else if (order.getStatus() == 2) {
            throw new OrderException("此订单已删除不能更改状态");
        }else if(order.getOrderStatus() == 0){
            if(order.getSellerId() != StpUtil.getLoginIdAsLong()){
                throw new OrderException("只有卖家才能接或者拒绝收交易");
            }
            if(status != 1 && status != 3){
                throw new OrderException("只有接收交易和取消交易");
            }
            if (status == 1) {
                uOrder.set("order_status", 1);
            } else {
                uOrder.set("order_status", 3);
            }
        }else if (order.getOrderStatus() == 1 ){
            if(order.getBuyerId() == StpUtil.getLoginIdAsLong() && (status == 21 || status == 31)){
               if (status == 21) {
                   uOrder.set("order_status", 21);
               } else {
                   uOrder.set("order_status", 31);
               }

            }else if(order.getSellerId() == StpUtil.getLoginIdAsLong() && (status == 22 || status == 32)){
                if (status == 22) {
                    uOrder.set("order_status", 22);
                } else {
                    uOrder.set("order_status", 32);
                }
            }
        }else if (order.getOrderStatus() == 21){
            if(order.getSellerId() == StpUtil.getLoginIdAsLong() && status == 22){
                uOrder.set("order_status", 2);
            }else{
                throw new OrderException("请等待卖家确认");
            }
        }else if (order.getOrderStatus() == 22){
            if(order.getBuyerId() == StpUtil.getLoginIdAsLong() && status == 21){
                uOrder.set("order_status", 1);
            }else{
                throw new OrderException("请等待买家确认");
            }
        }else if (order.getOrderStatus() == 31){
            if(order.getBuyerId() == StpUtil.getLoginIdAsLong() && status == 32){
                uOrder.set("order_status", 3);
            }else{
                throw new OrderException("请等待卖家确认");
            }
        }else if (order.getOrderStatus() == 32){
            if(order.getSellerId() == StpUtil.getLoginIdAsLong() && status == 31){
                uOrder.set("order_status", 3);
            }else{
                throw new OrderException("请等待买家确认");
            }
        }
        redisUtil.set(order.getOrderId(),order.getOrderStatus(),expireTime);
        boolean update = orderServiceImpl.update(uOrder);
        if (!update) {
            throw new OrderException("更改订单状态失败");
        }
        return AjaxResult.success("订单状态更新成功");
    }

    /**
     * 删除订单
     * @param orderId 订单id
     * @return AjaxResult
     */
    @GetMapping("/deleteOrder")
    @SaCheckLogin
    public AjaxResult deleteOrder(@RequestParam String orderId) {
        QueryWrapper<Order> qOrder = new QueryWrapper<>();
        qOrder.eq("order_id", orderId);
        Order order = orderServiceImpl.getOne(qOrder);
        if (order == null) {
            throw new OrderException("订单不存在");
        }
        if(order.getOrderStatus() != 2 && order.getOrderStatus() != 3){
            throw new OrderException("只有完成交易和取消交易的订单才能删除");
        }
        boolean remove = orderServiceImpl.remove(qOrder);
        if (!remove) {
            throw new OrderException("订单删除失败");
        }
        return AjaxResult.success("订单删除成功");
    }

    @GetMapping("/test")
    public AjaxResult test() {
        redisUtil.set("a","b",30);
        return AjaxResult.success("test");
    }

    /**
     * 订单评价
     * @param comment 评价内容
     * @return  AjaxResult
     */
    @PostMapping("/orderComment")
    @SaCheckLogin
    public AjaxResult orderComment(@RequestBody Comment comment){
        QueryWrapper<Order> qOrder = new QueryWrapper<>();
        qOrder.eq("order_id", comment.getOrderId());
        Order order = orderServiceImpl.getOne(qOrder);
        if (order == null) {
            throw new OrderException("订单不存在");
        }
        if(order.getStatus() != 1){
            throw new OrderException("每次订单只能评价一次");
        }
        QueryWrapper<Comment> qComment = new QueryWrapper<>();
        qComment.eq("order_id",comment.getOrderId());
        Comment comment1 = commentServiceImpl.getOne(qComment);
        if(comment1 != null){
            throw new OrderException("已经评价过了");
        }
        if(order.getOrderStatus() != 2){
            throw new OrderException("只有完成交易和取消交易的订单才能评价");
        }
        if(order.getBuyerId() != StpUtil.getLoginIdAsLong()){
            throw new OrderException("只有买家才能评价");
        }
        comment.setCommentTime(LocalDateTime.now());
        boolean save = commentServiceImpl.save(comment);
        if (!save) {
            throw new OrderException("评价失败");
        }
        return AjaxResult.success("评价成功");
    }

    /**
     * 查看评价
     * @param orderId 订单id
     * @return AjaxResult
     */
    @GetMapping("/getComment")
    @SaCheckLogin
    public AjaxResult getComment(@RequestParam String orderId){
        QueryWrapper<Comment> qComment = new QueryWrapper<>();
        qComment.eq("order_id", orderId);
        Comment comment = commentServiceImpl.getOne(qComment);
        if (comment == null) {
            throw new OrderException("订单还未评价");
        }
        return AjaxResult.success(comment);
    }

    /**
     * 删除评价
     * @param orderId 订单id
     * @return AjaxResult
     */

    @GetMapping("/deleteComment")
    @SaCheckLogin
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult deleteComment(@RequestParam String orderId){
        QueryWrapper<Order> qOrder = new QueryWrapper<>();
        qOrder.eq("order_id", orderId);
        Order order = orderServiceImpl.getOne(qOrder);
        if (order == null) {
            throw new OrderException("订单不存在");
        }

        QueryWrapper<Comment> qComment = new QueryWrapper<>();
        qComment.eq("order_id", orderId);
        Comment comment = commentServiceImpl.getOne(qComment);
        if (comment == null) {
            throw new OrderException("订单还未评价");
        }
        boolean remove = commentServiceImpl.remove(qComment);
        if (!remove) {
            throw new OrderException("删除评价失败");
        }
        UpdateWrapper<Order> uOrder = new UpdateWrapper<>();
        uOrder.eq("order_id", orderId);
        uOrder.set("status", 1);
        boolean update = orderServiceImpl.update(uOrder);
        if (!update) {
            throw new OrderException("删除评价失败");
        }
        return AjaxResult.success("删除评价成功");
    }

    /**
     * 查看订单详情
     * @param orderId 订单id
     * @return AjaxResult
     */
    @GetMapping("/getOrderDetail")
    @SaCheckLogin
    public AjaxResult getOrderDetail(@RequestParam String orderId){
        QueryWrapper<Order> qOrder = new QueryWrapper<>();
        qOrder.eq("order_id", orderId);
        Order order = orderServiceImpl.getOne(qOrder);
        if (order == null) {
            throw new OrderException("订单不存在");
        }
        return AjaxResult.success(order);
    }

    /**
     * 查看订单列表
     * @param productId 商品id
     * @return AjaxResult
     */
    @GetMapping("/getProductDetail")
    @SaCheckLogin
    public AjaxResult getProductDetail(@RequestParam String productId){
        QueryWrapper<Product> qProduct = new QueryWrapper<>();
        qProduct.eq("product_id", productId);
        Product product = productServiceImpl.getOne(qProduct);
        if (product == null) {
            throw new ProductException("商品不存在");
        }
        return AjaxResult.success(product);
    }

    /**
     * 查看卖家信息
     * @param sellerId 卖家id
     * @return AjaxResult
     */
    @GetMapping("/getSellerInfo")
    @SaCheckLogin
    public AjaxResult getSellerInfo(@RequestParam String sellerId){
        QueryWrapper<Tuser> qSeller = new QueryWrapper<>();
        qSeller.eq("user_id", sellerId);
        Tuser seller = tuserServiceImpl.getOne(qSeller);
        if (seller == null) {
            throw new UserException("商家不存在");
        }
        return AjaxResult.success(seller);
    }

}
