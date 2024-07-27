package ru.intelinvest.career.exception;

import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log
class ExceptionHandlerAdvice {
    @ExceptionHandler({RemoteApiException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String handleRemoteApiExceptionHandler(RemoteApiException ex) {
        return ex.getMessage();
    }
}
