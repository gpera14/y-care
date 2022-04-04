package com.browser.models;

import org.openqa.selenium.remote.SessionId;

import java.net.URL;

public class BrowserResponseModel {

    private String sessionId;

    private URL url;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public void isEmpty(){

    }
}
