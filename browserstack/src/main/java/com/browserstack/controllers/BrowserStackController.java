package com.browserstack.controllers;

import com.browserstack.exception.DriverNotFoundException;
import com.browserstack.models.BrowserStackRequestModel;
import com.browserstack.models.BrowserStackResponseModel;
import com.browserstack.services.IBrowserStackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/browser")
public class BrowserStackController {

    @Autowired
    IBrowserStackService browserStackService;
    private String browser;

    @PostMapping(path="/browserstack")
    public ResponseEntity<BrowserStackResponseModel> getDriver(@RequestBody BrowserStackRequestModel browsermodel) throws DriverNotFoundException {
        BrowserStackResponseModel model = null;

        model= browserStackService.post(browsermodel);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

}
