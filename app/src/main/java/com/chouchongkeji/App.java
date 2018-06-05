package com.chouchongkeji;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * Hello world!
 */

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"com.yichen.auth", "com.chouchongkeji"})
@MapperScan("com.chouchongkeji.dao")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
