package com.framework.core.driver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.core.http.AppiumUrlConstants;
import com.framework.core.http.HttpHandler;
import com.framework.core.http.RestRequest;
import com.framework.core.http.RestResponse;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.DataInput;
import java.util.HashMap;

public class CustomizeDriver {

    private String url;

    private String sessionId;

    private RemoteWebDriver remoteWebDriver;

    public CustomizeDriver(String url, String sessionId){

        this.url = url;

        this.sessionId = sessionId;

        //super(url, sessionId);
    }

    public HashMap<String, Object> getSessionDetails(){

        RestRequest request = new RestRequest();
        HashMap<String, Object> result = null;
        try {
            String uri = this.url + "/session/" + this.sessionId;
            HttpHeaders requestHeaders = new HttpHeaders();
            HttpEntity entity = new HttpEntity<>(requestHeaders);
            request.setUrl(uri);
            request.setBody(entity);
            RestResponse response = HttpHandler.get(request);

            System.out.println(response.getContent().toString());

            result = new ObjectMapper().readValue(response.getContent().getJSONObject("value").toString(), HashMap.class);
        }
        catch (Exception e){

            e.printStackTrace();
        }

        return result;
    }

    public void launchApp(){

        RestRequest request = new RestRequest();

        String uri = this.url +  "/session/" + this.sessionId + "/appium/app/launch";
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(requestHeaders);
        request.setUrl(uri);
        request.setBody(entity);
        RestResponse response = HttpHandler.post(request);

        System.out.println(response.getContent().toString());
    }

    public void closeApp(){

        RestRequest request = new RestRequest();

        String uri = this.url +  "/session/" + this.sessionId + "/appium/app/close";
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(requestHeaders);
        request.setUrl(uri);
        request.setBody(entity);
        RestResponse response = HttpHandler.post(request);

        System.out.println(response.getContent().toString());
    }

    public void quit() {

        RestRequest request = new RestRequest();

        String uri = this.url + AppiumUrlConstants.session + "/" + this.sessionId;

        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(requestHeaders);
        request.setUrl(uri);
        request.setBody(entity);
        RestResponse response = HttpHandler.delete(request);

        System.out.println(response.getContent().toString());
    }

    // Element api calls

    public String findElement(String locatorType, String value) {
        try {
            RestRequest request = new RestRequest();

            String uri = this.url + AppiumUrlConstants.session + "/" + this.sessionId + AppiumUrlConstants.element;

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            JSONObject body = new JSONObject();
            body.put("using", locatorType);
            body.put("value", value);
            HttpEntity<String> entity = new HttpEntity<>(body.toString(), requestHeaders);
            request.setUrl(uri);
            request.setBody(entity);
            RestResponse response = HttpHandler.post(request);
            return response.getContent().getJSONObject("value").getString("ELEMENT");
        }
        catch(Exception e){

            throw e;
        }
    }

    // Interactions

    public void moveTo(MobileElement element){

        RestRequest request = new RestRequest();

        String uri = this.url + AppiumUrlConstants.session + "/" + this.sessionId + AppiumUrlConstants.element;

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        JSONObject body = new JSONObject();

        body.put("element", element.getId());
        body.put("xoffset", element.getCenter().getX());
        body.put("yoffset", element.getCenter().getY());
        HttpEntity entity = new HttpEntity<>(body.toString(), requestHeaders);
        request.setUrl(uri);
        request.setBody(entity);
        RestResponse response = HttpHandler.post(request);

        System.out.println(response.getContent().toString());
    }

    public void click(MobileElement element) {

        try {
            //this.moveTo(element);
            RestRequest request = new RestRequest();

            String uri = this.url + AppiumUrlConstants.session + "/" + this.sessionId + AppiumUrlConstants.element
                    + "/" + element.getId() + AppiumUrlConstants.click;

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            JSONObject body = new JSONObject();

//            body.put("button", 0);
            HttpEntity entity = new HttpEntity<>(body.toString(), requestHeaders);
            request.setUrl(uri);
            request.setBody(entity);
            RestResponse response = HttpHandler.post(request);

            System.out.println(response.getContent().toString());
        }
        catch (Exception e){

            if(e instanceof NoSuchElementException){

                throw e;
            }
        }
    }

    public void sendKeys(MobileElement element, String text){
        try {
            RestRequest request = new RestRequest();

            String uri = this.url + AppiumUrlConstants.session + "/" + this.sessionId + AppiumUrlConstants.element + "/" +
                    element.getId() + AppiumUrlConstants.value;

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            JSONObject body = new JSONObject();

            body.put("value", text.split(""));
            body.put("text", text);
            HttpEntity entity = new HttpEntity<>(body.toString(), requestHeaders);
            request.setUrl(uri);
            request.setBody(entity);
            RestResponse response = HttpHandler.post(request);

            System.out.println(response.getContent().toString());
        }
        catch (Exception e){

            if(e instanceof NoSuchElementException){

                throw e;
            }
        }
    }

