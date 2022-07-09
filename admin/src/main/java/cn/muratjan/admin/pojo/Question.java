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
 * @TableName question
 */
@TableName(value ="question")
@Data
public class Question implements Serializable {
    /**
     * 问题id
     */
    @TableId
    private Long questId;

    /**
     * 提问者id
     */
    private Long questUserId;

    /**
     * 商品编号
     */
    private Long productId;

    /**
     * 提问时间
     */
    private LocalDateTime questionTime;

    /**
     * 提问内容
     */
    private String content;

    /**
     * 
     */
    private LocalDateTime createTime;

    /**
     * 
     */
    private LocalDateTime updateTime;

    /**
     * 
     */
    private String isDeleted;

    /**
     * 
     */
    private String version;

    /**
     * 
     */
    private String status;

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