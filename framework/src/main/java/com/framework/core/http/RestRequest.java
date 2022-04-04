package com.framework.core.http;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class RestRequest {

    private String url;

    private HttpHeaders headers;

    private HttpEntity body;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public HttpEntity getBody() {
        return body;
    }

    public void setBody(HttpEntity body) {
        this.body = body;
    }
}
