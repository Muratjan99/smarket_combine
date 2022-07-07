package cn.muratjan.smarket.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.github.yulichang.annotation.EntityMapping;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 
 * @TableName order
 */
@TableName(value ="order")
@Data
public class Order implements Serializable {
    /**
     * 订单编号
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String orderId;

    /**
     * 商品id
     */
    @Min(value = 1, message = "商品id不能小于1")
    private Long productId;

    /**
     * 买家id
     */
    @Min(value = 1, message = "买家id不能小于1")
    private Long buyerId;

    /**
     * 卖家id
     */
    @Min(value = 1, message = "卖家id不能小于1")
    private Long sellerId;

    /**
     * 地址id
     */
    @NotBlank(message = "地址id不能为空")
    private String addrId;

    /**
     * 购买数量
     */
    @Min(value = 1, message = "购买数量不能小于1")
    private Integer quantity;

    /**
     * 下单时间
     */
    private LocalDateTime datetime;

    /**
     * 备注
     */
    private String note;

    /**
     * 订单完成状态，0表示订单已提交，但未完成，1表示订单完成。确认订单完成之后才可以评价。
     */
    private Integer orderStatus;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer isDeleted;

    /**
     * 乐观锁
     */
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    /**
     * 状态
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer status;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 更新者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    @EntityMapping(thisField = "buyerId",joinField = "userId")
    private Tuser buyer;

    @TableField(exist = false)
    @EntityMapping(thisField = "sellerId",joinField = "userId")
    private Tuser seller;

    @TableField(exist = false)
    @EntityMapping(thisField = "addrId",joinField = "addrId")
    private Address address;

    @TableField(exist = false)
    @EntityMapping(thisField = "productId",joinField = "productId")
    private Product product;
}