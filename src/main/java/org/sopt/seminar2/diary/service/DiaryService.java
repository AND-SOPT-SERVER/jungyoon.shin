package org.sopt.seminar2.diary.service;

import jakarta.persistence.EntityNotFoundException;

import org.sopt.seminar2.diary.api.dto.request.DiaryCreateRequest;
import org.sopt.seminar2.diary.api.dto.request.DiaryUpdateRequest;
import org.sopt.seminar2.diary.api.dto.response.DiaryDetailResponse;
import org.sopt.seminar2.diary.api.dto.response.DiaryListResponse;
import org.sopt.seminar2.diary.common.code.FailureCode;
import org.sopt.seminar2.diary.common.exception.UserException;
import org.sopt.seminar2.diary.domain.repository.DiaryRepository;
import org.sopt.seminar2.diary.domain.Diary;
import org.sopt.seminar2.diary.domain.User;

import org.sopt.seminar2.diary.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.List;

import static org.sopt.seminar2.diary.common.code.FailureCode.NOT_EXISTS_USER;
import static org.sopt.seminar2.diary.common.code.FailureCode.NOT_MATCH_PASSWORD;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;

    public void postDiary(final DiaryCreateRequest diaryCreateRequest, final String username, final String password) {
        User user = findUser(username, password);
        Diary diary = Diary.create(user, diaryCreateRequest.title(), diaryCreateRequest.content());
        diaryRepository.save(diary);
    }


    public DiaryDetailResponse getDiary(final long id) {
        Diary diary = findDiary(id);

        return DiaryDetailResponse.of(
                diary.getTitle(),
                diary.getContent(),
                diary.getId(),
                diary.getCreatedAt()
        );
    }

    public DiaryListResponse getDiaryList() {
        List<Diary> diaries = diaryRepository.findAllByOrderByCreatedAtDesc();
        List<DiaryListResponse.DiaryResponse> diaryDetailResponses = diaries.stream()
                .map(diary -> DiaryListResponse.DiaryResponse.of(diary.getId(), diary.getTitle()))
                .toList();

        return DiaryListResponse.of(diaryDetailResponses);
    }

    @Transactional
    public void updateDiary(final long id, final DiaryUpdateRequest diaryUpdateRequest) {
        findDiary(id).updateDiary(diaryUpdateRequest.content());
    }

    public void deleteDiary(final long id) {
        diaryRepository.delete(findDiary(id));
    }

    public Diary findDiary(final long id) {
        return diaryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("id에 해당하는 다이어리가 없습니다."));
    }

    public User findUser(final String username, final String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserException(NOT_EXISTS_USER));

        if (!user.getPassword().equals(password)) {
            throw new UserException(NOT_MATCH_PASSWORD);
        }

        return user;
    }
}
