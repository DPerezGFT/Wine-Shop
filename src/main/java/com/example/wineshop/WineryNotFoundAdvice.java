package com.example.wineshop;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class WineryNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(WineryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String wineryNotFoundHandler(WineryNotFoundException ex) {
        return ex.getMessage();
    }
}