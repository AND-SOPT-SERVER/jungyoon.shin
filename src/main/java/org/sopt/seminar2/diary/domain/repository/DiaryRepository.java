package org.sopt.seminar2.diary.domain.repository;

import org.sopt.seminar2.diary.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    List<Diary> findAllByOrderByCreatedAtDesc();
}
