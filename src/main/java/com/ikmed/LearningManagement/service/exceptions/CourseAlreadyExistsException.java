package com.ikmed.LearningManagement.service.exceptions;


import org.springframework.dao.DataIntegrityViolationException;

import java.io.Serial;

public class CourseAlreadyExistsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public CourseAlreadyExistsException(String msg) {
        super(msg);
    }

    public CourseAlreadyExistsException(String msg, Throwable cause) {
        super(msg);
        this.initCause(cause);
    }
}
