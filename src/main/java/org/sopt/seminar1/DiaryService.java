package org.sopt.seminar1;

import org.sopt.seminar1.Main.Exception.DiaryNotFoundException;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryService {

    private final DiaryRepository diaryRepository = new DiaryRepository();
    private static final AtomicLong numbering = new AtomicLong();

    List<Diary> getDiaryList() {
        return diaryRepository.findAll();
    }

    Diary saveDiary(final String body) {
        Diary diary = Diary.create(numbering.getAndAdd(1), body);
        diaryRepository.saveDiary(diary.getId(), diary);
        return diary;
    }

    Diary findDiary(final long id) {
        Diary diary = diaryRepository.findById(id);
        if (diary == null) {
            throw new DiaryNotFoundException("해당 id의 일기를 찾을 수 없습니다.");
        }
        return diary;
    }

    void patchDiary(final long id, final String body) {
        Diary diary = findDiary(id);
        if (diary.getUpdateCount() == 2) {
            throw new IllegalStateException("일기는 하루에 2번까지만 수정이 가능합니다.");
        }
        diary.updateBody(body);
        diaryRepository.patchDiary(id, diary);
    }

    void restoreDiary(final long id) {
        Diary diary = diaryRepository.deletedDiary(id);
        if (diary == null) {
            throw new DiaryNotFoundException("해당 id의 일기는 존재하지 않으므로, 복구가 불가능합니다.");
        }
        diaryRepository.restoreDiary(id, diary);
    }

    void deleteDiary(final long id) {
        diaryRepository.deleteDiary(id, findDiary(id));
    }

    List<Diary> getDeletedDiaryList() {
        return diaryRepository.getDeletedDiaryList();
    }

}
