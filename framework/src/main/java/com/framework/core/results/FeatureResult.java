package com.framework.core.results;

import java.util.List;

public class FeatureResult {

    private String name;

    private List<ScenarioResult> result;

    public List<ScenarioResult> getResult() {
        return result;
    }

    public void setResult(List<ScenarioResult> result) {
        this.result = result;
    }

    public void addResult(ScenarioResult scenarioResult){

        result.add(scenarioResult);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
