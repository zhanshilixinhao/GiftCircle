package com.chouchongkeji;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * Hello world!
 */

@SpringBootApplication
@ComponentScan({"com.yichen.auth", "com.chouchongkeji"})
@MapperScan("com.chouchongkeji.dial.dao")
@EnableScheduling
public class App extends SpringBootServletInitializer {
//本地运行
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

//服务器
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(App.class);
//    }
}
