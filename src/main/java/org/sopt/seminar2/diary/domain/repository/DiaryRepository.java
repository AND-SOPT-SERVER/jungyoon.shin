package org.sopt.seminar2.diary.domain.repository;

import org.sopt.seminar2.diary.domain.Diary;
import org.sopt.seminar2.diary.domain.User;

import org.sopt.seminar2.diary.domain.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    @Query("SELECT d FROM Diary d " +
            "WHERE (:category IS NULL OR d.category = :category) " +
            "ORDER BY d.createdAt DESC")
    List<Diary> findTop10ByCategory(@Param("category") Category category);

    @Query("SELECT d FROM Diary d " +
            "WHERE (:category IS NULL OR d.category = :category) AND (d.user = :user)" +
            "ORDER BY d.createdAt DESC")
    List<Diary> findTop10ByCategoryAndUser(@Param("category") Category category, @Param("user") User user);

    Optional<Diary> findByIdAndUser(Long id, User user);
}
