package com.browser.exception;



public class BrowserNotFoundException extends Exception{


    String errors;


    public BrowserNotFoundException(String errors) {
        this.errors = errors;
    }

    public String getErrors() {
        return errors;
    }


}
