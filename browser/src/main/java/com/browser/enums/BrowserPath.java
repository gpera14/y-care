package com.browser.enums;

import com.browser.utils.PathUtils;

public enum BrowserPath {

    chrome,
    firefox,
    ie,
    safari;


    public String getBrowserPath(){

        if(name().equalsIgnoreCase(chrome.toString())){

            return PathUtils.browserModule + PathUtils.resourcesPath + "/browsers/chromedriver";
            //return PathUtils.getRootPath() + PathUtils.resourcesPath + "/browsers/chromedriver";
        }
        else if(name().equalsIgnoreCase(firefox.toString())){

            return PathUtils.browserModule + PathUtils.resourcesPath + "/browsers/firefoxdriver";
            //return PathUtils.getRootPath() + PathUtils.resourcesPath + "/browsers/chromedriver";
        }
        return "";
    }

}
