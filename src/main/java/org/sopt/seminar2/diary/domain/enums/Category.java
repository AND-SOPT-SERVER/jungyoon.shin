package org.sopt.seminar2.diary.domain.enums;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Category {

    FOOD("food"),
    SCHOOL("school"),
    MOVIE("movie"),
    WORK_OUT("workout");

    private final String name;

    public static Category findCategory(String name) {
        Arrays.stream(Category.values()).filter(category -> category.name.equals(name)).findAny().orElseThrow()
    }
}
