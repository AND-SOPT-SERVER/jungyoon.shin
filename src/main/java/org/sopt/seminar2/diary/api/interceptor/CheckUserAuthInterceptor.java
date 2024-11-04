package org.sopt.seminar2.diary.api.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.sopt.seminar2.diary.api.annotation.CheckUserAuth;
import org.sopt.seminar2.diary.common.exception.DiaryException;
import org.sopt.seminar2.diary.common.exception.UserException;
import org.sopt.seminar2.diary.domain.Diary;
import org.sopt.seminar2.diary.domain.User;
import org.sopt.seminar2.diary.domain.repository.DiaryRepository;
import org.sopt.seminar2.diary.domain.repository.UserRepository;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

import static org.sopt.seminar2.diary.common.code.FailureCode.*;


@Component
@RequiredArgsConstructor
public class CheckUserAuthInterceptor implements HandlerInterceptor {

    private final String USERNAME_HEADER = "username";
    private final String PASSWORD_HEADER = "password";

    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;
    @Override
    @Transactional
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Handler가 메소드인 경우에만 진행
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            // 핸들러 메서드에 @CheckUserAuth 어노테이션이 있는지 확인
            if (handlerMethod.getMethod().isAnnotationPresent(CheckUserAuth.class)) {
                String username = request.getHeader(USERNAME_HEADER);
                String password = request.getHeader(PASSWORD_HEADER);

                Map<String, String> pathVariables = (Map<String, String>) request
                        .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

                Long diaryId = Long.valueOf(pathVariables.get("diaryId"));
                
                User loginUser = getLoginUser(username);
                Diary diary = getDiary(diaryId);

                boolean isOwner = loginUser.equals(diary.getUser());

                if (!isOwner) {
                    throw new UserException(NO_PERMISSON_FOR_DAIRY);
                }

            }
        }
        return true;
    }

    private User getLoginUser(String username) {
       return userRepository.findByUsername(username)
               .orElseThrow(() -> new UserException(NOT_EXISTS_USER));
    }

    private Diary getDiary(long diaryId) {
        return diaryRepository.findById(diaryId)
                .orElseThrow(() -> new DiaryException(NOT_EXISTS_DIARY_WITH_ID));
    }
}
