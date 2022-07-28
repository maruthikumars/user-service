package com.broadcom.user.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class UserExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserExceptionHandler.class);

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> userNotFoundExceptionHandling(Exception exception, WebRequest request) {
        logger.error("User not found {}", exception.getLocalizedMessage());
        return new ResponseEntity<>(new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false)), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(RuntimeException exception, WebRequest request) {
        logger.error("UncaughtException {}", exception.getLocalizedMessage());
        return new ResponseEntity<>(new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
