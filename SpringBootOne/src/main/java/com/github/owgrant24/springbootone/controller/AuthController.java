package com.github.owgrant24.springbootone.controller;

import com.github.owgrant24.springbootone.model.User;
import com.github.owgrant24.springbootone.service.UserService;
import com.github.owgrant24.springbootone.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {
    private UserService userService;
    private UserValidator userValidator;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/cars";
    }

    @GetMapping("/sign_up")
    public String getSignUp(Model model) {
        model.addAttribute("user", new User());
        return "auth/sign_up";
    }

    @PostMapping("/sign_up")
    public String signUp(@ModelAttribute("user") @Valid User user, BindingResult result) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "auth/sign_up";
        }
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/error")
    public String error() {
        return "/error";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/sign_in";
    }

}
