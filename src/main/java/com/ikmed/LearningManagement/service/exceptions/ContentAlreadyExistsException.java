package com.ikmed.LearningManagement.service.exceptions;


import java.io.Serial;

public class ContentAlreadyExistsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ContentAlreadyExistsException(String msg) {
        super(msg);
    }

    public ContentAlreadyExistsException(String msg, Throwable cause) {
        super(msg);
        this.initCause(cause);
    }
}
