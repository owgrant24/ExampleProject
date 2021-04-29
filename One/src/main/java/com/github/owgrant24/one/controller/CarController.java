package com.github.owgrant24.one.controller;

import com.github.owgrant24.one.security.SecurityUser;
import com.github.owgrant24.one.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/cars")
public class CarController {
    private CarService carService;

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @GetMapping()
    public String index(Model model, @AuthenticationPrincipal SecurityUser user) {
        model.addAttribute("cars", carService.getAllCarsWithFilter(0, "sold"));
        model.addAttribute("user", user.toString());
        return "cars/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        // получим одного работника по id из DAO и передадим на отображение в представление
        model.addAttribute("car", carService.getCarById(id));
        return "cars/info";
    }

}
