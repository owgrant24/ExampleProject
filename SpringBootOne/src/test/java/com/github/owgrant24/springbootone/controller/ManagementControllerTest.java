package com.github.owgrant24.springbootone.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WithMockUser(username = "user", roles = "MANAGER")
@SpringBootTest
@AutoConfigureMockMvc
class ManagementControllerTest {

    @Autowired
    private ManagementController managementController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test() {
        assertThat(managementController).isNotNull();
    }


    @Test
    void newCar() throws Exception {
        mockMvc.perform(get("/management/new"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}