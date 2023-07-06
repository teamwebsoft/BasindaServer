package com.basinda.exceptions;

import org.springframework.http.HttpStatus;
import com.basinda.models.response.ResponseHeader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public class Response extends ResponseHeader {

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response> resourceNotFoundException(ResourceNotFoundException ex){
        Response response = new Response();
        response.setStatusCode(HttpStatus.NOT_FOUND);
        response.setStatus(true);
        response.setContent(ex.getMessage());
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Response> userAlreadyExistException(UserAlreadyExistException ex){
        Response response = new Response();
        response.setStatusCode(HttpStatus.CONFLICT);
        response.setStatus(true);
        response.setContent(ex.getMessage());
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}