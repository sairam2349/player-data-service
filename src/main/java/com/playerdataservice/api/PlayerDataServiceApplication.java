package com.playerdataservice.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.playerdataservice.api")
public class PlayerDataServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayerDataServiceApplication.class, args);
    }

}
