package com.cinemayan.audit.domain.exception;

import lombok.Getter;

@Getter
public class AuditException extends RuntimeException {

    private final AuditErrorCode errorCode;

    protected AuditException (String message, AuditErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    protected AuditException (String message, Throwable cause, AuditErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
