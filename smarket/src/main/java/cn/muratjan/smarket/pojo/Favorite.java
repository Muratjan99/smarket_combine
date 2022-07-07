package cn.muratjan.smarket.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.github.yulichang.annotation.EntityMapping;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * 
 * @TableName favorite
 */
@TableName(value ="favorite")
@Data
public class Favorite implements Serializable {
    /**
     * 收藏编号
     */
    @TableId(type = IdType.AUTO)
    private Long favId;

    /**
     * 被收藏商品的商品编号，外键引用product.product_id
     */
    @Min(value = 1, message = "商品编号不能小于1")
    private Long productId;

    /**
     * 与收藏记录关联的时间
     */
    private LocalDateTime favDate;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 更新日期
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 更新者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 版本
     */
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    /**
     * 逻辑删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    @EntityMapping(thisField = "productId",joinField = "productId")
    private Product product;
}