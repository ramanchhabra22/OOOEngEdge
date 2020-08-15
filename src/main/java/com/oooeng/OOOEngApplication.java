package com.oooeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.oooeng.*")
public class OOOEngApplication {
    public static void main(String[] args) {
        SpringApplication.run(OOOEngApplication.class, args);
    }

}
