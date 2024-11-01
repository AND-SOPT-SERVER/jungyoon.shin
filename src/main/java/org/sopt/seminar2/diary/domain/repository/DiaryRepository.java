package org.sopt.seminar2.diary.domain.repository;

import org.sopt.seminar2.diary.domain.Diary;
import org.sopt.seminar2.diary.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    List<Diary> findAllByOrderByCreatedAtDesc();

    Optional<Diary> findByIdAndUser(Long id, User user);
}
