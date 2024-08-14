package com.ideas2it.ems.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 *    This dto represents project details
 *    id - ID of the project
 *    name - Name of the project
 * </p>
 *
 * @author JeevithaKesavaraj
 */

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class ProjectDto {
    private int id;

    @NotBlank(message = "Project name cannot be blank.")
    private String name;
}
