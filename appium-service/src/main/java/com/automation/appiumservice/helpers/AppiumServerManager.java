package com.automation.appiumservice.helpers;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.util.HashMap;

public class AppiumServerManager {


    public static AppiumDriverLocalService createAppiumLocalServer() {
        System.out.println("Starting Appium Server on Localhost");
        AppiumDriverLocalService appiumDriverLocalService;
        HashMap<String, String > environment = new HashMap<>();
        environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));
        AppiumServiceBuilder builder =
                new AppiumServiceBuilder()
                        //.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                        .withArgument(GeneralServerFlag.RELAXED_SECURITY)
                        .usingAnyFreePort()
                        .withEnvironment(environment);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "warn");
        appiumDriverLocalService = AppiumDriverLocalService.buildService(builder);
        appiumDriverLocalService.start();
        System.out.println( "Appium Server Started at......"
                + appiumDriverLocalService.getUrl());
        return appiumDriverLocalService;
    }
}
