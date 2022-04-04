package com.framework.core.factory;


import com.framework.core.enums.ComponentType;

public class ComponentFactory {

    public static IDriver loadProcess(ComponentType componentType, Object configObject){

        IDriver componentDriver = null;

        try{

            if(componentType == ComponentType.browser){

                componentDriver = new SeleniumWebDriver(configObject);
            }
            else if(componentType == ComponentType.device){

                componentDriver = new DeviceDriver(configObject);
            }
//            else if(componentType == ComponentType.sauceLabs){
//
//                componentDriver = new SauceLabsDriver(configObject);
//            }
//            else if(componentType == ComponentType.browserStack){
//
//                componentDriver = new BrowserStackDriver(configObject);
//            }
        }
        catch (Exception exception){

            exception.printStackTrace();
        }
        return componentDriver;
    }
}
