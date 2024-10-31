package org.sopt.seminar2.diary.common.exception;

import org.sopt.seminar2.diary.common.code.FailureCode;

public class ApiException extends RuntimeException {

    private final FailureCode failureCode;
    private final String errorMessage;

    public FailureCode getFailureCode() {
        return failureCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ApiException(FailureCode failureCode, String errorMessage) {
        super(errorMessage + ": " + failureCode.getMessage());
        this.failureCode = failureCode;
        this.errorMessage = errorMessage;
    }
}
