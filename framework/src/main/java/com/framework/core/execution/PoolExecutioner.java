package com.framework.core.execution;

public class PoolExecutioner extends Thread {

    private ExecutionConfig executionConfig;

    public PoolExecutioner(ExecutionConfig executionConfig){

        this.executionConfig = executionConfig;
    }

    @Override
    public void run() {

        ExecutionProcessor executionProcessor = new ExecutionProcessor(executionConfig);

        executionProcessor.process();
    }
}
