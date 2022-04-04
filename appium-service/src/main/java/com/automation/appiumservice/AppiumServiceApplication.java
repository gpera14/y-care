package com.automation.appiumservice;

import com.automation.appiumservice.controllers.AppiumController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.automation.appiumservice.controllers", "com.automation.appiumservice.services"})
public class AppiumServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppiumServiceApplication.class, args);
    }

}
