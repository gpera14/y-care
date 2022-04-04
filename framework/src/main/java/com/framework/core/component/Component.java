package com.framework.core.component;

import java.util.List;
import java.util.Locale;

import com.framework.configurations.ConfigHandler;
import com.framework.core.enums.ComponentType;
import com.framework.core.models.Feature;

public abstract class Component {

    protected List<Feature> featureList;

    protected ComponentType componentType;

    protected Object config;

    public String process(){

        try{

            load();

            execute();

            close();

        }
        catch(Exception exception){

            exception.printStackTrace();
        }

        return "";
    }

    public void load() {
    }

    public void execute() { }

    public void close() { }

    public void setFeatureList(List<Feature> featureList) {
        this.featureList = featureList;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public void setConfig(Object config) {
        this.config = config;
    }

    public void setDashboardInfo() {
            System.setProperty("rp.attributes", "BROWSER:" + ConfigHandler.getConfigObj("browser").getString("name").toLowerCase(Locale.ROOT));
    }
}
