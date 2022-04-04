package com.automation.appiumservice.exception;

public class ElementNotFoundException extends Exception {


    String errors;


    public ElementNotFoundException(String errors) {
        this.errors = errors;
    }

    public String getErrors() {
        return errors;
    }
}
