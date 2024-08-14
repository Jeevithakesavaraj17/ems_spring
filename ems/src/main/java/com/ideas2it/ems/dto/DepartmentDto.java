package com.ideas2it.ems.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *     This dto represents department details
 *    id - ID of the department
 *    name - Name of the department
 * </p>
 *
 * @author JeevithaKesavaraj
 */

@AllArgsConstructor
@Getter
@Setter
public class DepartmentDto {
    private int id;

    @NotBlank(message = "Department name cannot be blank.")

    private String name;
}
