package com.automation.appiumservice.controllers;

import com.automation.appiumservice.exception.DriverNotFoundException;
import com.automation.appiumservice.exception.ElementNotFoundException;
import com.automation.appiumservice.models.AppiumRequestModel;
import com.automation.appiumservice.models.AppiumResponseModel;
import com.automation.appiumservice.services.IAppiumService;
import com.automation.appiumservice.services.ICommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class AppiumController {

    @Autowired
    private IAppiumService appiumService;

    @Autowired
    private ICommandService commandService;

    @PostMapping(path = "/mobile",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public AppiumResponseModel getDriver(@RequestPart("data") @Valid AppiumRequestModel requestModel,
                                         @RequestParam("file") MultipartFile multipartFile) throws DriverNotFoundException{

        AppiumResponseModel model = appiumService.post(requestModel, multipartFile);

        return model;
    }

    @PostMapping(path = "/driver/execute",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String executeCommands(@RequestBody Map<String, Object> payload) throws Exception {

       String result= commandService.execute(payload);

        return result;
    }
}
