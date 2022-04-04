package com.framework.core.component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ComponentManager {

    public static ExecutorService threadPool; //= Executors.newFixedThreadPool(1);

    private static ComponentManager _componentManager = null;



    public static ComponentManager getInstance() {

        if (ComponentManager._componentManager == null) {

            ComponentManager._componentManager = new ComponentManager();
        }
        return ComponentManager._componentManager;
    }

    public void startComponent(List<Component> componentList){

        try {

            threadPool = Executors.newFixedThreadPool(componentList.size());

            for (Component component : componentList){

                ComponentExecutioner componentExecutioner = new ComponentExecutioner(component);

                threadPool.submit(componentExecutioner);

            }
            threadPool.shutdown();
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
//            for(Device device : devicesList){
//
//                DeviceExecutioner deviceExecutioner = new DeviceExecutioner(device);
//
//                threadPool.submit(deviceExecutioner);
//

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }
}
