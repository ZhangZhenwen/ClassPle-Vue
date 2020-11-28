package com.zhenwen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhenwen
 */
@SpringBootApplication
@MapperScan("com.zhenwen.mapper")
public class ClasspleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClasspleApplication.class, args);
    }

}
