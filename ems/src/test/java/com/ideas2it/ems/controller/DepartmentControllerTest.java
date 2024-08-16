package com.ideas2it.ems.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ideas2it.ems.service.DepartmentServiceTest;

@Controller
public class DepartmentControllerTest {

    @Autowired
    private DepartmentServiceTest departmentServiceTest;

    @Test
    public void getDepartments() {
        departmentServiceTest.getDepartments();
    }
}
