package com.zzb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zzbang
 */
@SpringBootApplication
@MapperScan("com.zzb.dao")
public class PointManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PointManagerApplication.class, args);
    }

}
