package com.framework.core.execution;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutionManager {

    public static ExecutorService threadPool;

    private static ExecutionManager _executionManager = null;

    public static ExecutionManager getInstance() {

        if (ExecutionManager._executionManager == null) {

            ExecutionManager._executionManager = new ExecutionManager();
        }
        return ExecutionManager._executionManager;
    }

    public void StartFrameworkExecution(List<ExecutionConfig> executionConfigList) {
        try {

            threadPool = Executors.newFixedThreadPool(executionConfigList.size());

            for(ExecutionConfig executionConfig : executionConfigList){

                PoolExecutioner poolExecutioner = new PoolExecutioner(executionConfig);

                threadPool.submit(poolExecutioner);
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
