package com.oracle.app.eventticketsapp.exceptions;

/**
 * @author {ANAS DR}
 **/
public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(String message) {
        super(message);
    }
}
