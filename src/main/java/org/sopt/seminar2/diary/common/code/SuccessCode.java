package org.sopt.seminar2.diary.common.code;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    SUCESS_CREATE_DIARY(HttpStatus.CREATED, "일기 작성에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
