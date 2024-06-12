package com.ikmed.LearningManagement.service.exceptions;


import org.springframework.dao.DataIntegrityViolationException;

import java.io.Serial;

public class ModuleUpdateException extends DataIntegrityViolationException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ModuleUpdateException(String msg) {
        super(msg);
    }

    public ModuleUpdateException(String msg, Throwable cause) {
        super(msg);
        this.initCause(cause);
    }
}
