package com.ideas2it.ems.service;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private ProjectDao projectDao;

    @Override
    public ProjectDto addProject(ProjectDto projectDto) {
        Project project = ProjectMapper.convertDtoToEntity(projectDto);
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
        return ProjectMapper.convertEntityToDto(project);
    }

    @Override
    public boolean isProjectPresent(int projectId) {
        Project project = projectDao.findByProjectIdAndIsDeletedFalse(projectId);
        return null != project;
    }

    @Override
    public ProjectDto updateProject(int projectId, ProjectDto projectDto) {
        Project project = projectDao.findByProjectIdAndIsDeletedFalse(projectId);
        project.setProjectName(project.getProjectName());
        Project savedProject = projectDao.save(project);
        return ProjectMapper.convertEntityToDto(savedProject);
    }

    @Override
    public void deleteProject(int projectId) {
        Project project = projectDao.findByProjectIdAndIsDeletedFalse(projectId);
        project.setDeleted(true);
        projectDao.save(project);
    }

    @Override
    public List<EmployeeDto> getEmployeesByProject(int projectId) {
        Project project = projectDao.findByProjectIdAndIsDeletedFalse(projectId);
        List<Employee> employees = new ArrayList<>(project.getEmployees());
        List<EmployeeDto> employeesDtos = new ArrayList<>();
        for (Employee employee : employees) {
            employeesDtos.add(EmployeeMapper.convertEntityToDto(employee));
        }
        return employeesDtos;
    }
}