package cn.muratjan.admin.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.yulichang.annotation.EntityMapping;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName product
 */
@TableName(value ="product")
@Data
public class Product implements Serializable {
    /**
     * 商品编号
     */
    @TableId(type = IdType.AUTO)
    private Long productId;

    /**
     * 卖家编号
     */
    private Long vendorId;

    /**
     * 商品类别
     */
    private Long categoryId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品图片
     */
    private String photo;

    /**
     * 商品单价
     */
    private Double price;

    /**
     * 数量
     */
    private Long quantity;

    /**
     * 发布地点
     */
    private String address;

    /**
     * 原价
     */
    private Double originalPrice;

    /**
     * 成色(1X 1二手X是成新度，0全新)
     */
    private Integer quality;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
    @EntityMapping(thisField = "categoryId",joinField = "categoryId")
    private Category category;

    @TableField(exist = false)
    @EntityMapping(thisField = "vendorId",joinField = "userId")
    private Tuser vendor;
}