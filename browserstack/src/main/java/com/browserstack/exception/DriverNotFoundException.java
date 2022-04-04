package com.browserstack.exception;



public class DriverNotFoundException extends Exception{


    String errors;


    public DriverNotFoundException(String errors) {
        this.errors = errors;
    }

    public String getErrors() {
        return errors;
    }


}
