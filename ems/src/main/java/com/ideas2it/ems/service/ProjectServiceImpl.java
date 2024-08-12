package com.ideas2it.ems.service;

import java.util.List;

import com.ideas2it.ems.dao.ProjectDao;
import com.ideas2it.ems.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return (List<Project>) projectDao.findAll();
    }

    @Override
    public Project getProjectById(int projectId) {
        return projectDao.findById(projectId).orElseThrow(() -> new IllegalArgumentException(("Project not found with ID : " + projectId)));
    }

    @Override
    public Project updateProject(int projectId, Project project) {
        Project projectObject = projectDao.findById(projectId).orElseThrow(() -> new IllegalArgumentException(("Project not found with ID:" + projectId)));
        projectObject.setProjectName(project.getProjectName());
        return projectDao.save(projectObject);
    }

    @Override
    public void deleteProject(int projectId) {
        Project project = projectDao.findById(projectId).orElseThrow(() -> new IllegalArgumentException(("Project not found with ID:" + projectId)));
        project.setIsDeleted(true);
        projectDao.save(project);
    }
}