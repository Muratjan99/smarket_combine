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
 * @TableName chat_content
 */
@TableName(value ="chat_content")
@Data
public class ChatContent implements Serializable {
    /**
     * 聊天记录的编号，每个聊天记录都有唯一的编号。不同编号的聊天记录可以属于同一个私聊，也可以属于不同的私聊。
     */
    @TableId
    private Long contentId;

    /**
     * 消息编号（外键引用private_chat.chat_id）
     */
    private Long chatId;

    /**
     * 聊天内容，可以是图片也可以是文字
     */
    private String content;

    /**
     * 该条消息的发送时间
     */
    private LocalDateTime chatTime;

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