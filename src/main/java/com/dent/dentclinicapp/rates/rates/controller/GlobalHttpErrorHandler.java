package com.dent.dentclinicapp.rates.rates.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<Object> handleElementNotFoundException(ElementNotFoundException exception)
    {
        return new ResponseEntity<>("Element with given id does not exist!", HttpStatus.BAD_REQUEST);
    }
}