package com.example.travelbot.exeption.handler.exeption;

/**
 * Throws, if some given resource (in my case it is a city) is not found.
 *
 * @author n.logvinova
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String str) {
        super(str);
    }
}
