package com.framework.core.enums;

import com.framework.utils.PathUtils;

public enum BrowserPath {

    chrome,
    firefox,
    ie,
    safari;


    public String getBrowserPath(){

        if(name().equalsIgnoreCase(chrome.toString())){

            return PathUtils.getRootPath() + PathUtils.resourcesPath + "/browsers/chromedriver";
        }
        return "";
    }

}
