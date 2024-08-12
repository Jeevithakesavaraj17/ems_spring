package com.ideas2it.ems.dao;

import com.ideas2it.ems.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *     It performs CRUD operations for project.
 * </p>
 *
 * @author  JeevthaKesavaraj
 */
@Repository
public interface ProjectDao extends CrudRepository<Project, Integer> {
}
