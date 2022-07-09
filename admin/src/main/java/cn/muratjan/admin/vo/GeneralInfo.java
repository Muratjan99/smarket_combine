package cn.muratjan.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author MRT
 * @date 2022/7/8 0:44
 */
@Data
@AllArgsConstructor
public class GeneralInfo {
    private long userCount;
    private long productCount;
    private long orderCount;
    private double totalMoney;
}
