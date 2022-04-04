package com.saucelabs.services;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.saucelabs.exception.DriverNotFoundException;
import com.saucelabs.models.SauceLabsRequestModel;
import com.saucelabs.models.SauceLabsResponseModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
@JsonIgnoreProperties
public class SauceLabsService implements ISauceLabsService {

    private WebDriver webDriver;

    @Override
    public SauceLabsResponseModel post(SauceLabsRequestModel SauceLabsModel) throws DriverNotFoundException {


        SauceLabsResponseModel driverDetailsModel = new SauceLabsResponseModel();
        DesiredCapabilities capabilities = getCapabilities(SauceLabsModel);

        try {

                String userName = SauceLabsModel.username;

                String accessKey = SauceLabsModel.accesskey;

                String URL = "https://" + userName + ":" + accessKey + "@ondemand.saucelabs.com:80/wd/hub";

                webDriver = new RemoteWebDriver(new URL(URL), capabilities);

                HttpCommandExecutor executor = (HttpCommandExecutor) ((RemoteWebDriver) webDriver).getCommandExecutor();

                driverDetailsModel.setSessionId(((RemoteWebDriver) webDriver).getSessionId().toString());

                driverDetailsModel.setUrl(executor.getAddressOfRemoteServer());


        }catch(Exception ex){

            throw new DriverNotFoundException(ex.getMessage());
        }


        return driverDetailsModel;
    }


    public DesiredCapabilities getCapabilities(SauceLabsRequestModel model){



        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("name", model.browser +"-"+
                model.browser_ver);

        caps.setCapability("build", model.build);

        return caps;
    }


}
