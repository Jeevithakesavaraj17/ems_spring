package com.ideas2it.ems.service;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import com.ideas2it.ems.dao.DepartmentDao;
import com.ideas2it.ems.dto.DepartmentDto;
import com.ideas2it.ems.model.Department;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private DepartmentDao departmentDao;

    @InjectMocks
    private DepartmentServiceImplTest departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department = new Department();
        department.setDepartmentId(1);
        department.setDepartmentName("Admin");
    }

    @DisplayName("JUnit test for getAllDepartments method")
    @Test
    public void getDepartments() {
        when(departmentDao.findAll()).thenReturn(List.of(department));
        List<DepartmentDto> departments = departmentService.getDepartments();
        assertThat(departments).isNotNull();
        assertThat(departments.size()).isEqualTo(1);
    }
}
