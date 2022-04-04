package com.browserstack.services;

import com.browserstack.exception.DriverNotFoundException;
import com.browserstack.models.BrowserStackRequestModel;
import com.browserstack.models.BrowserStackResponseModel;

public interface IBrowserStackService {

    BrowserStackResponseModel post(BrowserStackRequestModel browserModel) throws DriverNotFoundException;
}
