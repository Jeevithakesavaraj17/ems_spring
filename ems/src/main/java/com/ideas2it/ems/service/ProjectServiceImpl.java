package com.ideas2it.ems.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.ems.dao.ProjectDao;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Project;

/**
 * <p>
 * This class implements project service and manages the project and get the project details
 * </p>
 *
 * @author  Jeevithakesavaraj
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectDao projectDao;

    @Override
    public Project addProject(Project project) {
        return projectDao.save(project);
    }

    @Override
    public List<Project> getProjects() {
        return (List<Project>) projectDao.findByIsDeletedFalse();
    }

    @Override
    public Project getProjectById(int projectId) {
        return projectDao.findByProjectIdAndIsDeletedFalse(projectId);
    }

    @Override
    public Project updateProject(int projectId, Project project) {
        Project projectObject = projectDao.findByProjectIdAndIsDeletedFalse(projectId);
        projectObject.setProjectName(project.getProjectName());
        return projectDao.save(projectObject);
    }

    @Override
    public void deleteProject(int projectId) {
        Project project = projectDao.findByProjectIdAndIsDeletedFalse(projectId);
        project.setIsDeleted(true);
        projectDao.save(project);
    }

    @Override
    public List<Employee> getEmployeesByProject(int projectId) {
        Project project = getProjectById(projectId);
        return new ArrayList<>(project.getEmployees());
    }
}