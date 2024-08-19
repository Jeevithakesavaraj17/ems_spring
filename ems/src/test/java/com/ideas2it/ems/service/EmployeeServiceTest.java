package com.ideas2it.ems.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.ideas2it.ems.dao.EmployeeDao;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.dto.CreateEmployeeDto;
import com.ideas2it.ems.mapper.EmployeeMapper;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.SalaryAccount;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Project;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Department department;

    private Employee employee;

    private EmployeeDto employeeDto;

    @BeforeEach
    void setUp() {
        department = new Department();
        department.setDepartmentId(1);
        department.setDepartmentName("Admin");
        SalaryAccount salaryAccount = new SalaryAccount();
        salaryAccount.setAccountNumber(532186323);
        salaryAccount.setIfscCode("IOB4345767");
        Set<Project> projects = new HashSet<>();
        Project project = new Project();
        project.setProjectId(1);
        project.setProjectName("FrontEnd");
        projects.add(project);
        String date = "2001-09-09";
        LocalDate dateOfBirth = LocalDate.parse(date);
        employee = new Employee(1, "Jeevitha", dateOfBirth,
                department, salaryAccount, "9087654321",
                "jeevitha@gmail.com", 2, false, projects);
        employeeDto = EmployeeMapper.convertEntityToDto(employee);
        employee.setProjects(projects);
    }

    @Test
    public void testAddEmployee() {
        when(employeeDao.save(any(Employee.class))).thenReturn(employee);
        CreateEmployeeDto savedEmployeeDto = employeeService.addEmployee(employeeDto);
        assertEquals("Jeevitha", savedEmployeeDto.getName());
    }

    @Test
    public void testGetEmployees() {
        when(employeeDao.findByIsDeletedFalse()).thenReturn(List.of(employee));
        List<EmployeeDto> Employees = employeeService.getEmployees();
        assertThat(Employees).isNotNull();
        assertThat(Employees.size()).isEqualTo(1);
    }

    @Test
    public void testGetEmployeeById() {
        when(employeeDao.findByEmployeeIdAndIsDeletedFalse(1)).thenReturn(employee);
        EmployeeDto EmployeeDto = employeeService.getEmployeeById(employee.getEmployeeId());
        assertThat(EmployeeDto).isNotNull();
    }

    @Test
    public void testGetProjectsByEmployee() {
        when(employeeDao.findByEmployeeIdAndIsDeletedFalse(1)).thenReturn(employee);
        Employee savedEmployee = EmployeeMapper.convertDtoToEntity(employeeService.getEmployeeById(1));
        Set<Project> projects = employee.getProjects();
        assertEquals(projects.size(),1);
    }
}

