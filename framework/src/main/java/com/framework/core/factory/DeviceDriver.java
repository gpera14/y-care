package com.framework.core.factory;

import com.framework.core.driver.CustomizeDriver;
import com.framework.core.helpers.DriverHelper;
import com.framework.core.managers.TestCaseData;
import com.framework.core.models.DeviceInfo;
import com.framework.utils.PathUtils;
import org.json.JSONObject;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.framework.core.factory.IDriver;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class DeviceDriver implements IDriver {

    CustomizeDriver _driver;

    RemoteWebDriver _remoteDriver;

    DeviceInfo configObject;

    public DeviceDriver(Object config) throws IOException {

        HttpEntity<ByteArrayResource> attachmentPart;

        String uri = "http://localhost:8081/mobile";

        configObject = (DeviceInfo) config;

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(driverOctet());

        MultiValueMap<String, Object> multipartRequest = new LinkedMultiValueMap<>();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        Path file = Paths.get(PathUtils.appPath + "/" + configObject.getAppName());

        ByteArrayResource fileAsResource = new ByteArrayResource(Files.readAllBytes(file)){
            @Override
            public String getFilename(){
                return file.getFileName().toString();
            }
        };
        attachmentPart = new HttpEntity<>(fileAsResource, requestHeaders);

        multipartRequest.set("file",attachmentPart);

        JSONObject body = new JSONObject();

        body.put("deviceName", configObject.getDeviceName());

        body.put("platformName", configObject.getPlatformName());

        body.put("platformVersion", configObject.getPlatformVersion());

//        "com.safeway.client.android.tomthumb.debug"
        body.put("appPackage", configObject.getAppPackage());
//        "com.safeway.mcommerce.android.SplashScreen")
        body.put("appActivity", configObject.getAppActivity());

        body.put("deviceType", "local");
        HttpHeaders requestHeadersJSON = new HttpHeaders();
        requestHeadersJSON.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntityJson = new HttpEntity<>(body.toString(), requestHeadersJSON);

        multipartRequest.set("data", requestEntityJson);

        HttpEntity<MultiValueMap<String,Object>> requestEntity = new HttpEntity<>(multipartRequest);

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST,requestEntity,String.class);

        JSONObject responseObj = new JSONObject(response.getBody());

        String sessionId = responseObj.getString("sessionId");

        URL remoteUrl = new URL(responseObj.getString("url"));

        _driver = new CustomizeDriver(remoteUrl.toString(), sessionId);

        _remoteDriver = DriverHelper.createDriverFromSession(sessionId, remoteUrl);

        _driver.setRemoteWebDriver(_remoteDriver);
    }

    @Override
    public Object Get() {
        return _driver;
    }

    @Override
    public void Close() {
        _driver.quit();
    }

    private static HttpMessageConverter driverOctet(){

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));

        return converter;
    }
}
