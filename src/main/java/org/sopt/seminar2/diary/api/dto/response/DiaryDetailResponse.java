package org.sopt.seminar2.diary.api.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record DiaryDetailResponse(
        String title,
        String content,
        long id,
        LocalDateTime createdAt
) {
    public static DiaryDetailResponse of(
            final String title,
            final String content,
            final long id,
            final LocalDateTime createdAt
    ) {
        return DiaryDetailResponse.builder()
                .title(title)
                .content(content)
                .id(id)
                .createdAt(createdAt)
                .build();
    }
}
