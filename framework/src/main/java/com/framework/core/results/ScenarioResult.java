package com.framework.core.results;

import java.util.List;

public class ScenarioResult {

    private String name;

    private List<StepResult> scenarioResult;

    public List<StepResult> getResults() {
        return scenarioResult;
    }

    public void setResults(List<StepResult> results) {
        this.scenarioResult = results;
    }

    public void addResult(StepResult result){

        scenarioResult.add(result);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
