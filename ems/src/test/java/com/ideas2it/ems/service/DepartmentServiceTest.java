package com.ideas2it.ems.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.ideas2it.ems.dao.DepartmentDao;
import com.ideas2it.ems.dto.DepartmentDto;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Project;
import com.ideas2it.ems.model.SalaryAccount;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private DepartmentDao departmentDao;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private Employee employee;

    private Department department;

    private DepartmentDto departmentDto, updatedDepartmentDto;

    @BeforeEach
    void setUp() {
        department = new Department();
        department.setDepartmentId(1);
        department.setDepartmentName("Admin");
        departmentDto = new DepartmentDto(1, "Admin");
        updatedDepartmentDto = new DepartmentDto(1,"HR");
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
        Set<Employee> employees = new HashSet<>();
        employees.add(employee);
        department.setEmployees(employees);
    }

    @Test
    void testAddDepartmentSuccess() {
        when(departmentDao.existsByDepartmentName("Admin")).thenReturn(false);
        when(departmentDao.save(any(Department.class))).thenReturn(department);
        DepartmentDto savedDepartmentDto = departmentService.addDepartment(departmentDto);
        assertEquals("Admin", savedDepartmentDto.getName());
    }

    @Test
    void testAddDepartmentFailure() {
        when(departmentDao.existsByDepartmentName("Admin")).thenReturn(true);
        EmployeeException employeeException = assertThrows(EmployeeException.class, ()-> {departmentService.addDepartment(departmentDto);});
        assertEquals("This department is already exists.", employeeException.getMessage());
    }

    @Test
    void testGetDepartmentsSuccess() {
        when(departmentDao.findByIsDeletedFalse()).thenReturn(List.of(department));
        List<DepartmentDto> departments = departmentService.getAllDepartments();
        assertThat(departments).isNotNull();
        assertThat(departments.size()).isEqualTo(1);
    }

    @Test
    void testGetDepartmentByIdSuccess() {
        when(departmentDao.findByDepartmentIdAndIsDeletedFalse(1)).thenReturn(department);
        DepartmentDto departmentDto = departmentService.getDepartmentById(department.getDepartmentId());
        assertThat(departmentDto).isNotNull();
    }

    @Test
    void testGetDepartmentByIdFailure() {
        when(departmentDao.findByDepartmentIdAndIsDeletedFalse(1)).thenReturn(null);
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, ()-> {departmentService.getDepartmentById(1);});
        assertEquals("Department is not found for this Id: " + 1, noSuchElementException.getMessage());
    }

    @Test
    void testUpdateDepartmentSuccess() {
        when(departmentDao.findByDepartmentIdAndIsDeletedFalse(1)).thenReturn(department);
        when(departmentDao.save(any(Department.class))).thenReturn(department);
        DepartmentDto savedDepartmentDto = departmentService.updateDepartment(updatedDepartmentDto);
        assertEquals("HR", savedDepartmentDto.getName());
    }

    @Test
    void testUpdateDepartmentFailure() {
        when(departmentDao.findByDepartmentIdAndIsDeletedFalse(1)).thenReturn(null);
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, ()-> {departmentService.getDepartmentById(1);});
        assertEquals("Department is not found for this Id: " + 1, noSuchElementException.getMessage());
        when(departmentDao.existsByDepartmentName("Admin")).thenReturn(true);
        EmployeeException employeeException = assertThrows(EmployeeException.class, ()-> {departmentService.addDepartment(departmentDto);});
        assertEquals("This department is already exists.", employeeException.getMessage());
    }

    @Test
    void testGetEmployeesByDepartmentIdSuccess() {
        when(departmentDao.findByDepartmentIdAndIsDeletedFalse(1)).thenReturn(department);
        List<EmployeeDto> employees = departmentService.getEmployeesByDepartment(1);
        assertThat(employees.size()).isEqualTo(1);
    }

    @Test
    void testGetEmployeesByDepartmentIdFailure() {
        when(departmentDao.findByDepartmentIdAndIsDeletedFalse(1)).thenReturn(null);
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, ()-> {departmentService.getDepartmentById(1);});
        assertEquals("Department is not found for this Id: " + 1, noSuchElementException.getMessage());
    }
}
