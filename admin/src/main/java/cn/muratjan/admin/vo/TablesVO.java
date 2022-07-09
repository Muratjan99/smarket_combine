package cn.muratjan.admin.vo;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MRT
 * @date 2022/7/9 15:22
 */
@Data
public class TablesVO {
    private List<LocalDate> userCreateDates;
    private List<Integer> userCounts;

    private List<LocalDate> orderCreateDates;
    private List<Integer> orderCounts;

    private List<LocalDate> productCreateDates;
    private List<Integer> productCounts;

    private List<LocalDate> totalMoneyCreateDates;
    private List<Double> totalMoneys;

    public TablesVO() {
        userCreateDates = new ArrayList<>();
        userCounts = new ArrayList<>();
        orderCreateDates = new ArrayList<>();
        orderCounts = new ArrayList<>();
        productCreateDates = new ArrayList<>();
        productCounts = new ArrayList<>();
        totalMoneyCreateDates = new ArrayList<>();
        totalMoneys = new ArrayList<>();
    }

}
