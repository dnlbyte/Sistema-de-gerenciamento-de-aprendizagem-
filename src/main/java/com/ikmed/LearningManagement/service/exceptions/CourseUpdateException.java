package com.ikmed.LearningManagement.service.exceptions;


import org.springframework.dao.DataIntegrityViolationException;

import java.io.Serial;

public class CourseUpdateException extends DataIntegrityViolationException {
    @Serial
    private static final long serialVersionUID = 1L;

    public CourseUpdateException(String msg) {
        super(msg);
    }

    public CourseUpdateException(String msg, Throwable cause) {
        super(msg);
        this.initCause(cause);
    }
}
