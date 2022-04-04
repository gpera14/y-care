package com.framework.core.component;

import java.util.concurrent.Callable;

public class ComponentExecutioner implements Callable<String> {

    private Component component;

    public ComponentExecutioner(Component component){

        this.component = component;

    }

    @Override
    public String call() throws Exception {

        component.process();

        return "";
    }
}
