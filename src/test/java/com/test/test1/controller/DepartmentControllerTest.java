package com.test.test1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.test1.entity.Department;
import com.test.test1.service.Serviice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Serviice serviice;
    @Autowired
    ObjectMapper mapper;
    private Department department1;
    private Department department2;
    List<Department> departmentList = new ArrayList<>();


    @BeforeEach
    void setUp() {
        department1 = new Department(Long.valueOf(1), "CSE", "Hyderabad", "TS001");
        department2 = new Department(Long.valueOf(2), "IT", "Hyderabad", "TS002");
        departmentList.add(department1);
        departmentList.add(department2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSaveDepartment() throws Exception {

        when(serviice.saveDepartment(department1)).
                thenReturn(department1);
        MockHttpServletRequestBuilder mockRequest = post("/api/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(department1));

        this.mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andDo(print());

    }

    @Test
    void getDepartmentById() throws Exception {
        when(serviice.getDepartmentById(Long.valueOf(1))).
                thenReturn(department1);
        this.mockMvc.perform(get("/api/departments/department/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void updateDepartment() throws Exception {
        when(serviice.updateDepartmentById(department1,Long.valueOf(1))).
                thenReturn(department1);
        MockHttpServletRequestBuilder mockRequest = put("/api/departments/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(department1));

        this.mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testGetAllDepartments() throws Exception {
        when(serviice.getAllDepartments()).
                thenReturn(departmentList);
        this.mockMvc.perform(get("/api/departments"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
}

    @Test
    void testGetDeptByName() throws Exception {

        when(serviice.getDeptByName("CSE")).
                thenReturn(departmentList);
        this.mockMvc.perform(get("/api/departments/name/CSE"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }
}