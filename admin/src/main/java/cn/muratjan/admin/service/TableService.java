package cn.muratjan.admin.service;

import cn.muratjan.admin.dto.UserTableDTO;
import cn.muratjan.admin.vo.TablesVO;

import java.util.List;

/**
 * @author MRT
 * @date 2022/7/9 0:10
 */
public interface TableService{
    /**
     * 获取表格数据
     * @return 表格数据
     */

    TablesVO getTables();
}
