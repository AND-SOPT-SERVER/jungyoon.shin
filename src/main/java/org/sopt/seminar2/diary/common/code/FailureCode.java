package org.sopt.seminar2.diary.common.code;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum FailureCode {

    INVALID_VALUE(HttpStatus.BAD_REQUEST, "잘못된 값입니다."),
    MISSING_PARAMETER(HttpStatus.BAD_REQUEST, "필수 파라미터가 없습니다."),
    NOT_EXISTS_CATEGORY(HttpStatus.BAD_REQUEST, "잘못된 카테고리 값입니다."),

    // User
    NOT_EXISTS_USER(HttpStatus.NOT_FOUND, "해당 이름을 가진 유저가 존재하지 않습니다."),
    NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),


    // Diary
    NOT_EXISTS_DIARY_WITH_ID(HttpStatus.NOT_FOUND, "id에 해당하는 일기가 없습니다."),
    NOT_EXISTS_DIARY_WITH_ID_AND_USER(HttpStatus.NOT_FOUND, "유저와 id에 해당하는 일기가 없습니다.");


    private final HttpStatus httpStatus;
    private final String message;

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
