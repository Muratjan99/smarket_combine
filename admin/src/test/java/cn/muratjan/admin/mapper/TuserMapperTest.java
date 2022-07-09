package cn.muratjan.admin.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author MRT
 * @date 2022/7/9 13:24
 */
@SpringBootTest
class TuserMapperTest {

    @Resource
    private TuserMapper tuserMapper;
    @Test
    void test() {
        System.out.println(tuserMapper.selectList(null));
    }

}