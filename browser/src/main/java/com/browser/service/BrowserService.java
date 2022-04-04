package com.browser.service;

import com.browser.exception.BrowserNotFoundException;
import com.browser.models.BrowserRequestModel;
import com.browser.models.BrowserResponseModel;
import com.browser.enums.BrowserPath;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.springframework.stereotype.Service;

@Service @JsonIgnoreProperties
public class BrowserService implements IBrowserService {

    private WebDriver webDriver;

    @Override
    public BrowserResponseModel post(BrowserRequestModel browserModel) throws BrowserNotFoundException {

        BrowserResponseModel driverDetailsModel = new BrowserResponseModel();

        try {

            if (browserModel.name.equalsIgnoreCase("chrome")) {

                System.setProperty("webdriver.chrome.driver", BrowserPath.valueOf(browserModel.name).getBrowserPath());

                DesiredCapabilities capabilities = DesiredCapabilities.chrome();

                capabilities.setCapability(CapabilityType.SUPPORTS_NETWORK_CONNECTION, true);

                webDriver = new ChromeDriver(capabilities);

                HttpCommandExecutor executor = (HttpCommandExecutor) ((ChromeDriver) webDriver).getCommandExecutor();

                driverDetailsModel.setSessionId(((ChromeDriver) webDriver).getSessionId().toString());

                driverDetailsModel.setUrl(executor.getAddressOfRemoteServer());
                ;
            } else if (browserModel.name.equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.firefox.driver", BrowserPath.valueOf(browserModel.name).getBrowserPath());

                DesiredCapabilities capabilities = DesiredCapabilities.firefox();

                capabilities.setCapability(CapabilityType.SUPPORTS_NETWORK_CONNECTION, true);

                webDriver = new FirefoxDriver(capabilities);

                HttpCommandExecutor executor = (HttpCommandExecutor) ((FirefoxDriver) webDriver).getCommandExecutor();

                driverDetailsModel.setSessionId(((ChromeDriver) webDriver).getSessionId().toString());

                driverDetailsModel.setUrl(executor.getAddressOfRemoteServer());
                ;

            }
            else
            {
                throw new BrowserNotFoundException("Parameter name not Correct");
            }
        }catch(Exception ex){

            throw new BrowserNotFoundException(ex.getMessage());
        }


        return driverDetailsModel;
    }
}
