package com.volvo.mall.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UmsMemberLevelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImNyZWF0ZWQiOjE3MjIyNDg4ODgwMDUsImV4cCI6MTcyMjg1MzY4OH0.e1PMpFBPsigTvUFdUqgcv7BCJL9YtCnJgTWFF-KS_m35GRY1PtmvJsF89m7GgTSq_zqSWx9yoYI6eoJn-u1P-A";

    @BeforeEach
    void setUp() {
    }

    @Test
    void list() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/memberLevel/list")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .param("defaultStatus", "1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(200));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}