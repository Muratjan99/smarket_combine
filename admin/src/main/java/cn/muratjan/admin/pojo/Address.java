package cn.muratjan.admin.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName address
 */
@TableName(value ="address")
@Data
public class Address implements Serializable {
    /**
     * 地址编号
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String addrId;

    /**
     * 用户编号
     */
    @Min(value = 1, message = "用户编号不能小于1")
    private Long userId;

    /**
     * 收货人
     */
    @NotBlank(message = "收货人不能为空")
    private String receiver;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String receiverPhone;

    /**
     * 校区
     */
    private String campus;

    /**
     * 用户录入的地址
     */
    private String addr;

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
}