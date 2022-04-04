package com.browser.utils;

public class PathUtils {
	
	public static String root_path = System.getProperty("user.dir");

	public static String device_path = root_path + "/src/test/config/devices/";
	
	public static String appPath = root_path + "/data/apps/";

	public static String stepsPath = "/src/test/java/com/albertsons/automation/stepdefs";

	public static String resourcesPath = "/src/main/resources";

	public static String dataFolder = root_path + "/data/features/";

	public static String browserModule = root_path + "/browser";

	
	public static String getRootPath() {
		
		String userDir = System.getProperty("user.dir");
		
		return userDir;
	}

}
