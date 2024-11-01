package org.sopt.seminar2.diary.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.sopt.seminar2.diary.api.dto.request.DiaryCreateRequest;
import org.sopt.seminar2.diary.api.dto.request.DiaryUpdateRequest;
import org.sopt.seminar2.diary.api.dto.response.DiaryDetailResponse;
import org.sopt.seminar2.diary.api.dto.response.DiaryListResponse;
import org.sopt.seminar2.diary.common.dto.SuccessResponse;
import org.sopt.seminar2.diary.service.DiaryService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.sopt.seminar2.diary.common.code.SuccessCode.SUCESS_CREATE_DIARY;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping("/diary")
    public ResponseEntity<SuccessResponse> postDiary(
            @Valid @RequestBody DiaryCreateRequest diaryCreateRequest,
            @RequestHeader("username") String username,
            @RequestHeader("password") String password
    ) {
        diaryService.postDiary(diaryCreateRequest, username, password);
        return ResponseEntity.ok(SuccessResponse.of(SUCESS_CREATE_DIARY));
    }

    // 일기 상세 조회
    @GetMapping("/diary/{diaryId}")
    public ResponseEntity<DiaryDetailResponse> getDiary(
            @PathVariable long diaryId
    ) {
        DiaryDetailResponse diaryDetailResponse = diaryService.getDiary(diaryId);
        return ResponseEntity.ok().body(diaryDetailResponse);
    }

    @GetMapping("/diary")
    public ResponseEntity<DiaryListResponse> getDiaryList() {
        DiaryListResponse diaryListResponse = diaryService.getDiaryList();
        return ResponseEntity.ok().body(diaryListResponse);
    }

    @DeleteMapping("/diary/{diaryId}")
    public ResponseEntity<Void> deleteDiary(
            @PathVariable long diaryId,
            @RequestHeader("username") String username,
            @RequestHeader("password") String password
    ) {
        diaryService.deleteDiary(diaryId, username, password);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/diary/{diaryId}")
    public ResponseEntity<Void> updateDiary(
            @PathVariable long diaryId,
            @RequestHeader("username") String username,
            @RequestHeader("password") String password,
            @RequestBody DiaryUpdateRequest diaryUpdateRequest
    ) {
        diaryService.updateDiary(diaryId, diaryUpdateRequest, username, password);
        return ResponseEntity.ok().build();
    }

}
