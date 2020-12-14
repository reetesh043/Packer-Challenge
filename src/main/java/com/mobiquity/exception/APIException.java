package com.mobiquity.exception;

/**
 * @author Reetesh Kumar
 * APIException - Customized Exception Class To Handle and Throw Runtime exceptions.
 */
public class APIException extends Exception {

    /**
     * APIException to handle  runtime exceptions
     *
     * @param message - the exception message
     * @param e       - The exception to be handled
     */
    public APIException(String message, Exception e) {
        super(message, e);
    }

    /**
     * APIException to handle  runtime exceptions
     *
     * @param message - the exception message
     */
    public APIException(String message) {
        super(message);
    }
}