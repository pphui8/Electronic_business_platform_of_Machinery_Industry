package com.example.mallwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan("com.example.mallwork.dao")
public class MallWorkApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallWorkApplication.class, args);
    }

}
