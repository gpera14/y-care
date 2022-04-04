package com.framework.core.managers;

import java.util.Map;

public class TestCaseData {

    private static ThreadLocal<Map<String, Map<String, String>>> featureData = new ThreadLocal<>();

    private static ThreadLocal<Map<String, String>> testCaseData = new ThreadLocal<>();

    public static void setTestData(String scenarioName){

        testCaseData.set(featureData.get().get(scenarioName));
    }

    public static void setTestCaseData(Map<String, Map<String, String>> data) {

        featureData.set(data);
    }

    public static String get(String key){

        return testCaseData.get().get(key);
    }
}
