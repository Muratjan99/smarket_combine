package cn.muratjan.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName campus
 */
@TableName(value ="campus")
@Data
public class Campus implements Serializable {
    /**
     * 校区id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String campusId;

    /**
     * 校区名称
     */
    private String campusName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}