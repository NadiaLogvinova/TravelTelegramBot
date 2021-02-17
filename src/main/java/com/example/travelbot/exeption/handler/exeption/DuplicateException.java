package com.example.travelbot.exeption.handler.exeption;

/**
 * Throws, if some given resource (in my case it is a city) already exists.
 *
 * @author n.logvinova
 */
public class DuplicateException extends RuntimeException {

    public DuplicateException(String str) {
        super(str);
    }
}
