package org.sopt.seminar2.diary.service;

import org.sopt.seminar2.diary.api.dto.response.DiaryDetailResponse;
import org.sopt.seminar2.diary.api.dto.response.DiaryListResponse;
import org.sopt.seminar2.diary.common.exception.DiaryException;
import org.sopt.seminar2.diary.common.exception.UserException;
import org.sopt.seminar2.diary.domain.enums.Category;
import org.sopt.seminar2.diary.domain.repository.DiaryRepository;
import org.sopt.seminar2.diary.domain.repository.UserRepository;

import org.sopt.seminar2.diary.domain.Diary;
import org.sopt.seminar2.diary.domain.User;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.List;

import static org.sopt.seminar2.diary.common.code.FailureCode.*;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;

    public void postDiary(
            final String title,
            final String content,
            final Category category,
            final String username,
            final String password
    ) {
        User user = findUser(username, password);
        Diary diary = Diary.create(user, title, content);
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
    public void updateDiary(
            final long id,
            final String content,
            final String username,
            final String password
    ) {
        User user = findUser(username, password);
        findDiaryWithUser(id, user).updateDiary(content);
    }

    public void deleteDiary(final long id, final String username, final String password) {
        User user = findUser(username, password);
        diaryRepository.delete(findDiaryWithUser(id, user));
    }

    public Diary findDiaryWithUser(final long id, final User user) {
        return diaryRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new DiaryException(NOT_EXISTS_DIARY_WITH_ID_AND_USER));
    }

    public Diary findDiary(final long diaryId) {
        return diaryRepository.findById(diaryId)
                .orElseThrow(() -> new DiaryException(NOT_EXISTS_DIARY_WITH_ID));
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
