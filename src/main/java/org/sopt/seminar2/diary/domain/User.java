package org.sopt.seminar2.diary.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String nickname;

}
