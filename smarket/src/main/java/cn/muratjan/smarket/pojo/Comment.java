package cn.muratjan.smarket.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * 
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class Comment implements Serializable {
    /**
     * 评论编号
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String commentId;

    /**
     * 订单编号
     */
    @Min(value = 1, message = "订单编号不能小于1")
    private String orderId;

    /**
     * 评论时间
     */
    private LocalDateTime commentTime;

    /**
     * 文字内容
     */
    private String textContent;

    /**
     * 照片内容
     */
    private String photoContent;

    /**
     * 评分
     */
    @Min(value = 1, message = "评分不能小于1")
    private Integer score;

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
}