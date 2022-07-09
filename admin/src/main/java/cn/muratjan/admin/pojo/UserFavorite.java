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
 * @TableName user_favorite
 */
@TableName(value ="user_favorite")
@Data
public class UserFavorite implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long ufId;

    /**
     * 该主键外键引用favorite.fav_id
     */
    private Long favId;

    /**
     * 该主键外键引用tuser.user_id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新日期
     */
    private LocalDateTime updateTime;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 逻辑删除
     */
    private Integer isDeleted;

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