package org.sopt.seminar2.diary.api.dto.request;

import jakarta.validation.constraints.Size;

public record DiaryCreateRequest(

        @Size(min = 1, max = 10, message = "제목은 10자 이하여야 합니다.")
        String title,

        @Size(min = 1, max = 30, message = "본문은 30자 이하여야 합니다.")
        String content,

        String category
) {
}
