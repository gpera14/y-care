package com.framework.utils;

public class PathUtils {
	
	public static String root_path = System.getProperty("user.dir");

	//public static String stepsPath = "/src/test/java/com/albertsons/automation/stepdefs";

	public static String resourcesPath = root_path + "/src/test/resources";

	public static String device_path = resourcesPath + "/config/devices/";

	public static String appPath = resourcesPath + "/apps";

	public static String dataFolder = resourcesPath + "/data";

	public static String featuresFolder = resourcesPath + "/features";

	public static String getRootPath() {
		
		String userDir = System.getProperty("user.dir");
		
		return userDir;
	}

}
