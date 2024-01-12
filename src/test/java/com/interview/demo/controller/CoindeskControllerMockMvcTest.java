package com.interview.demo.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CoindeskControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnMessage() throws Exception {
        this.mockMvc.perform(post("/getCoindesk"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("British Pound Sterling")));
    }
}
