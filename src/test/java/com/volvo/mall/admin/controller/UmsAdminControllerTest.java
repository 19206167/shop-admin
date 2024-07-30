package com.volvo.mall.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.volvo.mall.admin.dto.UmsAdminLoginParam;
import com.volvo.mall.admin.dto.UmsAdminParam;
import com.volvo.mall.admin.dto.UpdateAdminPasswordParam;
import com.volvo.mall.admin.model.UmsAdmin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UmsAdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImNyZWF0ZWQiOjE3MjIyNDg4ODgwMDUsImV4cCI6MTcyMjg1MzY4OH0" +
            ".e1PMpFBPsigTvUFdUqgcv7BCJL9YtCnJgTWFF-KS_m35GRY1PtmvJsF89m7GgTSq_zqSWx9yoYI6eoJn-u1P-A";

    @BeforeEach
    void setUp() {
    }

    @Test
    void register() {
        UmsAdminParam umsAdminParam = new UmsAdminParam();
        umsAdminParam.setNickName("test1");
        umsAdminParam.setUsername("test1");
        umsAdminParam.setPassword("123456");
        umsAdminParam.setEmail("test@test.com");

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/admin/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(umsAdminParam)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(200))
                    .andExpect(jsonPath("$.data.username").value("test1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImNyZWF0ZWQiOjE3MjIyNDg4ODgwMDUsImV4cCI6MTcyMjg1MzY4OH0.e1PMpFBPsigTvUFdUqgcv7BCJL9YtCnJgTWFF-KS_m35GRY1PtmvJsF89m7GgTSq_zqSWx9yoYI6eoJn-u1P-A
    @Test
    void login() {
        UmsAdminLoginParam umsAdminLoginParam = new UmsAdminLoginParam();
        umsAdminLoginParam.setUsername("admin");
        umsAdminLoginParam.setPassword("macro123");

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/admin/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(umsAdminLoginParam)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(200));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void refreshToken() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/admin/refreshToken")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(200));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAdminInfo() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/admin/info")
                    // Bearer 是令牌类型的指示符
                            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(200));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void logout() {
    }

    @Test
    void list() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/admin/list")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .param("keyword", "test"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(200));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getItem() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/admin/3")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(200));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void update() {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setEmail("546@z.com");
        umsAdmin.setId(11L);

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/admin/update/11")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(umsAdmin)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(200));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updatePassword() {
        UpdateAdminPasswordParam updatePasswordParam = new UpdateAdminPasswordParam();
        updatePasswordParam.setUsername("test1");
        updatePasswordParam.setOldPassword("123456");
        updatePasswordParam.setNewPassword("456789");

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updatePasswordParam)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(200));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void delete() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/admin/delete/10")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(200));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateStatus() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/admin/updateStatus/10")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .param("status", "0"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(200));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateRole() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/admin/role/update")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .param("adminId", "11")
                    .param("roleIds", "1,2"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(200));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getRoleList() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/admin/role/3")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}