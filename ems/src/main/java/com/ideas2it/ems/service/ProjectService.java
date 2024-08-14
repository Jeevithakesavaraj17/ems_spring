package com.ideas2it.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.dto.ProjectDto;

/**
 * <p>
 * This interface consists of method declaration for add, get the project details and employees in the particular project.
 * </p>
 *
 * @author  Jeevithakesavaraj
 */
@Service
public interface ProjectService {
    /**
     * <p>
     * Add project to the list
     * </p>
     *
     * @param projectDto  {@link ProjectDto}
     * @return ProjectDto     If project added, return project object
     */
    ProjectDto addProject(ProjectDto projectDto);

    /**
     * <p>
     * Get list of projects
     * </p>
     *
     * @return List<ProjectDto>    {@link ProjectDto} list of projects
     */
    List<ProjectDto> getProjects();

    /**
     * <p>
     * Get project from the list of projects using project Id
     * </p>
     *
     * @param projectId   ID of the project
     * @return ProjectDto    If project present, return project object or else return null.
     */
    ProjectDto getProjectById(int projectId);

    /**
     * <p>
     * Update project name in the database
     * </p>
     *
     * @param projectDto   project details which we have to update
     * @return Project  If project is updated, returns project object
     */
    ProjectDto updateProject(ProjectDto projectDto);

    /**
     * <p>
     * Delete project in project table
     * </p>
     *
     * @param projectId     Id of the project which we have to delete
     */
    void deleteProject(int projectId);

    /**
     * <p>
     *     Get list of employees in the particular project
     * </p>
     *
     * @param projectId   Id of the project
     * @return List<Employee>    list of employees in that project
     */
    List<EmployeeDto> getEmployeesByProject(int projectId);
}