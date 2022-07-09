package cn.muratjan.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName sold_history
 */
@TableName(value ="sold_history")
@Data
public class SoldHistory implements Serializable {
    /**
     * sold_history主键
     */
    @TableId(type = IdType.AUTO)
    private Long shId;

    /**
     * 外键引用tuser.user_id，该表为卖家售出历史记录
     */
    private Long sellerId;

    /**
     * 外键引用product.product_id
     */
    private Long productId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    private Integer isDeleted;

    /**
     * 乐观锁
     */
    private Integer version;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建者
     */
    private Long createBy;

    /**
     * 更新者
     */
    private Long updateBy;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}