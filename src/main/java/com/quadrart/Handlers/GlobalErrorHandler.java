package com.quadrart.Handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.quadrart.Storage.StorageException;
import com.quadrart.Storage.StorageFileNotFoundException;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(StorageException.class)
    public ResponseEntity<?> handleException(StorageException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleException(StorageFileNotFoundException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleException(Exception exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
