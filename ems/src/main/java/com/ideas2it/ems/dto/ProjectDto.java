package com.ideas2it.ems.dto;

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
    private String name;
}
