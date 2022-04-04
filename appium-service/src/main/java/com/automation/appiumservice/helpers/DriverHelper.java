package com.automation.appiumservice.helpers;

import io.appium.java_client.AppiumDriver;

import java.util.HashMap;
import java.util.Map;

public class DriverHelper {

    public static ThreadLocal<Map<String, AppiumDriver>> driverMap = new ThreadLocal<>();

    public static AppiumDriver getDriver(String sessionId){

        return driverMap.get().get(sessionId);
    }

    public static void setDriver(String sessionId, AppiumDriver driver){
        Map<String, AppiumDriver> map = new HashMap<>();
        map.put(sessionId, driver);
        driverMap.set(map);
    }
}
