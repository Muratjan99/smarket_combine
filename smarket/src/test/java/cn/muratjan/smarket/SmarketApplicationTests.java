package cn.muratjan.smarket;

import cn.muratjan.smarket.common.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class SmarketApplicationTests {
    @Resource
    private RedisUtils redisUtils;

    @Test
    void contextLoads() {
        redisUtils.set("test", "test",30);
        System.out.println(redisUtils.get("test"));
    }
}
