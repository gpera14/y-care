package com.automation.appiumservice.services;

import com.automation.appiumservice.exception.ElementNotFoundException;

import java.util.Map;

public interface ICommandService {

    String execute(Map<String, Object> request) throws Exception;
}
