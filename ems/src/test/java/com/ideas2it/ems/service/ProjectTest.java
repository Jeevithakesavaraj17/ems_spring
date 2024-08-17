package com.ideas2it.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ideas2it.ems.dto.ProjectDto;

@Service
interface ProjectTest {

    /**
     * Get all Projects.
     *
     * @return List of ProjectDto  {@link ProjectDto}
     */
    List<ProjectDto> getProjects();
}