    public void clear(MobileElement element) {
        try {
            RestRequest request = new RestRequest();

            String uri = this.url + AppiumUrlConstants.session + "/" + this.sessionId + AppiumUrlConstants.element + "/" +
                    element.getId() + AppiumUrlConstants.clear;

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            JSONObject body = new JSONObject();
            HttpEntity entity = new HttpEntity<>(body.toString(), requestHeaders);
            request.setUrl(uri);
            request.setBody(entity);
            RestResponse response = HttpHandler.post(request);
            System.out.println(response.getContent().toString());
        } catch (Exception e) {

            if (e instanceof NoSuchElementException) {

                throw e;
            }
        }
    }

    public boolean isDisplayed(MobileElement element){

        RestResponse response = null;
        try{
            RestRequest request = new RestRequest();

            String uri = this.url + AppiumUrlConstants.session + "/" + this.sessionId + AppiumUrlConstants.element + "/" +
                    element.getId() + AppiumUrlConstants.displayed;

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            JSONObject body = new JSONObject();
            HttpEntity entity = new HttpEntity<>(body.toString(), requestHeaders);
            request.setUrl(uri);
            request.setBody(entity);
            response = HttpHandler.post(request);
            System.out.println(response.getContent().toString());
        }
        catch(Exception e){

            throw e;
        }
        return Boolean.valueOf(response.getContent().getJSONObject("value").toString());
    }

    public void pressKeyCode(AndroidKey key){

        RestResponse response = null;
        try{
            RestRequest request = new RestRequest();

            String uri = this.url + AppiumUrlConstants.session + "/" + this.sessionId + AppiumUrlConstants.appium +
                    AppiumUrlConstants.device + AppiumUrlConstants.pressKeyCode;

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            JSONObject body = new JSONObject();
            body.put("keycode", key.getCode());
            body.put("metastate", key.getCode());
            body.put("flags", key.getCode());
            HttpEntity entity = new HttpEntity<>(body.toString(), requestHeaders);
            request.setUrl(uri);
            request.setBody(entity);
            response = HttpHandler.post(request);
            System.out.println(response.getContent().toString());
        }
        catch(Exception e){

            throw e;
        }
    }

    public void hideKeyboard(){

        RestResponse response = null;
        try{
            RestRequest request = new RestRequest();

            String uri = this.url + AppiumUrlConstants.session + "/" + this.sessionId + AppiumUrlConstants.appium +
                    AppiumUrlConstants.device + AppiumUrlConstants.hideKeyBoard;

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            JSONObject body = new JSONObject();
            body.put("strategy", "default");
            HttpEntity entity = new HttpEntity<>(body.toString(), requestHeaders);
            request.setUrl(uri);
            request.setBody(entity);
            response = HttpHandler.post(request);
            System.out.println(response.getContent().toString());
        }
        catch(Exception e){

            throw e;
        }
    }
    public void setContext(String text){
        try {
            RestRequest request = new RestRequest();

            String uri = this.url + AppiumUrlConstants.session + "/" + this.sessionId + "/context";

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            JSONObject body = new JSONObject();

            body.put("value", text.split(""));
            body.put("text", text);
            HttpEntity entity = new HttpEntity<>(body.toString(), requestHeaders);
            request.setUrl(uri);
            request.setBody(entity);
            RestResponse response = HttpHandler.post(request);

            System.out.println(response.getContent().toString());
        }
        catch (Exception e){

            if(e instanceof NoSuchElementException){

                throw e;
            }
        }

    }
    public String getContext(){
        RestRequest request = new RestRequest();
        String result=null;
        try {


            String uri = this.url + AppiumUrlConstants.session + "/" + this.sessionId + "/context";

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            JSONObject body = new JSONObject();
            HttpEntity entity = new HttpEntity<>(body.toString(), requestHeaders);
            request.setUrl(uri);
            request.setBody(entity);
            RestResponse response = HttpHandler.post(request);
            result=response.getContent().getJSONObject("value").getString("ELEMENT");

        }
        catch (Exception e){

            if(e instanceof NoSuchElementException){

                throw e;
            }
        }

    return result;
    }

    public RemoteWebDriver getRemoteWebDriver() {
        return remoteWebDriver;
    }

    public void setRemoteWebDriver(RemoteWebDriver remoteWebDriver) {
        this.remoteWebDriver = remoteWebDriver;
    }
}
