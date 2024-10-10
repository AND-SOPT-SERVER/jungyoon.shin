package org.sopt.seminar1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DiaryRepository {

    private final Map<Long, Diary> storage = new ConcurrentHashMap<>();
    private final Map<Long, Diary> deletedDiaryList = new ConcurrentHashMap<>();

    void saveDiary(final long id, final Diary diary) {
        storage.put(id, diary);
    }

    Diary deletedDiary(final long id) {
        if (!deletedDiaryList.containsKey(id)) {
            return null;
        }
        return deletedDiaryList.get(id);
    }

    Diary findById(long id) {
        if (!storage.containsKey(id)) {
            return null;
        }
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
        storage.put(findById(id).getId(), diary);
    }

    void deleteDiary(final long id, final Diary diary) {
        deletedDiaryList.put(findById(id).getId(), diary);
        storage.remove(id);
    }

    List<Diary> getDeletedDiaryList() {
        List<Diary> diaryList = new ArrayList<>();
        for (Long key : deletedDiaryList.keySet()) {
            diaryList.add(deletedDiaryList.get(key));
        }
        return diaryList;
    }

    void restoreDiary(final long id, final Diary diary) {
        storage.put(id, diary);
        deletedDiaryList.remove(id);
    }

}