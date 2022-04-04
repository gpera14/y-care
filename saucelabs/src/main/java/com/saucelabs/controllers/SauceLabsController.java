package com.saucelabs.controllers;


import com.saucelabs.exception.DriverNotFoundException;
import com.saucelabs.models.SauceLabsRequestModel;
import com.saucelabs.models.SauceLabsResponseModel;
import com.saucelabs.services.ISauceLabsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/browser")
public class SauceLabsController {

    @Autowired
    ISauceLabsService SauceLabsService;
    private String browser;

    @PostMapping(path="/SauceLabs")
    public ResponseEntity<SauceLabsResponseModel> getDriver(@RequestBody SauceLabsRequestModel browsermodel) throws DriverNotFoundException {
        SauceLabsResponseModel model = null;

        model= SauceLabsService.post(browsermodel);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

}
