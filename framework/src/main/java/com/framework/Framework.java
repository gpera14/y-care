package com.framework;

import com.framework.core.execution.ExecutionConfig;
import com.framework.core.execution.ExecutionManager;
import com.framework.core.helpers.JarHelper;
import com.framework.core.managers.YamlFileManager;
import com.framework.core.reader.DataFileReader;
import com.framework.configurations.ConfigHandler;
import com.framework.core.enums.ComponentType;
import com.framework.core.models.Feature;
import com.framework.utils.PathUtils;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class Framework {

    /**
     * this method is used to start framework executions
     * creates device list and adds features list per device object
     */
    public void startExecutions() {

        ExecutionManager executionManager = ExecutionManager.getInstance();

        List<ExecutionConfig> configList = getComponentTypes();

        JarHelper.loadJar(configList);

        executionManager.StartFrameworkExecution(configList);
    }

    public List<ExecutionConfig> getComponentTypes(){

        ExecutionConfig executionConfig;

        List<Feature> featureList;

        List<ExecutionConfig> executionConfigList = new ArrayList<>();

        if(!ConfigHandler.config.isNull("browser")){

            executionConfig = new ExecutionConfig();

            featureList = getFeatureList(ConfigHandler.getConfigObj("browser"));

            executionConfig.setComponentType(ComponentType.browser);

            executionConfig.setFeatureList(featureList);

            executionConfigList.add(executionConfig);
        }
        if(!ConfigHandler.config.isNull("browserStack")){

            executionConfig = new ExecutionConfig();

            featureList = getFeatureList(ConfigHandler.getConfigObj("browserStack"));

            executionConfig.setComponentType(ComponentType.browserStack);

            executionConfig.setFeatureList(featureList);

            executionConfigList.add(executionConfig);
        }
        if(!ConfigHandler.config.isNull("sauceLabs")){

            executionConfig = new ExecutionConfig();

            featureList = getFeatureList(ConfigHandler.getConfigObj("sauceLabs"));

            executionConfig.setComponentType(ComponentType.sauceLabs);

            executionConfig.setFeatureList(featureList);

            executionConfigList.add(executionConfig);
        }
        if(!ConfigHandler.config.isNull("perfecto")){

            executionConfig = new ExecutionConfig();

            featureList = getFeatureList(ConfigHandler.getConfigObj("perfecto"));

            executionConfig.setComponentType(ComponentType.perfecto);

            executionConfig.setFeatureList(featureList);

            executionConfigList.add(executionConfig);
        }
        if(!ConfigHandler.config.isNull("device")){

            executionConfig = new ExecutionConfig();

            featureList = getFeatureList(ConfigHandler.getConfigObj("device"));

            executionConfig.setComponentType(ComponentType.device);

            executionConfig.setFeatureList(featureList);

            executionConfigList.add(executionConfig);
        }

        return executionConfigList;
    }

    private List<Feature> getFeatureList(JSONObject configObject) {

        List<Feature> featureList = new ArrayList<>();

        try {

            JSONArray features = configObject.getJSONArray("features");

            for (int i = 0; i < features.length(); i++) {

                String featureName = FilenameUtils.removeExtension(features.getString(i));

                YamlFileManager yamlFileManager = new YamlFileManager(PathUtils.dataFolder + featureName);

                Map<String, Map<String, String>> testCaseData = new DataFileReader(featureName).readDataFile();

                Feature feature = new Feature();

                feature.setName(features.getString(i));

                feature.setTestCaseData(testCaseData);

                featureList.add(feature);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return featureList;
    }
    /**
     *
     * @param features List of features from suites.xml file
     * @return List of Device objects containing device info for ios or android
     */
//    public List<Device> getDeviceList(ArrayList<Feature> features){
//
//        List<Device> devices = new ArrayList<>();
//
//        Map<String, String> deviceProps;
//
//        ArrayList<String> deviceNames = ConfigHandler.devices;
//
//        for (String name : deviceNames) {
//
//            String filePath = PathUtils.getRootPath() + PathUtils.device_path + name + ".properties";
//
//            deviceProps = ExecutionHelper.read_properties(filePath);
//
//            Device device = getDeviceFromProps(deviceProps);
//
//            device.setFeatures(features);
//
//            devices.add(device);
//        }
//
//        return devices;
//    }

    /**
     *
     * @param deviceProps
     * device properties after reading properties file of device name
     * @return Device type object containing Device Info object for each device
     */
//    public Device getDeviceFromProps(Map<String, String> deviceProps){
//
//        Device device = new ComplexDevice();
//
//        try {
//
//            String platformName = deviceProps.get("platform-name");
//
//            String deviceType = deviceProps.get("device-type");
//
//            DeviceInfo deviceInfo = new DeviceInfo();
//
//            deviceInfo.setAppName(deviceProps.get("app-name"));
//
//            deviceInfo.setDeviceName(deviceProps.get("device-name"));
//
//            deviceInfo.setPlatformName(platformName);
//
//            deviceInfo.setPlatformVersion(deviceProps.get("platform-version"));
//
//            deviceInfo.setDeviceType(deviceType);
//
//            deviceInfo.setAppPackage(deviceProps.get("app-package"));
//
//            deviceInfo.setAppActivity(deviceProps.get("app-activity"));
//
//            if (platformName.equalsIgnoreCase("android")) {
//
//                deviceInfo.setAvd(deviceProps.get("avd"));
//            }
//
//            if (deviceType.equalsIgnoreCase("cloud")) {
//
//                deviceInfo.setPerfectoUser(ConfigHandler.getConfigObj("perfecto").optString("user"));
//
//                deviceInfo.setPerfectoPassword(ConfigHandler.getConfigObj("perfecto").optString("password"));
//
//                deviceInfo.setToken(ConfigHandler.getConfigObj("perfecto").optString("token"));
//
//                deviceInfo.setCloudUrl(ConfigHandler.getConfigObj("perfecto").optString("serverUrl"));
//            }
//
//            device.setDeviceInfo(deviceInfo);
//        }
//
//        catch(Exception ex){
//
//            ex.printStackTrace();
//        }
//
//        return device;
//    }
}
