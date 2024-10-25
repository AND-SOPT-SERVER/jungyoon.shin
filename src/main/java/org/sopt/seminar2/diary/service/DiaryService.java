package org.sopt.seminar2.diary.service;

import jakarta.persistence.EntityNotFoundException;
import org.sopt.seminar2.diary.api.dto.request.DiaryCreateRequest;
import org.sopt.seminar2.diary.api.dto.request.DiaryUpdateRequest;
import org.sopt.seminar2.diary.api.dto.response.DiaryDetailResponse;
import org.sopt.seminar2.diary.api.dto.response.DiaryListResponse;
import org.sopt.seminar2.diary.domain.Diary;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.sopt.seminar2.diary.domain.repository.DiaryRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public void postDiary(final DiaryCreateRequest diaryCreateRequest) {
        Diary diary = Diary.create(diaryCreateRequest.title(), diaryCreateRequest.content());
        diaryRepository.save(diary);
    }

    @Transactional(readOnly = true)
    public DiaryDetailResponse getDiary(final long id) {
        Diary diary = findDiary(id);

        return DiaryDetailResponse.of(
                diary.getTitle(),
                diary.getContent(),
                diary.getId(),
                diary.getCreatedAt()
        );
    }

    @Transactional(readOnly = true)
    public DiaryListResponse getDiaryList() {
        List<Diary> diaries = diaryRepository.findAllByOrderByCreatedAtDesc();
        List<DiaryListResponse.DiaryResponse> diaryDetailResponses = diaries.stream()
                .map(diary -> DiaryListResponse.DiaryResponse.of(diary.getId(), diary.getTitle()))
                .toList();

        return DiaryListResponse.of(diaryDetailResponses);
    }

    public void updateDiary(final long id, final DiaryUpdateRequest diaryUpdateRequest) {
        findDiary(id).updateDiary(diaryUpdateRequest.content());
    }

    public void deleteDiary(final long id) {
        diaryRepository.delete(findDiary(id));
    }

    @Transactional
    public Diary findDiary(final long id) {
        return diaryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("id에 해당하는 다이어리가 없습니다."));
    }
}
