package com.github.owgrant24.springbootone.util;

import com.github.owgrant24.springbootone.model.User;
import com.github.owgrant24.springbootone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@Slf4j
@SpringBootTest
class UserValidatorTest {

    @Autowired
    private UserValidator userValidator;

    @MockBean
    private UserService userService;

    private static final String userEmail = "admin@mail.ru";

    private static final User user = mock(User.class);

    @BeforeAll
    public static void setup() {
        // Когда запрашиваем у пользователя email , нам приходит наш email = admin@....
        when(user.getEmail()).thenReturn(userEmail);
        when(user.getPassword()).thenReturn("1");
        when(user.getConfirmPassword()).thenReturn("1");
    }

    @Test
    void validateShouldAcceptUserWithNewEmail() {
        // Подготовка тестового сценария

        // Когда запрашиваем пользователя с таким емейлом, нам возвращается null
        when(userService.getUserByEmail(userEmail)).thenReturn(null);
        Errors errors = mock(Errors.class);

        userValidator.validate(user, errors);
        // Проверяем что на объекте errors, никогда не вызывался объект rejectValue с такими параметрами
        // (email и 2 любых других)
        verify(errors, never()).rejectValue(eq("email"), any(), any());
        log.info(errors.toString());
    }

    @Test
    void validateShouldRejectUserWithAlreadyUsedEmail() {
        // Подготовка тестового сценария

        // Когда запрашиваем пользователя с таким емейлом, нам возвращается null
        when(userService.getUserByEmail(userEmail)).thenReturn(user);
        Errors errors = mock(Errors.class);

        userValidator.validate(user, errors);
        // Проверяем что rejectValue на объекте errors вызывался один раз, c этими параметрами
        verify(errors, times(1))
                .rejectValue(eq("email"), any(), any());
    }

}