package cn.muratjan.smarket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("cn.muratjan.smarket.mapper")
@EnableTransactionManagement
@EnableCaching
public class SmarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmarketApplication.class, args);
    }

}
