package com.shipping.backend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultExceptionHandler {

    private final static Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<?> handleCustomException( CustomException e){
        log.info("Server response with a null value");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
