package com.framework.core.execution;

import com.framework.core.enums.ComponentType;
import com.framework.utils.PathUtils;
import io.cucumber.core.cli.Main;

public class FeatureExecutioner {

    private String featureName;

    private ComponentType componentType;

    public FeatureExecutioner(String featureName, ComponentType componentType){

        this.featureName = featureName;

        this.componentType = componentType;
    }

    public void execute(){

        String featurePath = PathUtils.getRootPath()+ "/src/test/resources/features/" + featureName;
        //String [] args = new String[]{ "--glue", "stepdefs", "-p", "pretty", "-p", "com.epam.reportportal.cucumber.StepReporter", featurePath};
//        String [] args = new String[]{ "--glue", "stepdefs", "-p", "pretty", "-p", "com.epam.reportportal.cucumber.ScenarioReporter", featurePath};
        String [] args = new String[]{ "--glue", "stepdefs", featurePath};
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        Main.run(args, contextClassLoader);
    }

}