package com.automation.appiumservice.exception;

public class BadRequestException  extends Exception {


    String errors;


    public BadRequestException(String errors) {
        this.errors = errors;
    }

    public String getErrors() {
        return errors;
    }
}
