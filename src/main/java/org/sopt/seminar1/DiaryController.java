package org.sopt.seminar1;

import org.sopt.seminar1.Main.UI.InvalidInputException;

import java.util.List;

public class DiaryController {

    private Status status = Status.READY;
    private final DiaryService diaryService = new DiaryService();

    Status getStatus() {
        return status;
    }

    void boot() {
        this.status = Status.RUNNING;
    }

    void finish() {
        this.status = Status.FINISHED;
    }

    // APIS
    final List<Diary> getList() {
        return diaryService.getDiaryList();
    }

    final void post(final String body) {
        checkBodyLength(body);
        diaryService.saveDiary(body);
    }

    final void delete(final String id) {
        diaryService.deleteDiary(convertIdType(id));
    }

    final void patch(final String id, final String body) {
        checkBodyLength(body);
        diaryService.patchDiary(convertIdType(id), body);
    }

    final List<Diary> getDeletedDiaryList() {
        return diaryService.getDeletedDiaryList();
    }

    final void restoreDiary(final String id) {
        diaryService.restoreDiary(convertIdType(id));
    }

    private long convertIdType(final String id) {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException ex) {
            throw new InvalidInputException("id값은 숫자만 가능합니다.");
        }
    }

    private void checkBodyLength(final String body) {
        if (body.length() > 30) {
            throw new InvalidInputException("일기 내용은 30자 이하만 가능합니다.");
        }
    }

    enum Status {
        READY,
        RUNNING,
        FINISHED,
        ERROR,
    }
}