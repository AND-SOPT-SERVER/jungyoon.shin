package org.sopt.seminar2.diary.common.code;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum FailureCode {

    INVALID_VALUE(HttpStatus.BAD_REQUEST, "잘못된 값입니다."),
    MISSING_PARAMETER(HttpStatus.BAD_REQUEST, "필수 파라미터가 없습니다.");


    private final HttpStatus httpStatus;
    private final String message;

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
