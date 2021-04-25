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

@Controller
@RequestMapping(value = "/cars")
public class MainController {

    private CarService carService;

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @GetMapping()
    public String index(Model model
            , @RequestParam(value = "filterBrand", required = false) String filterBrand
                        //  , @RequestParam(value = "sold", required = false) String sold
            , @AuthenticationPrincipal SecurityUser user) {
        if (filterBrand == null || filterBrand.isEmpty()) {
            model.addAttribute("cars", carService.getAllCarsWithFilter(0, "sold"));
        } else {
//            model.addAttribute("cars", carService.getAllCarsWithFilter(filterBrand, "brand"));
            model.addAttribute("cars"
                    , carService.getAllCarsWithTwoFilter(0, filterBrand, "sold", "brand")
            );
        }
        model.addAttribute("user", user.toString());
        model.addAttribute("filterBrand", filterBrand);
        return "cars/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        // получим одного работника по id из DAO и передадим на отображение в представление
        model.addAttribute("car", carService.getCarById(id));
        return "cars/info";
    }

    // Форма создания новой машины
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @GetMapping("/new")
    public String newCar(@ModelAttribute("car") Car car) {
        return "cars/new";
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("car", carService.getCarById(id));
        return "cars/edit";
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @PostMapping()
    public String create(@ModelAttribute("car") @Valid Car car,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "cars/new";
        }
        carService.saveCar(car);
        return "redirect:/cars";                                              // редирект на страницу index
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "cars/edit";
        }
        carService.saveCar(car);
        return "redirect:/cars";
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        carService.deleteCarById(id);
        return "redirect:/cars";
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @GetMapping("/{id}/sell")
    public String sale(Model model, @PathVariable("id") int id) {
        model.addAttribute("car", carService.getCarById(id));
        return "cars/sell";
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @PatchMapping("/{id}/sell")
    public String sell(@ModelAttribute("car") @Valid Car car
            , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "cars/sell";
        }
        car.setSold(1);
        carService.saveCar(car);
        return "redirect:/cars";                                              // редирект на страницу index
    }

    @GetMapping("/all")
    public String carsAll(Model model
            , @RequestParam(value = "filterBrand", required = false) String filterBrand
                          //  , @RequestParam(value = "sold", required = false) String sold
            , @AuthenticationPrincipal SecurityUser user) {
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("user", user.toString());
        model.addAttribute("filterBrand", filterBrand);
        return "cars/cars_all";
    }

}
