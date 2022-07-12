package com.zyppysselfhelp.exception;

/**
 * This is the exception thrown when one is not authorized to access a resource.
 */
public class UnauthorizedException extends Exception {

    private static final long serialVersionUID = 1L;

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
