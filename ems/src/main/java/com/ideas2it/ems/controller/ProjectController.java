package com.ideas2it.ems.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.dto.ProjectDto;
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
@RequestMapping("/api/v1/projects")
public class ProjectController {
    private static final Logger logger = LogManager.getLogger();
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
    public ResponseEntity<ProjectDto> addProject(@Valid @RequestBody ProjectDto projectDto) {
        ProjectDto savedProjectDto = projectService.addProject(projectDto);
        logger.info("Project added successfully :" + savedProjectDto.getName());
        return new ResponseEntity<>(savedProjectDto, HttpStatus.CREATED);
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
        return new ResponseEntity<>(projectService.getProjects(), HttpStatus.OK);
    }

    /**
     * Get project by project Id
     * @param projectId     Id of the project which we have to search
     * @return  ProjectDto   project details which we have searched
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable("id") int projectId) {
        return new ResponseEntity<>(projectService.getProjectById(projectId), HttpStatus.OK);
    }

    /**
     * <p>
     * Update project name by getting project Id and new name for the project
     * </p>
     *
     * @param projectDto       new name for the project
     * @return ProjectDto      details of project which we have updated
     */
    @PatchMapping
    public ResponseEntity<ProjectDto> updateProject(@Valid @RequestBody ProjectDto projectDto) {
        ProjectDto updatedProjectDto = projectService.updateProject(projectDto);
        logger.info("Project name updated successfully for this Id: " + updatedProjectDto.getId());
        return new ResponseEntity<>(updatedProjectDto, HttpStatus.OK);
    }

    /**
     * <p>
     * Delete the project by project Id
     * </p>
     *
     * @param projectId   Id of the project which we have to delete
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable("id") int projectId) {
        projectService.deleteProject(projectId);
        logger.info("Project deleted successfully: {}", projectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * <p>
     *     Get list of employees in the particular project
     * </p>
     *
     * @param projectId     Id of the project which we want list of employees
     * @return List<Employee>   list of employees in particular project
     */
    @GetMapping("/employees/{id}")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByProject(@PathVariable("id") int projectId) {
        return new ResponseEntity<>(projectService.getEmployeesByProject(projectId), HttpStatus.OK);
    }
}