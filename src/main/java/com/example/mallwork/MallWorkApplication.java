package com.example.mallwork;

import com.example.mallwork.DaoImp.UserDaoImp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.mallwork.Dao"})
public class MallWorkApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallWorkApplication.class, args);
        UserDaoImp udi = new UserDaoImp();
//        System.out.println("===================================");
//        System.out.println("测试" + udi.checkUserByAccount("Cat"));
//        System.out.println("===================================");
    }

}
