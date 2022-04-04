package com.framework.core.execution;

import com.framework.core.component.ComplexComponent;
import com.framework.core.component.Component;
import com.framework.core.component.ComponentManager;
import com.framework.core.managers.YamlFileManager;
import com.framework.configurations.ConfigHandler;
import com.framework.core.enums.ComponentType;
import com.framework.core.models.DeviceInfo;
import com.framework.utils.PathUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.*;

public class ExecutionProcessor {

    public ExecutionConfig executionConfig;

    public ExecutionProcessor(ExecutionConfig executionConfig) {

        this.executionConfig = executionConfig;
    }

    public String process(){

        try{
            ComponentManager componentManager = ComponentManager.getInstance();

            List<Component> componentList = new ArrayList<>();

            if(executionConfig.getComponentType().equals(ComponentType.browser)){

                int threadPoolSize = ConfigHandler.getConfigObj("browser").getInt("threads");

                for (int i = 1; i<=threadPoolSize; i++){

                    Component browserComponent = new ComplexComponent();

                    browserComponent.setFeatureList(executionConfig.getFeatureList());

                    browserComponent.setComponentType(executionConfig.getComponentType());

                    browserComponent.setConfig(ConfigHandler.getConfigObj("browser"));

                    componentList.add(browserComponent);
                }
            }
            else if(executionConfig.getComponentType().equals(ComponentType.browserStack)){

                JSONArray browsers = ConfigHandler.getConfigObj("browserStack").getJSONArray("browsers");

                for(int i=0; i<browsers.length(); i++){

                    Component browserStackComponent = new ComplexComponent();

                    JSONObject configObject = browsers.getJSONObject(i);

                    browserStackComponent.setFeatureList(executionConfig.getFeatureList());

                    browserStackComponent.setComponentType(executionConfig.getComponentType());

                    browserStackComponent.setConfig(configObject);

                    componentList.add(browserStackComponent);
                }
            }

           else if(executionConfig.getComponentType().equals(ComponentType.sauceLabs)){

                JSONArray browsers = ConfigHandler.getConfigObj("sauceLabs").getJSONArray("browsers");

                for(int i=0; i<browsers.length(); i++){

                    Component sauceLabsComponent = new ComplexComponent();

                    JSONObject configObject = browsers.getJSONObject(i);

                    sauceLabsComponent.setFeatureList(executionConfig.getFeatureList());

                    sauceLabsComponent.setComponentType(executionConfig.getComponentType());

                    sauceLabsComponent.setConfig(configObject);

                    componentList.add(sauceLabsComponent);
                }
            }

           else if(executionConfig.getComponentType().equals(ComponentType.device)){

               JSONArray devices = ConfigHandler.getConfigObj("device").getJSONArray("devices");

               for(int i=0; i<devices.length(); i++){

                   Component deviceComponent = new ComplexComponent();

                   JSONObject configObject = devices.getJSONObject(i);

                   deviceComponent.setFeatureList(executionConfig.getFeatureList());

                   deviceComponent.setComponentType(executionConfig.getComponentType());

                   deviceComponent.setConfig(getDeviceDetails(configObject));

                   componentList.add(deviceComponent);
               }
            }

            componentManager.startComponent(componentList);
        }

        catch(Exception exception){

            exception.printStackTrace();
        }

        return "";
    }

    private DeviceInfo getDeviceDetails(JSONObject configObject) throws FileNotFoundException {

        DeviceInfo deviceInfo = new DeviceInfo();

        String name = configObject.getString("name");

        YamlFileManager yamlFileManager = new YamlFileManager(PathUtils.device_path + name);

        Map<String, String> deviceProps = yamlFileManager.read();

        String platform = deviceProps.get("platform");

        deviceInfo.setDeviceName(deviceProps.get("deviceName"));

        deviceInfo.setPlatformName(platform);

        deviceInfo.setPlatformVersion(deviceProps.get("platformVersion"));

        deviceInfo.setUdId(deviceProps.get("udId"));

        deviceInfo.setDeviceType(deviceProps.get("deviceType"));

        deviceInfo.setAppName(configObject.getString("app"));

        if(platform.equalsIgnoreCase("ios")){

            deviceInfo.setBundleId(configObject.getString("bundleId"));
        }
        else{

            deviceInfo.setAppActivity(configObject.getString("appActivity"));

            deviceInfo.setAppPackage(configObject.getString("appPackage"));

            deviceInfo.setAvd(deviceProps.get("avd"));
        }

        return deviceInfo;
    }
}
