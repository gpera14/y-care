package com.saucelabs.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class SauceLabsExceptionHandling {



    private static final Logger LOGGER = LoggerFactory.getLogger(SauceLabsExceptionHandling.class);


    @ExceptionHandler({
            DriverNotFoundException.class

    })

    @Nullable
    public final ResponseEntity<SauceLabsError> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        LOGGER.error("Handling " + ex.getClass().getSimpleName() + " due to " + ex.getMessage());

        if (ex instanceof DriverNotFoundException) {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            DriverNotFoundException unfe = (DriverNotFoundException) ex;

            return handleBrowserStackNotFoundException(unfe, headers, status, request);
        } else {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Unknown exception type: " + ex.getClass().getName());
            }

            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);
        }
    }


    protected ResponseEntity<SauceLabsError> handleBrowserStackNotFoundException(DriverNotFoundException ex,
                                                                                 HttpHeaders headers, HttpStatus status,
                                                                                 WebRequest request) {

        List<String> details = new ArrayList<>();
        details.add(ex.errors);
        SauceLabsError error = new SauceLabsError("Something went wrong", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);


    }



    protected ResponseEntity<SauceLabsError> handleExceptionInternal(Exception ex, @Nullable SauceLabsError body,
                                                                     HttpHeaders headers, HttpStatus status,
                                                                     WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }
}