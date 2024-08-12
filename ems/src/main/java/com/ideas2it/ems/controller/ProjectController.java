package com.ideas2it.ems.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.dto.ProjectDto;
import com.ideas2it.ems.mapper.EmployeeMapper;
import com.ideas2it.ems.mapper.ProjectMapper;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Project;
import com.ideas2it.ems.service.ProjectService;

/**
 * <p>
 * This class
 * have some methods for getting project details, add and display employees.
 * </p>
 *
 * @author Jeevithakesavaraj
 */
@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;


    /**
     * <p>
     * Get ProjectId and Project name and add that project details
     * </p>
     *
     * @param projectDto    Name of the project which we have to add
     * @return   ProjectDto  ID and name of theproject which we have added
     */
    @PostMapping
    public ResponseEntity<ProjectDto> addProject(@RequestBody ProjectDto projectDto) {
        Project project = ProjectMapper.convertToEntity(projectDto);
        return new ResponseEntity<>(ProjectMapper.convertToDto(projectService.addProject(project)), HttpStatus.CREATED);
    }

    /**
     * <p>
     * Display list of projects
     * </p>
     *
     * @return List<ProjectDto>   list of project dto
     */
    @GetMapping
    public ResponseEntity<List<ProjectDto>> displayProjects() {
        List<ProjectDto> projectsDto = new ArrayList<>();
        List<Project> projects = projectService.getProjects();
        for (Project project : projects) {
            projectsDto.add(ProjectMapper.convertToDto(project));
        }
        return new ResponseEntity<>(projectsDto, HttpStatus.OK);
    }

    /**
     * Get project by project Id
     * @param projectId     Id of the project which we have to search
     * @return  ProjectDto   project details which we have searched
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable("id") int projectId) {
        return new ResponseEntity<>(ProjectMapper.convertToDto(projectService.getProjectById(projectId)), HttpStatus.OK);
    }

    /**
     * <p>
     * Update project name by getting project Id and new name for the project
     * </p>
     *
     * @param  projectId        Id of the project
     * @param projectDto       new name for the project
     * @return ProjectDto      details of project which we have updated
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable("id") int projectId, @RequestBody ProjectDto projectDto) {
        Project project = ProjectMapper.convertToEntity(projectDto);
        return new ResponseEntity<>(ProjectMapper.convertToDto(projectService.updateProject(projectId, project)), HttpStatus.OK);
    }

    /**
     * <p>
     * Delete the project by project Id
     * </p>
     *
     * @param projectId   Id of the project which we have to delete
     */
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable("id") int projectId) {
        projectService.deleteProject(projectId);
    }

    /**
     * <p>
     *     Get list of employees in the particular project
     * </p>
     *
     * @param projectId     Id of the project which we want list of employees
     * @return List<Employee>   list of employees in particular project
     */
    public ResponseEntity<List<EmployeeDto>> getEmployeesByProject(int projectId) {
        List<EmployeeDto> employeesDto = new ArrayList<>();
        List<Employee> employees = projectService.getEmployeesByProject(projectId);
        for (Employee employee : employees) {
            employeesDto.add(EmployeeMapper.convertToDto(employee));
        }
        return new ResponseEntity<>(employeesDto, HttpStatus.OK);
    }
}