package com.framework.core.execution;

import com.framework.core.enums.ComponentType;
import com.framework.core.models.Feature;

import java.util.List;

public class ExecutionConfig {

    private List<Feature> featureList;

    private ComponentType componentType;

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public List<Feature> getFeatureList() {
        return featureList;
    }

    public void setFeatureList(List<Feature> featureList) {
        this.featureList = featureList;
    }

}
