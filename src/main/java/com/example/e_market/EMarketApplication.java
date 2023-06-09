package com.example.e_market;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.e_market.mapper")
public class EMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(EMarketApplication.class, args);
    }

}
