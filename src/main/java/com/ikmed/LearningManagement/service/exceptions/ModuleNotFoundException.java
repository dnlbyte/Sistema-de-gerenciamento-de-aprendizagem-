package com.ikmed.LearningManagement.service.exceptions;

import jakarta.persistence.EntityNotFoundException;

import java.io.Serial;

public class ModuleNotFoundException  extends EntityNotFoundException{
    @Serial
    private static final long serialVersionUID = 1L;

    public ModuleNotFoundException(String msg) {
        super(msg);
    }

    public ModuleNotFoundException(String msg, Throwable cause) {
        super(msg);
        this.initCause(cause);
    }

}
