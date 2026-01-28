package com.flipkart.exceptions;

/**
 * Exception thrown when user is invalid
 * 
 * @author gamma-group
 */
public class UserInvalidException extends Exception {
    public UserInvalidException(String message) {
        super(message);
    }
}