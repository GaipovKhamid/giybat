package com.khamidgaipov.api.giybat.uz.controller;

import com.khamidgaipov.api.giybat.uz.exception.BadReqException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(BadReqException.class)
    public ResponseEntity<String> handle(BadReqException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
