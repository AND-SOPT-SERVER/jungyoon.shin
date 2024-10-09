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
        Diary diary = diaryRepository.findById(id);
        diary.setBody(body);
        diaryRepository.patchDiary(id, diary);
    }

    void deleteDiary(final long id) {
        diaryRepository.deleteDiary(id);
    }
}
