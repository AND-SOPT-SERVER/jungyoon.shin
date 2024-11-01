package org.sopt.seminar2.diary.common;

import lombok.extern.slf4j.Slf4j;

import org.sopt.seminar2.diary.common.code.FailureCode;
import org.sopt.seminar2.diary.common.dto.ErrorResponse;

import org.sopt.seminar2.diary.common.exception.ApiException;
import org.sopt.seminar2.diary.common.exception.DiaryException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleDiaryException(ApiException e) {
        log.error("[DairyException] 발생 : {}" , e.getMessage());

        FailureCode failureCode = e.getFailureCode();
        return ResponseEntity.status(failureCode.getHttpStatus())
                .body(ErrorResponse.of(
                        failureCode.getHttpStatus().value(),
                        failureCode.getMessage()
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("[MethodArgumentNotValidException] 발생 : {}", e.getMessage());

        FailureCode failureCode = FailureCode.INVALID_VALUE;
        return ResponseEntity.status(failureCode.getHttpStatus())
                .body(ErrorResponse.of(
                        failureCode.getHttpStatus().value(),
                        failureCode.getMessage(),
                        e.getBindingResult()
                ));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleValidationFailure(MissingServletRequestParameterException e) {
        log.error("[MissingParameterException] 발생 : {}", e.getMessage());

        FailureCode failureCode = FailureCode.MISSING_PARAMETER;
        return ResponseEntity.status(failureCode.getHttpStatus())
                .body(ErrorResponse.of(
                        failureCode.getHttpStatus().value(),
                        failureCode.getMessage()
                ));
    }
}
