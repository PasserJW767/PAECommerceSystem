package com.personal.ecommercesys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@SpringBootApplication

/*
* 报错：Field userinfoDao in com.personal.ecommercesys.service.impl.UserinfoServiceImpl required a bean of type 'com.personal.ecommercesys.dao.UserinfoDao' that could not be found.
* 加一个注解进行扫描就可以了，即@MapperScan，要让Springboot自动创建出Dao的bean的话，要让它去发现出UserinfDao的那个包，在启动引导类加上注解。
* MapperScan括号中填写路径，如果多个包就用{"",""}，把包的路径直接复制进去，这样就可以扫描到了。
* Service也要被扫描，也需要加一个注解：
* */
@MapperScan(basePackages = "com.personal.ecommercesys.dao")

public class EcommercesysApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommercesysApplication.class, args);
    }
}
