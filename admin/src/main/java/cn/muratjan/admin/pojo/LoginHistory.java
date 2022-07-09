package cn.muratjan.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTime;

    /**
     * 登录结果
     */
    private Integer loginResult;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}