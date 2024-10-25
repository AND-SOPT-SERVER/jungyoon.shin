package org.sopt.seminar2.diary.service;

import jakarta.persistence.EntityNotFoundException;
import org.sopt.seminar2.diary.api.dto.request.DiaryCreateRequest;
import org.sopt.seminar2.diary.api.dto.response.DiaryDetailResponse;
import org.sopt.seminar2.diary.domain.Diary;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.sopt.seminar2.diary.domain.repository.DiaryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public void postDiary(DiaryCreateRequest diaryCreateRequest) {
        Diary diary = Diary.create(diaryCreateRequest.title(), diaryCreateRequest.content());
        diaryRepository.save(diary);
    }

    public DiaryDetailResponse getDiary(final long id) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("id에 해당하는 다이어리가 없습니다."));

        return DiaryDetailResponse.of(
                diary.getTitle(),
                diary.getContent(),
                diary.getId(),
                diary.getCreatedAt()
        );
    }
}
