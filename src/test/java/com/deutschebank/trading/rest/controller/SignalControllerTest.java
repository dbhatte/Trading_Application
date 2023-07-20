package com.deutschebank.trading.rest.controller;

import com.deutschebank.trading.algo.Application;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWebMvc
public class SignalControllerTest {

    @MockBean
    private Application application;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testIfApplicationIsCalledCorrectly() throws Exception {
        this.mockMvc.perform(post(String.format("/api/signal/%d", 1)))
                .andExpect(status().isAccepted());
        Mockito.verify(application).handleSignal(1);
    }
}
