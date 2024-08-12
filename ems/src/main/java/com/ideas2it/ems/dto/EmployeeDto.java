package com.ideas2it.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <p>
 *     This dto represents employee details
 *    id - ID of the employee
 *    name - Name of the employee
 *    dateOfBirth - employee's date of birth
 *    age - age of the employee that we have to calcu
 * </p>
 *
 * @author JeevithaKesavaraj
 */

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class EmployeeDto {
    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private String age;
    private int departmentId;
    private String departmentName;
    private String phoneNumber;
    private String mailId;
    private int experience;
    private long accountNumber;
    private String ifscCode;
    private String projects;
}
