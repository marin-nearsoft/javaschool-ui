package com.nearsoft.javaschoolbackend.exception;

import com.nearsoft.javaschoolbackend.exception.custom.CentralServerException;
import com.nearsoft.javaschoolbackend.exception.custom.PackageDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.time.LocalDateTime;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CentralServerException.class, PackageDataException.class})
    public ResponseEntity<CustomErrorResponse> customHandleCentralServerError(Exception ex, WebRequest request) throws IOException {
        CustomErrorResponse res = new CustomErrorResponse();
        res.setTimestamp(LocalDateTime.now());
        res.setError("Bad Gateway");
        res.setStatus(HttpStatus.BAD_GATEWAY.value());
        res.setMessage(ex.getMessage());
        res.setPath(((ServletWebRequest)request).getRequest().getRequestURL().toString());

        return new ResponseEntity<>(res, HttpStatus.BAD_GATEWAY);
    }

}
