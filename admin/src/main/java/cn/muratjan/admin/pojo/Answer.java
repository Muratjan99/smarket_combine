package cn.muratjan.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

/**
 * 
 * @TableName answer
 */
@TableName(value ="answer")
@Data
public class Answer implements Serializable {
    /**
     * 回答id
     */
    @TableId
    private Long ansId;

    /**
     * 回答问题的用户的id
     */
    private Long ansUserId;

    /**
     * 商品id
     */
    private Long questId;

    /**
     * 回答时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime ansTime;

    /**
     * 回答内容
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
    private Long createBy;

    /**
     * 
     */
    private Long updateBy;

    /**
     * 
     */
    private String isDeleted;

    /**
     * 
     */
    private String status;

    /**
     * 
     */
    private Integer version;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}