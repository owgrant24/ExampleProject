package com.github.owgrant24.springbootone.controller;

import com.github.owgrant24.springbootone.security.SecurityUser;
import com.github.owgrant24.springbootone.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("cars", carService.getAllCarsWithFilter(false));
        model.addAttribute("user", user.toString());
        return "cars/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        // получим одну машину по id из DAO и передаём на отображение в представление
        model.addAttribute("car", carService.getCarById(id));
        return "cars/info";
    }

}
