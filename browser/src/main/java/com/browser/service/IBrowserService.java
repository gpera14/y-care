package com.browser.service;

import com.browser.exception.BrowserNotFoundException;
import com.browser.models.BrowserRequestModel;
import com.browser.models.BrowserResponseModel;

public interface IBrowserService {


    BrowserResponseModel post(BrowserRequestModel browserModel) throws BrowserNotFoundException;
}
