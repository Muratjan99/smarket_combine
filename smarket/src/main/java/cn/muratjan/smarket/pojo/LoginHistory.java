package cn.muratjan.smarket.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName login_history
 */
@TableName(value ="login_history")
@Data
public class LoginHistory implements Serializable {
    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Long lhId;

    /**
     * 登录用户
     */
    private Long loginUser;

    /**
     * 登录ip
     */
    private String loginIp;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;

    /**
     * 登录结果
     */
    private Integer loginResult;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}