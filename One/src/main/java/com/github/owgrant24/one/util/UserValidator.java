package com.github.owgrant24.one.util;

import com.github.owgrant24.one.model.User;
import com.github.owgrant24.one.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // проверка что этот валидатор может валидировать сущности с этим типом
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        // Проверка есть ли пользователь с таким email уже в базе
        if (userService.getUserByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "", "This email is already in use");
        }
        // Проверка , совпадает ли поля : Введите пароль  и подтверждение пароля
        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "", "Password don't match.");
        }

    }
}
