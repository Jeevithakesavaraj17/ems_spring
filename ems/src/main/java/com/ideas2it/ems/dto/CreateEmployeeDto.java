package com.ideas2it.ems.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 *    This dto represents employee details
 *    id - ID of the employee
 *    name - Name of the employee
 *    age - age of the employee that we have to calculate using util class
 *    department name - name of the department
 *    phone number- employee's phoneNumber
 *    mailId - employee's mail Id
 *    experience - experience of the employee
 * </p>
 *
 * @author JeevithaKesavaraj
 */
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class CreateEmployeeDto {
    private int id;
    private String name;
    private String age;
    private String departmentName;
    private String phoneNumber;
    private String mailId;
    private int experience;
    private String projects;
}
