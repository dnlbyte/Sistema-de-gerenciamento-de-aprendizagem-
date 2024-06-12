package com.ikmed.LearningManagement.service.exceptions;


import org.springframework.dao.DataIntegrityViolationException;

import java.io.Serial;

public class CourseInsertException extends DataIntegrityViolationException {
    @Serial
    private static final long serialVersionUID = 1L;

    public CourseInsertException(String msg) {
        super(msg);
    }

    public CourseInsertException(String msg, Throwable cause) {
        super(msg);
        this.initCause(cause);
    }
}
