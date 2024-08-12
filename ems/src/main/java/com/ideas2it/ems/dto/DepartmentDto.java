package com.ideas2it.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@Setter
public class DepartmentDto {
    private int id;
    private String name;
}
