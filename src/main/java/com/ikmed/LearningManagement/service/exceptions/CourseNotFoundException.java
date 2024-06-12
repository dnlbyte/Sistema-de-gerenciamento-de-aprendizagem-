package com.ikmed.LearningManagement.service.exceptions;

import jakarta.persistence.EntityNotFoundException;

import java.io.Serial;

public class CourseNotFoundException extends EntityNotFoundException{
    @Serial
    private static final long serialVersionUID = 1L;

    public CourseNotFoundException(String msg) {
        super(msg);
    }

    public CourseNotFoundException(String msg, Throwable cause) {
        super(msg);
        this.initCause(cause);
    }

}
