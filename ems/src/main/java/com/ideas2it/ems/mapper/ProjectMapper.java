package com.ideas2it.ems.mapper;

import com.ideas2it.ems.dto.ProjectDto;
import com.ideas2it.ems.model.Project;

/**
 * <p>
 *     This class have methods for conversion of ProjectDto to ProjectEntity and ProjectEntity to ProjectDto
 * </p>
 *
 * @author JeevithaKesavaraj
 */
public class ProjectMapper {

    /**
     * <p>
     *     This method is for conversion of ProjectDto to ProjectEntity
     * </p>
     * @param projectDto   project details which we have to convert to project entity
     * @return project   object which we have converted to entity
     */
    public static Project convertToEntity(ProjectDto projectDto) {
        Project project = new Project();
        project.setProjectName(projectDto.getName());
        return project;
    }

    /**
     * This method is used to convert projectEntity to projectDto
     * @param project     project which we have convert to projectDto
     * @return projectDto   project dto which we have converted
     */
    public static ProjectDto convertToDto(Project project) {
        ProjectDto projectDto = new ProjectDto(project.getProjectId(),project.getProjectName());
        return projectDto;
    }
}
