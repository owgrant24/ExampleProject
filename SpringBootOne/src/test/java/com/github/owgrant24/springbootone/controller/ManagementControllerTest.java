package com.github.owgrant24.springbootone.controller;

import com.github.owgrant24.springbootone.model.Car;
import com.github.owgrant24.springbootone.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WithMockUser(username = "user", roles = "MANAGER")
@SpringBootTest
@AutoConfigureMockMvc
class ManagementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    private static Car car = mock(Car.class);
    private final int id = 1;

    @Test
    void newCar() throws Exception {
        mockMvc.perform(get("/management/new"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void edit() throws Exception {
        when(carService.getCarById(id)).thenReturn(car);

        mockMvc.perform(get("/management/{id}/edit", String.valueOf(id)))
                .andDo(print())
                // Проверка что нам придет ответ "200"
                .andExpect(status().isOk())
                // Проверка что в модель будет добавлен атрибут car со значением мокнутого car
                .andExpect(model().attribute("car", car))
                // Проверка что нам вернется view с данным именем
                .andExpect(view().name("management/edit"))
        ;
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/management")
                        .with(csrf())
                        .param("vin", "1234568909")
                        .param("brand", "Honda")
                        .param("model", "Civic")
                        .param("year", "2008")
                        .param("mileage", "192000")
                        .param("price", "455555")
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/management"));
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(patch("/management/{id}", String.valueOf(id))
                        .with(csrf())
                        .param("vin", "1234568909")
                        .param("brand", "Honda")
                        .param("model", "Civic")
                        .param("year", "2008")
                        .param("mileage", "192000")
                        .param("price", "455555")
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/management"));
    }


    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/management/{id}", String.valueOf(id))
                        .with(csrf())
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/management"));
    }

    @Test
    void sale() throws Exception {
        when(carService.getCarById(id)).thenReturn(car);
        mockMvc.perform(get("/management/{id}/sell", String.valueOf(id)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void sell() throws Exception {
        mockMvc.perform(patch("/management/{id}/sell", String.valueOf(id))
                        .with(csrf())
                        .param("vin", "1234568909")
                        .param("brand", "Honda")
                        .param("model", "Civic")
                        .param("year", "2008")
                        .param("mileage", "192000")
                        .param("price", "455555")
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/management"));
    }

}