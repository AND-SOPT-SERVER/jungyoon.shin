package org.sopt.seminar2.diary.api.dto.response;

import java.util.List;

public record DiaryListResponse(
        List<DiaryResponse> diaryList

) {
    public record DiaryResponse(
            long id,
            String title
    ) {
        public static DiaryResponse of(final long id, final String title) {
            return new DiaryResponse(id, title);
        }
    }

    public static DiaryListResponse of(final List<DiaryResponse> diaryResponses) {
        return new DiaryListResponse(diaryResponses);
    }
}
