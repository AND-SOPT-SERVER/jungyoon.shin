package org.sopt.seminar2.diary.api;

import lombok.RequiredArgsConstructor;
import org.sopt.seminar2.diary.api.dto.request.DiaryCreateRequest;
import org.sopt.seminar2.diary.api.dto.response.DiaryDetailResponse;
import org.sopt.seminar2.diary.service.DiaryService;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping("/diary")
    public ResponseEntity<Void> postDiary(
            @RequestBody DiaryCreateRequest diaryCreateRequest
    ) {
        diaryService.postDiary(diaryCreateRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/diary/{diaryId}")
    public ResponseEntity<DiaryDetailResponse> getDiary(
            @PathVariable Long diaryId
    ) {
        DiaryDetailResponse diaryDetailResponse = diaryService.getDiary(diaryId);
        return ResponseEntity.ok().body(diaryDetailResponse);
    }

}
