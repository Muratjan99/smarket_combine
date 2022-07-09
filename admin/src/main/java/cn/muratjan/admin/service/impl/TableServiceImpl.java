package cn.muratjan.admin.service.impl;

import cn.muratjan.admin.dto.OrderDTO;
import cn.muratjan.admin.dto.ProductTableDTO;
import cn.muratjan.admin.mapper.OrderDTOMapper;
import cn.muratjan.admin.mapper.ProductTableDTOMapper;
import cn.muratjan.admin.mapper.UserTableDTOMapper;
import cn.muratjan.admin.service.TableService;
import cn.muratjan.admin.dto.UserTableDTO;
import cn.muratjan.admin.vo.TablesVO;
import com.github.yulichang.query.MPJQueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MRT
 * @date 2022/7/9 0:11
 */
@Service
public class TableServiceImpl implements TableService {
    @Resource
    private OrderDTOMapper orderDTOMapper;
    @Resource
    private UserTableDTOMapper userTableDTOMapper;
    @Resource
    private ProductTableDTOMapper productTableDTOMapper;

    /**
     * 获取表格数据
     *
     * @return 表格数据
     */
    @Override
    public TablesVO getTables() {
        TablesVO tablesVO = new TablesVO();
        // 用户表格数据
        MPJQueryWrapper<UserTableDTO> queryWrapper = new MPJQueryWrapper<>();
        queryWrapper.select(" CONVERT(create_time,DATE) as create_date  ", "count(*) as count").groupBy("CONVERT(create_time,DATE)");
        List<UserTableDTO> list = userTableDTOMapper.selectList(queryWrapper);
        for (UserTableDTO userTableDTO : list) {
            tablesVO.getUserCreateDates().add(userTableDTO.getCreateDate());
            tablesVO.getUserCounts().add(userTableDTO.getCount());
        }
        // 订单表格数据
        List<OrderDTO> orderList = orderDTOMapper.selectList(new MPJQueryWrapper<OrderDTO>()
                .select("CONVERT(create_time,DATE) as create_date", "count(*) as count", "sum(total_money) as total_money")
                .groupBy("CONVERT(create_time,DATE)"));
        for (OrderDTO orderDTO : orderList) {
            tablesVO.getOrderCreateDates().add(orderDTO.getCreateDate());
            tablesVO.getTotalMoneyCreateDates().add(orderDTO.getCreateDate());
            tablesVO.getOrderCounts().add(orderDTO.getCount());
            tablesVO.getTotalMoneys().add(orderDTO.getTotalMoney());
        }
        // 商品表格数据
        List<ProductTableDTO> productList = productTableDTOMapper.selectList(new MPJQueryWrapper<ProductTableDTO>()
                .select("CONVERT(create_time,DATE) as create_date", "count(*) as count")
                .groupBy("CONVERT(create_time,DATE)"));
        for (ProductTableDTO productTableDTO : productList) {
            tablesVO.getProductCreateDates().add(productTableDTO.getCreateDate());
            tablesVO.getProductCounts().add(productTableDTO.getCount());
        }
        return tablesVO;
    }
}
