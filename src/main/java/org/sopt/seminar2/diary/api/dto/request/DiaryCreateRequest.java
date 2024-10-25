package org.sopt.seminar2.diary.api.dto.request;

import jakarta.validation.constraints.Size;

public record DiaryCreateRequest(
        String title,

        @Size(max = 30, message = "글자수는 30자 이하여야 합니다.")
        String content
) {
}
