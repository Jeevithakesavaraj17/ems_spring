package com.ideas2it.ems.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ideas2it.ems.model.Project;

/**
 * <p>
 *     It performs CRUD operations for project.
 * </p>
 *
 * @author  JeevthaKesavaraj
 */
@Repository
public interface ProjectDao extends CrudRepository<Project, Integer> {

    /**
     * <p>
     *     Checks if the project name is present or not
     * </p>
     * @param projectName   Name of the project
     * @return boolean     If project name is present, return true or else false
     */
    boolean existsByProjectName(String projectName);

    /**
     * <p>
     *    Retrieves all the projects by checking their boolean values
     * </p>
     *
     * @return List<Project>  list of projects
     */
    List<Project> findByIsDeletedFalse();

    /**
     * <p>
     *     Retrieves single project by project Id
     * </p>
     *
     * @param projectId   Id of the project
     * @return project    project details which we have searched
     */
    Project findByProjectIdAndIsDeletedFalse(int projectId);
}
