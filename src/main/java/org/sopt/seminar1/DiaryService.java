package org.sopt.seminar1;

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

    Diary getDiary(final long id) {
        return diaryRepository.findById(id);
    }

    void patchDiary(final long id, final String body) {
        Diary diary = getDiary(id);
        diary.setBody(body);
        diaryRepository.patchDiary(id, diary);
    }

    void restoreDiary(final long id) {
        Diary diary = diaryRepository.deletedDiary(id);
        if (diary == null) {
            throw new Main.UI.NotFoundException("해당 id의 일기는 존재하지 않으므로, 복구가 불가능합니다.");
        }
        diaryRepository.restoreDiary(id, diaryRepository.deletedDiary(id));
    }

    void deleteDiary(final long id) {
        diaryRepository.deleteDiary(id, getDiary(id));
    }

    List<Diary> getDeletedDiaryList() {
        return diaryRepository.getDeletedDiaryList();
    }

}
