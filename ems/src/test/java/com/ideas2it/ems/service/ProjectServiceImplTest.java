package com.ideas2it.ems.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ideas2it.ems.dao.ProjectDao;
import com.ideas2it.ems.dto.ProjectDto;
import com.ideas2it.ems.model.Project;
import com.ideas2it.ems.mapper.ProjectMapper;

@Service
public class ProjectServiceImplTest implements ProjectTest {

    private ProjectDao projectDao;

    public List<ProjectDto> getProjects() {
        List<ProjectDto> projectDtos = new ArrayList<>();
        Iterable<Project> projects = projectDao.findAll();
        for (Project project : projects) {
            projectDtos.add(ProjectMapper.convertEntityToDto(project));
        }
        return projectDtos;
    }
}

