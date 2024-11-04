package org.sopt.seminar2.diary.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.sopt.seminar2.diary.api.annotation.CheckUserAuth;
import org.sopt.seminar2.diary.api.dto.request.DiaryCreateRequest;
import org.sopt.seminar2.diary.api.dto.request.DiaryUpdateRequest;
import org.sopt.seminar2.diary.api.dto.response.DiaryDetailResponse;
import org.sopt.seminar2.diary.api.dto.response.DiaryListResponse;
import org.sopt.seminar2.diary.common.dto.SuccessResponse;
import org.sopt.seminar2.diary.domain.enums.Category;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.sopt.seminar2.diary.common.code.SuccessCode.SUCCESS_CREATE_DIARY;
import static org.sopt.seminar2.diary.common.code.SuccessCode.SUCCESS_GET_DIARY_DETAIL;
import static org.sopt.seminar2.diary.common.code.SuccessCode.SUCCESS_DELETE_DIARY;
import static org.sopt.seminar2.diary.common.code.SuccessCode.SUCCESS_UPDATE_DIARY;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DiaryController {

    private final String USERNAME_HEADER = "username";
    private final String PASSWORD_HEADER = "password";

    private final DiaryService diaryService;

    @PostMapping("/diary")
    public ResponseEntity<SuccessResponse> postDiary(
            @Valid @RequestBody DiaryCreateRequest diaryCreateRequest,
            @RequestHeader(USERNAME_HEADER) String username,
            @RequestHeader(PASSWORD_HEADER) String password
    ) {
        diaryService.postDiary(
                diaryCreateRequest.title(),
                diaryCreateRequest.content(),
                Category.findCategory(diaryCreateRequest.category()),
                username,
                password
        );
        return ResponseEntity.ok(SuccessResponse.of(SUCCESS_CREATE_DIARY));
    }

    // 일기 상세 조회
    @GetMapping("/diary/{diaryId}")
    public ResponseEntity<SuccessResponse<DiaryDetailResponse>> getDiary(
            @PathVariable long diaryId
    ) {
        DiaryDetailResponse diaryDetailResponse = diaryService.getDiary(diaryId);
        return ResponseEntity.ok(SuccessResponse.of(SUCCESS_GET_DIARY_DETAIL, diaryDetailResponse));
    }

    // 메인 홈
    @GetMapping("/diary")
    public ResponseEntity<DiaryListResponse> getDiaryList(
            @RequestParam(required = false) String category
    ) {
        DiaryListResponse diaryListResponse = diaryService.getDiaryList(Category.findCategory(category));
        return ResponseEntity.ok().body(diaryListResponse);
    }

    // 내 일기 모아보기
    @GetMapping("/mypage/diary")
    public ResponseEntity<DiaryListResponse> getMyDairyList(
            @RequestHeader(USERNAME_HEADER) String username,
            @RequestHeader(PASSWORD_HEADER) String password,
            @RequestParam(required = false) String category
    ) {
        DiaryListResponse diaryListResponse = diaryService.getMyDiaryList(Category.findCategory(category), username, password);
        return ResponseEntity.ok().body(diaryListResponse);
    }

    // 일기 삭제
    @DeleteMapping("/diary/{diaryId}")
    @CheckUserAuth
    public ResponseEntity<SuccessResponse> deleteDiary(
            @PathVariable long diaryId,
            @RequestHeader(USERNAME_HEADER) String username,
            @RequestHeader(PASSWORD_HEADER) String password
    ) {
        diaryService.deleteDiary(diaryId, username, password);
        return ResponseEntity.ok(SuccessResponse.of(SUCCESS_DELETE_DIARY));
    }

    // 일기 수정
    @PatchMapping("/diary/{diaryId}")
    @CheckUserAuth
    public ResponseEntity<SuccessResponse> updateDiary(
            @PathVariable long diaryId,
            @RequestHeader(USERNAME_HEADER) String username,
            @RequestHeader(PASSWORD_HEADER) String password,
            @RequestBody DiaryUpdateRequest diaryUpdateRequest
    ) {
        diaryService.updateDiary(diaryId, diaryUpdateRequest.content(), username, password);
        return ResponseEntity.ok(SuccessResponse.of(SUCCESS_UPDATE_DIARY));
    }

}
