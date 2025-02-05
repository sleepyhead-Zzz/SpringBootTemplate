package com.springboottemplate.common.utils.jackson;

/**
 * @author Sleepyhead
 */
public class JacksonException extends RuntimeException {

    public JacksonException(String message, Exception e) {
        super(message, e);
    }

}
