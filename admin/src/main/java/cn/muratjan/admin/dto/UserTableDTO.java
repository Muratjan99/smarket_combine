package cn.muratjan.admin.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author MRT
 * @date 2022/7/9 0:05
 */
@TableName("tuser")
@Data
public class UserTableDTO implements Serializable {
    @TableField(value = "count")
    private Integer  count;
    private LocalDate createDate;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
