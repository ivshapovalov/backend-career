package ru.intelinvest.career.exception;


public class RemoteApiException extends RuntimeException {
    public RemoteApiException(String message) {
        super(message);
    }
}
