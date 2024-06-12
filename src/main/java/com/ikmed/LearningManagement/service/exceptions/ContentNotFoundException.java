package com.ikmed.LearningManagement.service.exceptions;

import jakarta.persistence.EntityNotFoundException;

import java.io.Serial;

public class ContentNotFoundException extends EntityNotFoundException{
    @Serial
    private static final long serialVersionUID = 1L;

    public ContentNotFoundException(String msg) {
        super(msg);
    }

    public ContentNotFoundException(String msg, Throwable cause) {
        super(msg);
        this.initCause(cause);
    }

}
