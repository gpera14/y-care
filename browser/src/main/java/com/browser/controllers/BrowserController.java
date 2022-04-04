package com.browser.controllers;


import com.browser.exception.BrowserNotFoundException;

import com.browser.service.IBrowserService;
import com.browser.models.BrowserRequestModel;
import com.browser.models.BrowserResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/browser")
public class BrowserController {

    @Autowired
    IBrowserService browserService;
    private String browser;

    @PostMapping(path="/local")
    public ResponseEntity<BrowserResponseModel> getDriver(@RequestBody BrowserRequestModel browsermodel) throws BrowserNotFoundException {
        BrowserResponseModel model = null;

            model= browserService.post(browsermodel);

            return new ResponseEntity<>(model, HttpStatus.OK);
    }

}
