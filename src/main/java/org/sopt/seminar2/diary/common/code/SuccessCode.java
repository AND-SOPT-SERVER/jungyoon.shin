package org.sopt.seminar2.diary.common.code;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    SUCCESS_CREATE_DIARY(HttpStatus.CREATED, "일기 작성에 성공했습니다."),
    SUCCESS_UPDATE_DIARY(HttpStatus.OK, "일기 수정에 성공했습니다."),
    SUCCESS_DELETE_DIARY(HttpStatus.OK, "일기 삭제에 성공했습니다."),
    SUCCESS_GET_DIARY_DETAIL(HttpStatus.OK, "일기 상세 조회에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
