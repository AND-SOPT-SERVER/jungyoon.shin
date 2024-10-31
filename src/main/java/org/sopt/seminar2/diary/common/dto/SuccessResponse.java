package org.sopt.seminar2.diary.common.dto;

import org.sopt.seminar2.diary.common.code.SuccessCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"status", "message", "result"})
public record SuccessResponse<T>(
        int status,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        T result
) {
    public static <T> SuccessResponse of(SuccessCode successCode){
        return new SuccessResponse(successCode.getHttpStatus().value(), successCode.getMessage(), null);
    }

    public static <T> SuccessResponse of(SuccessCode successCode, T result){
        return new SuccessResponse(successCode.getHttpStatus().value(), successCode.getMessage(), result);
    }
}
