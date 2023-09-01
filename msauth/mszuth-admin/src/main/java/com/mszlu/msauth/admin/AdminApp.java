package com.mszlu.msauth.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 码神之路
 */
@SpringBootApplication
@MapperScan("com.mszlu.msauth.admin.repository")
@EnableTransactionManagement
public class AdminApp {

    public static void main(String[] args) {
        SpringApplication.run(AdminApp.class,args);
    }
}
