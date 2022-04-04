package com.browserstack.services;


import com.browserstack.exception.DriverNotFoundException;
import com.browserstack.models.BrowserStackRequestModel;
import com.browserstack.models.BrowserStackResponseModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
@JsonIgnoreProperties
public class BrowserStackService implements IBrowserStackService {

    private WebDriver webDriver;

    @Override
    public BrowserStackResponseModel post(BrowserStackRequestModel BrowserStackModel) throws DriverNotFoundException {


        BrowserStackResponseModel driverDetailsModel = new BrowserStackResponseModel();
        DesiredCapabilities capabilities = getCapabilities(BrowserStackModel);

        try {

                String userName = BrowserStackModel.username;

                String accessKey = BrowserStackModel.accesskey;

                String URL = "https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";

                webDriver = new RemoteWebDriver(new URL(URL), capabilities);

                HttpCommandExecutor executor = (HttpCommandExecutor) ((RemoteWebDriver) webDriver).getCommandExecutor();

                driverDetailsModel.setSessionId(((RemoteWebDriver) webDriver).getSessionId().toString());

                driverDetailsModel.setUrl(executor.getAddressOfRemoteServer());


        }catch(Exception ex){

            throw new DriverNotFoundException(ex.getMessage());
        }


        return driverDetailsModel;
    }


    public DesiredCapabilities getCapabilities(BrowserStackRequestModel model){



        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("name", model.browser +"-"+
                model.browser_ver);

        caps.setCapability("build", model.build);

        return caps;
    }


}
