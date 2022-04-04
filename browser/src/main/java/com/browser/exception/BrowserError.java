package com.browser.exception;

import java.time.LocalDateTime;
import java.util.List;

public class BrowserError {

    private String errors;
    private List<String> errorDetails;

    public BrowserError(String errors, List<String> errorDetails) {

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
