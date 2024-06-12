package com.ikmed.LearningManagement.service.exceptions;


import org.springframework.dao.DataIntegrityViolationException;

import java.io.Serial;

public class ModuleInsertException  extends DataIntegrityViolationException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ModuleInsertException(String msg) {
        super(msg);
    }

    public ModuleInsertException(String msg, Throwable cause) {
        super(msg);
        this.initCause(cause);
    }
}
