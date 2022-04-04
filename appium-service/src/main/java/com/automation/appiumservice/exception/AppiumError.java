package com.automation.appiumservice.exception;

import java.util.List;

public class AppiumError {
    private String errors;
    private List<String> errorDetails;

    public AppiumError(String errors, List<String> errorDetails) {

        this.errors = errors;
        this.errorDetails=errorDetails;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public List<String> getDetails(){
        return errorDetails;
    }
    public void setErrorDetails(List<String> errors) {
        this.errorDetails = errorDetails;
    }
}
