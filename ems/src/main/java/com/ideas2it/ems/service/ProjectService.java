package com.ideas2it.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Project;

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
     * @param project  project details which we have to add
     * @return Project     If project added, return project object
     */
    Project addProject(Project project);

    /**
     * <p>
     * Get list of projects
     * </p>
     *
     * @return List<Project>    list of projects
     */
    List<Project> getProjects();

    /**
     * <p>
     * Get project from the list of projects using project Id
     * </p>
     *
     * @param projectId   ID of the project
     * @return Project    If project present, return project object or else return null.
     */
    Project getProjectById(int projectId);

    /**
     * <p>
     * Update project name in the database
     * </p>
     *
     * @param projectId  Id of the project who we have to update the details
     * @param project   project details which we have to update
     * @return Project  If project is updated, returns project object
     */
    Project updateProject(int projectId, Project project);

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
    List<Employee> getEmployeesByProject(int projectId);
}