package com.automation.appiumservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.util.ArrayList;
import java.util.List;

public class AppiumExceptionHandling {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppiumExceptionHandling.class);


    @ExceptionHandler({
            DriverNotFoundException.class

    })

    @Nullable
    public final ResponseEntity<AppiumError> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        LOGGER.error("Handling " + ex.getClass().getSimpleName() + " due to " + ex.getMessage());

        if (ex instanceof DriverNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            DriverNotFoundException unfe = (DriverNotFoundException) ex;

            return handleDriverNotFoundException(unfe, status);
        }
        else if (ex instanceof ElementNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            ElementNotFoundException unfe = (ElementNotFoundException) ex;

            return handleElementNotFoundException(unfe, status);
        }
        else if (ex instanceof BadRequestException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            BadRequestException unfe = (BadRequestException) ex;

            return handleBadRequestException(unfe, status);
        }
        else if (ex instanceof MethodNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            MethodNotFoundException unfe = (MethodNotFoundException) ex;

            return handleMethodNotFoundException(unfe, status);
        }else {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Unknown exception type: " + ex.getClass().getName());
            }

            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);
        }
    }


    protected ResponseEntity<AppiumError> handleDriverNotFoundException(DriverNotFoundException ex, HttpStatus status) {

        List<String> details = new ArrayList<>();
        details.add(ex.errors);
        AppiumError error = new AppiumError("Something went wrong", details);
        return new ResponseEntity(error, status);


    }
    protected ResponseEntity<AppiumError> handleElementNotFoundException(ElementNotFoundException ex, HttpStatus status) {

        List<String> details = new ArrayList<>();
        details.add(ex.errors);
        AppiumError error = new AppiumError("Something went wrong", details);
        return new ResponseEntity(error, status);


    }
    protected ResponseEntity<AppiumError> handleMethodNotFoundException(MethodNotFoundException ex, HttpStatus status) {

        List<String> details = new ArrayList<>();
        details.add(ex.errors);
        AppiumError error = new AppiumError("Something went wrong", details);
        return new ResponseEntity(error, status);


    }

    protected ResponseEntity<AppiumError> handleBadRequestException(BadRequestException ex, HttpStatus status) {

        List<String> details = new ArrayList<>();
        details.add(ex.errors);
        AppiumError error = new AppiumError("Something went wrong", details);
        return new ResponseEntity(error, status);


    }



    protected ResponseEntity<AppiumError> handleExceptionInternal(Exception ex, @Nullable AppiumError body,
                                                                   HttpHeaders headers, HttpStatus status,
                                                                   WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }
}
