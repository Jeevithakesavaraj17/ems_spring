package com.ideas2it.ems.service;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import com.ideas2it.ems.dao.ProjectDao;
import com.ideas2it.ems.dto.ProjectDto;
import com.ideas2it.ems.model.Project;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {
    @Mock
    private ProjectDao projectDao;

    @InjectMocks
    private ProjectServiceImplTest projectService;

    private Project project;

    @BeforeEach
    void setUp() {
        project = new Project();
        project.setProjectId(1);
        project.setProjectName("FrontEnd");
    }

    @DisplayName("JUnit test for getAllProjects method")
    @Test
    public void getProjects() {
        when(projectDao.findAll()).thenReturn(List.of(project));
        List<ProjectDto> Projects = projectService.getProjects();
        assertThat(Projects).isNotNull();
        assertThat(Projects.size()).isEqualTo(1);
    }
}
