package com.saucelabs.models;

import java.net.URL;

public class SauceLabsResponseModel {

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

    public void isEmpty(){}
}
