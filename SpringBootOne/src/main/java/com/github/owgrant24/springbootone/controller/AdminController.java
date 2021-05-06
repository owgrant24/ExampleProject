package com.github.owgrant24.springbootone.controller;

import com.github.owgrant24.springbootone.service.UserService;
import com.github.owgrant24.springbootone.util.TextReplacer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Secured({"ROLE_ADMIN", "ROLE_DEMO"})
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_DEMO"})
    @GetMapping()
    public String admin(Model model) {
        model.addAttribute("users", userService.getAllUsers());;
        return "def/dashboard";
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

}
