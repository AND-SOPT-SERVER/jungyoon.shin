package org.sopt.seminar2.diary.domain;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.sopt.seminar2.diary.domain.enums.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private Category category;

    private LocalDateTime createdAt;

    @Builder
    private Diary(
            final User user,
            final String title,
            final String content,
            final Category category
    ) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.category = category;
    }

    public static Diary create(
            final User user,
            final String title,
            final String content,
            final Category category
    ) {
        return Diary.builder()
                .user(user)
                .title(title)
                .content(content)
                .category(category)
                .build();
    }

    public void updateDiary(final String content) {
        this.content = content;
    }

}
