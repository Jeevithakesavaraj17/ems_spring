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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.ideas2it.ems.dao.ProjectDao;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.dto.ProjectDto;
import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.mapper.ProjectMapper;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Project;
import com.ideas2it.ems.model.SalaryAccount;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {
    @Mock
    private ProjectDao projectDao;

    @InjectMocks
    private ProjectServiceImpl projectService;

    private Employee employee;

    private Department department;

    private Project project;

    private ProjectDto projectDto;
    private ProjectDto updatedProjectDto;

    @BeforeEach
    void setUp() {
        project = new Project();
        project.setProjectId(1);
        project.setProjectName("FrontEnd");
        Set<Project> projects = new HashSet<>();
        projects.add(project);
        projectDto = ProjectMapper.convertEntityToDto(project);
        updatedProjectDto = new ProjectDto(1, "Backend");
        department = new Department();
        department.setDepartmentId(1);
        department.setDepartmentName("Admin");
        SalaryAccount salaryAccount = new SalaryAccount();
        salaryAccount.setAccountNumber(532186323);
        salaryAccount.setIfscCode("IOB4345767");
        String date = "2001-09-09";
        LocalDate dateOfBirth = LocalDate.parse(date);
        employee = new Employee(1, "Jeevitha", dateOfBirth,
                 department, salaryAccount, "9087654321",
                "jeevitha@gmail.com", 2, false, projects);
        Set<Employee> employees = new HashSet<>();
        employees.add(employee);
        project.setEmployees(employees);
    }

    @Test
    public void testAddProjectSuccess() {
        when(projectDao.existsByProjectName("FrontEnd")).thenReturn(false);
        when(projectDao.save(any(Project.class))).thenReturn(project);
        ProjectDto savedProjectDto = projectService.addProject(projectDto);
        assertEquals("FrontEnd", savedProjectDto.getName());
    }

    @Test
    public void testAddProjectFailure() {
        when(projectDao.existsByProjectName("FrontEnd")).thenReturn(true);
        EmployeeException employeeException = assertThrows(EmployeeException.class, ()-> {
            projectService.addProject(projectDto);
        });
        assertEquals("This Project is already exists.", employeeException.getMessage());
    }

    @Test
    public void testGetProjects() {
        when(projectDao.findByIsDeletedFalse()).thenReturn(List.of(project));
        List<ProjectDto> Projects = projectService.getProjects();
        assertThat(Projects).isNotNull();
        assertThat(Projects.size()).isEqualTo(1);
    }

    @Test
    public void testGetProjectById() {
        when(projectDao.findByProjectIdAndIsDeletedFalse(1)).thenReturn(project);
        ProjectDto projectDto = projectService.getProjectById(project.getProjectId());
        assertThat(projectDto).isNotNull();
    }

    @Test
    public void testUpdateProject() {
        when(projectDao.findByProjectIdAndIsDeletedFalse(1)).thenReturn(project);
        when(projectDao.save(any(Project.class))).thenReturn(project);
        ProjectDto savedProjectDto = projectService.updateProject(updatedProjectDto);
        assertThat( savedProjectDto.getName()).isEqualTo("Backend");
    }

    @Test
    public void testGetEmployeesByProjectIdSuccess() {
        when(projectDao.findByProjectIdAndIsDeletedFalse(1)).thenReturn(project);
        List<EmployeeDto> employees = projectService.getEmployeesByProject(1);
        assertThat(employees.size()).isEqualTo(1);
    }
}
