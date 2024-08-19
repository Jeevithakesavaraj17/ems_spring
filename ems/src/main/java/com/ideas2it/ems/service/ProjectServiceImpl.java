package com.ideas2it.ems.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.ideas2it.ems.exception.EmployeeException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.ems.dao.ProjectDao;
import com.ideas2it.ems.dto.ProjectDto;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.mapper.EmployeeMapper;
import com.ideas2it.ems.mapper.ProjectMapper;
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
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    private ProjectDao projectDao;

    @Override
    public ProjectDto addProject(ProjectDto projectDto) {
        Project project = ProjectMapper.convertDtoToEntity(projectDto);
        if (projectDao.existsByProjectName(projectDto.getName())) {
            throw new EmployeeException("This Project is already exists.");
        }
        Project savedProject = projectDao.save(project);
        return ProjectMapper.convertEntityToDto(savedProject);
    }

    @Override
    public List<ProjectDto> getProjects() {
        List<ProjectDto> projectsDto = new ArrayList<>();
        List<Project> projects = projectDao.findByIsDeletedFalse();
        for (Project project : projects) {
            projectsDto.add(ProjectMapper.convertEntityToDto(project));
        }
        return projectsDto;
    }

    @Override
    public ProjectDto getProjectById(int projectId) {
        Project project = projectDao.findByProjectIdAndIsDeletedFalse(projectId);
        if (null == project) {
            logger.warn("No project found in this Id: {}", projectId);
            throw new NoSuchElementException("Project is not found in this Id: " + projectId);
        }
        return ProjectMapper.convertEntityToDto(project);
    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDto) {
        Project project = projectDao.findByProjectIdAndIsDeletedFalse(projectDto.getId());
        if (null == project) {
            logger.warn("No project found in this Id for updating: {}", projectDto.getId());
            throw new NoSuchElementException("Project is not found: " + projectDto.getId());
        }
        project.setProjectName(project.getProjectName());
        Project savedProject = projectDao.save(project);
        return ProjectMapper.convertEntityToDto(savedProject);
    }

    @Override
    public void deleteProject(int projectId) {
        Project project = projectDao.findByProjectIdAndIsDeletedFalse(projectId);
        if (null == project) {
            logger.warn("No project found in this Id for deleting: {}", projectId);
            throw new NoSuchElementException("Project is not found: " + projectId);
        }
        project.setDeleted(true);
        projectDao.save(project);
    }

    @Override
    public List<EmployeeDto> getEmployeesByProject(int projectId) {
        Project project = projectDao.findByProjectIdAndIsDeletedFalse(projectId);
        if (null == project) {
            logger.warn("No project found in this Id for getting employees: {}", projectId);
            throw new NoSuchElementException("Project is not found: " + projectId);
        }
        List<Employee> employees = new ArrayList<>(project.getEmployees());
        List<EmployeeDto> employeesDto = new ArrayList<>();
        for (Employee employee : employees) {
            employeesDto.add(EmployeeMapper.convertEntityToDto(employee));
        }
        return employeesDto;
    }
}