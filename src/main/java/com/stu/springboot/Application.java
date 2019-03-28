package com.stu.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//此注入为springboot项目的必须注解，它是springboot项目的入口
@SpringBootApplication
//若mapper类上没有自动注入则需要加上下面的语句
//@MapperScan(basePackages = "com.stu.springboot.mapper")

@EnableTransactionManagement  //开启事务支持
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
