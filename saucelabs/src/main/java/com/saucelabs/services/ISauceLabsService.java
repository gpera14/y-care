package com.saucelabs.services;


import com.saucelabs.exception.DriverNotFoundException;
import com.saucelabs.models.SauceLabsRequestModel;
import com.saucelabs.models.SauceLabsResponseModel;

public interface ISauceLabsService {

    SauceLabsResponseModel post(SauceLabsRequestModel browserModel) throws DriverNotFoundException;
}
