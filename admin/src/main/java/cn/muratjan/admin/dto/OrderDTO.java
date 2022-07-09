package cn.muratjan.admin.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author MRT
 * @date 2022/7/9 16:07
 */
@TableName("`order`")
@Data
public class OrderDTO implements Serializable {
    private Integer count;
    private LocalDate createDate;
    private Double totalMoney;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
