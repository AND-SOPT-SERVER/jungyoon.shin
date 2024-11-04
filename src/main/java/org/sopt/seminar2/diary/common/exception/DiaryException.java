package org.sopt.seminar2.diary.common.exception;

import org.sopt.seminar2.diary.common.code.FailureCode;

public class DiaryException extends ApiException {

    private final FailureCode failureCode;

    public FailureCode getFailureCode() {
        return failureCode;
    }

    public DiaryException(FailureCode failureCode) {
        super(failureCode, "[DiaryException]");
        this.failureCode = failureCode;
    }

}
