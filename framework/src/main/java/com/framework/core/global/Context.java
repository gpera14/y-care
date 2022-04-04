package com.framework.core.global;

import com.framework.core.results.FeatureResult;
import com.framework.core.results.StepResult;
import com.framework.core.results.ScenarioResult;
import io.cucumber.java.Scenario;

public class Context {

    public String stepName;

    public String scenarioName;

    public String featureName;

    public StepResult result;

    public Scenario scenario;

    public ScenarioResult scenarioResult;

    public FeatureResult featureResult;
}
