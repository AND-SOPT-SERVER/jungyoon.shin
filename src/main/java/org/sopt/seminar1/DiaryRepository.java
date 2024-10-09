package org.sopt.seminar1;

import org.sopt.seminar1.Main.UI.InvalidInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DiaryRepository {

    private final Map<Long, Diary> storage = new ConcurrentHashMap<>();

    void saveDiary(final long id, final Diary diary) {

        storage.put(id, diary);
    }

    Diary findById(long id) {
        validateDairyExists(id);
        return storage.get(id);
    }

    List<Diary> findAll() {
        List<Diary> diaryList = new ArrayList<>();
        for (Long key : storage.keySet()) {
            diaryList.add(storage.get(key));
        }
        return diaryList;
    }

    void patchDiary(final long id, final Diary diary) {
        validateDairyExists(id);
        storage.put(id, diary);
    }

    void deleteDiary(final long id) {
        validateDairyExists(id);
        storage.remove(id);
    }

    void validateDairyExists(final long id) {
        if(!storage.containsKey(id)) {
            throw new InvalidInputException("해당 id의 일기는 존재하지 않습니다.");
        }
    }


}