package com.github.owgrant24.one.controller;

import com.github.owgrant24.one.model.Car;
import com.github.owgrant24.one.security.SecurityUser;
import com.github.owgrant24.one.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
@Controller
@RequestMapping(value = "/management")
public class ManagementController {
    private CarService carService;

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @GetMapping()
    public String index(Model model, @AuthenticationPrincipal SecurityUser user) {
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("user", user.toString());
        return "management/index";
    }

    // Форма создания новой машины
    @GetMapping("/new")
    public String newCar(@ModelAttribute("car") Car car) {
        return "management/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("car", carService.getCarById(id));
        return "management/edit";
    }

    @PostMapping()
    public String create(@ModelAttribute("car") @Valid Car car,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "management/new";
        }
        carService.saveCar(car);
        return "redirect:/management";                                              // редирект на страницу index
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "management/edit";
        }
        carService.saveCar(car);
        return "redirect:/management";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        carService.deleteCarById(id);
        return "redirect:/management";
    }

    @GetMapping("/{id}/sell")
    public String sale(Model model, @PathVariable("id") int id) {
        model.addAttribute("car", carService.getCarById(id));
        return "management/sell";
    }


    @PatchMapping("/{id}/sell")
    public String sell(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "management/sell";
        }
        car.setSold(true);
        carService.saveCar(car);
        return "redirect:/management";                                              // редирект на страницу index
    }
}
