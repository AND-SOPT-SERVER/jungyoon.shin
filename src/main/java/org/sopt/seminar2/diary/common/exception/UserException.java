package org.sopt.seminar2.diary.common.exception;

import org.sopt.seminar2.diary.common.code.FailureCode;

public class UserException extends ApiException {

    private final FailureCode failureCode;

    public FailureCode getFailureCode() {
        return failureCode;
    }

    public UserException(FailureCode failureCode) {
        super(failureCode, "[UserException]");
        this.failureCode = failureCode;
    }
}
