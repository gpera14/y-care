package com.browserstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.browserstack.controllers", "com.browserstack.services"})
public class BrowserstackApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrowserstackApplication.class, args);
    }

}
