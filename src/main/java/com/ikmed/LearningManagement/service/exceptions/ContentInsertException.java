package com.ikmed.LearningManagement.service.exceptions;


import org.springframework.dao.DataIntegrityViolationException;

import java.io.Serial;

public class ContentInsertException extends DataIntegrityViolationException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ContentInsertException(String msg) {
        super(msg);
    }

    public ContentInsertException(String msg, Throwable cause) {
        super(msg);
        this.initCause(cause);
    }
}
