package com.framework;

import com.framework.configurations.ConfigHandler;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class FrameworkApplication {

    public static void main(String[] args) throws IOException {

        //SpringApplication.run(BrowserApplication.class, args);

        ConfigHandler.initConfigurations();

        Framework framework = new Framework();

        framework.startExecutions();
    }
}
