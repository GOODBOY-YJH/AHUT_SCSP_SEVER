package com.example.ahut_scsp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
// @MapperScan(basePackages = "com.example.ahut_scsp.mapper")
public class AhutScspApplication {

    public static void main(String[] args) {
        SpringApplication.run(AhutScspApplication.class, args);
    }

}
