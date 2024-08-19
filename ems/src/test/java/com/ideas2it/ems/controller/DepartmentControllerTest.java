package com.ideas2it.ems.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.ideas2it.ems.dto.DepartmentDto;
import com.ideas2it.ems.service.DepartmentServiceImpl;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {
    @Mock
    private DepartmentServiceImpl departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    private DepartmentDto departmentDto;

    @BeforeEach
    void setUp() {
        departmentDto = new DepartmentDto(1,"Admin");
        List<DepartmentDto> departmentDtos = departmentService.getAllDepartments();
    }

    @Test
    void testAddDepartment() {
        when(departmentService.addDepartment(departmentDto)).thenReturn(departmentDto);
        ResponseEntity<DepartmentDto> createdDepartment = departmentController.addDepartment(departmentDto);
        assertEquals(HttpStatus.CREATED,createdDepartment.getStatusCode());
    }
}
