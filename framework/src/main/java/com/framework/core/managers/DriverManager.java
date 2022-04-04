package com.framework.core.managers;


import com.framework.core.factory.IDriver;

public class DriverManager {


    public static ThreadLocal<IDriver> componentDriver = new ThreadLocal<>();

    public static void setDriver(IDriver driver) {
        componentDriver.set(driver);
    }

}

