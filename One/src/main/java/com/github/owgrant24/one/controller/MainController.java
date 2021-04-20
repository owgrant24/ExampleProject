package com.github.owgrant24.one.controller;

import com.github.owgrant24.one.model.Car;
import com.github.owgrant24.one.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/cars")
public class MainController {

    private CarService carService;

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @GetMapping()
    public String index(Model model) {
        // Получим все машины из DAO и передадим на отображение в представление
        model.addAttribute("cars", carService.getAllCars());
        return "cars/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        // получим одного работника по id из DAO и передадим на отображение в представление
        model.addAttribute("car", carService.getCarById(id));
        return "cars/info";
    }

    // Форма создания новой машины
    @GetMapping("/new")
    public String newCar(@ModelAttribute("car") Car car) {
        return "cars/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("car", carService.getCarById(id));
        return "cars/edit";
    }

    @PostMapping()
    public String create(@ModelAttribute("car") @Valid Car car,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "cars/new";
        }
        carService.saveCar(car);
        return "redirect:/cars";                                              // редирект на страницу index
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "cars/edit";
        }
        carService.saveCar(car);
        return "redirect:/cars";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        carService.deleteCarById(id);
        return "redirect:/cars";

    }

}
