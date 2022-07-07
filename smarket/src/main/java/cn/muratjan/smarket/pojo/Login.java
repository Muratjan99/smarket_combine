package cn.muratjan.smarket.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName login
 */
@TableName(value ="login")
@Data
public class Login implements Serializable {
    /**
     * 登录编号
     */
    @TableId(type = IdType.AUTO)
    private Long loginId;

    /**
     * 登录类型
     */
    private Long loginType;

    /**
     * 登录用户名
     */
    private Long loginUser;

    /**
     * 
     */
    private String loginSalt;

    /**
     * 登录密码
     */
    private String loginToken;

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
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 更新者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 逻辑删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer isDeleted;

    /**
     * 版本
     */
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}