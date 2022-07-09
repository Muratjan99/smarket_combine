package cn.muratjan.admin.service.impl;

import cn.muratjan.admin.service.TableService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author MRT
 * @date 2022/7/9 16:16
 */
@SpringBootTest
class TableServiceImplTest {

    @Resource
    private TableService userTableVOService;
    @Test
    void getUserTable() {
        System.out.println(userTableVOService.getTables());
    }
}