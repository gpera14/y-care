package com.automation.appiumservice.exception;

public class MethodNotFoundException extends Exception {


    String errors;


    public MethodNotFoundException(String errors) {
        this.errors = errors;
    }

    public String getErrors() {
        return errors;
    }
}
