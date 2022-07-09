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
 * @TableName private_chat
 */
@TableName(value ="private_chat")
@Data
public class PrivateChat implements Serializable {
    /**
     * 聊天编号
     */
    @TableId(type = IdType.AUTO)
    private Long chatId;

    /**
     * 
     */
    private Long sellerId;

    /**
     * 
     */
    private Long buyerId;

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