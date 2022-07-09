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
 * @TableName user_behavior
 */
@TableName(value ="user_behavior")
@Data
public class UserBehavior implements Serializable {
    /**
     * 用户行为编号。
用户行为描述为
(user_id, product_id, ub_action, ub_date)，即某用户在某特定时间对某商品有什么行为。行为包括：
* pv（点击商品详情页)
* buy (商品购买)
* fav(收藏商品)
该表用于推荐算法。
     */
    @TableId
    private Long ubId;

    /**
     * 
     */
    private Long userId;

    /**
     * 
     */
    private Long productId;

    /**
     * 
     */
    private Long categoryId;

    /**
     * 只能有三种取值：
* pv（点击商品详情页)
* buy (商品购买)
* fav(收藏商品)
     */
    private String action;

    /**
     * 该行为发生的时间
     */
    private LocalDateTime ubDate;

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