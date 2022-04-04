package com.framework.core.http;

import org.json.JSONObject;

public class RestResponse {

    private JSONObject content;

    private int statusCode;


    public JSONObject getContent() {
        return content;
    }

    public void setContent(JSONObject content) {
        this.content = content;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
