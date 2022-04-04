package com.framework.core.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.framework.core.helpers.DriverHelper;
import org.json.JSONObject;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class SeleniumWebDriver implements IDriver {

    RemoteWebDriver _webDriver;

    JSONObject configObject;

    public SeleniumWebDriver(Object config) throws JsonProcessingException, MalformedURLException {

        this.configObject = (JSONObject) config;
//        _browserName = name;
//
//        if(_browserName.equalsIgnoreCase("chrome")){
//
//            System.setProperty("webdriver.chrome.driver", BrowserPath.valueOf(name).getBrowserPath());
//
//            _webDriver = new ChromeDriver();
//        }
//
//        _webDriver.manage().window().maximize();
//
//        _webDriver.navigate().to(ConfigHandler.getConfigObj("browser").getString("url"));

        HttpHeaders headers = new HttpHeaders();

        String uri = "http://localhost:8080/browser/local";

        JSONObject requestJson = new JSONObject();

        requestJson.put("name", configObject.getString("name"));

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson.toString(),headers);

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(driverOctet());

        ObjectNode driverDetails = restTemplate.postForObject(uri, entity, ObjectNode.class);//restTemplate.getForObject(uri, ObjectNode.class);

        ObjectMapper mapper = new ObjectMapper();

        JSONObject responseObject = new JSONObject(mapper.writeValueAsString(driverDetails));

        String sessionId = responseObject.getString("sessionId");

        URL remoteUrl = new URL(responseObject.getString("url"));

        _webDriver = DriverHelper.createDriverFromSession(sessionId, remoteUrl);

        _webDriver.manage().window().maximize();

        _webDriver.navigate().to(configObject.getString("url"));
    }

    @Override
    public Object Get() {

        return _webDriver;
    }

    @Override
    public void Close() {

        if(_webDriver != null){

            _webDriver.close();

            System.gc();
        }
    }

    private static HttpMessageConverter driverOctet(){

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));

        return converter;
    }
}
