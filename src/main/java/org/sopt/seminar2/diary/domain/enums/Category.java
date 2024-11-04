package org.sopt.seminar2.diary.domain.enums;

import lombok.RequiredArgsConstructor;
import org.sopt.seminar2.diary.common.exception.DiaryException;

import java.util.Arrays;

import static org.sopt.seminar2.diary.common.code.FailureCode.NOT_EXISTS_CATEGORY;

@RequiredArgsConstructor
public enum Category {

    FOOD("food"),
    SCHOOL("school"),
    MOVIE("movie"),
    WORK_OUT("workout");

    private final String name;

    public static Category findCategory(String name) {
        if (name == null) {
            return null;
        }
        return Arrays.stream(Category.values()).filter(category -> category.name.equals(name)).findAny()
                .orElseThrow(() -> new DiaryException(NOT_EXISTS_CATEGORY));
    }
}
