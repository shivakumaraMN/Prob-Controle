package com.submersible.probecontrol.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProbeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void executesCommandsAndReturnsState() throws Exception {
        String commandsJson = "[\"F\"]";
        mockMvc.perform(post("/api/probe/default/commands")
                        .contentType("application/json")
                        .content(commandsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.position.x").value(0))
                .andExpect(jsonPath("$.position.y").value(1))
                .andExpect(jsonPath("$.visited[1].x").value(0))
                .andExpect(jsonPath("$.visited[1].y").value(1));
    }
}
