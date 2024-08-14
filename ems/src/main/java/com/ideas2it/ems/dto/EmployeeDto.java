package com.ideas2it.ems.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <p>
 *    This dto represents employee details
 *    id - ID of the employee
 *    name - Name of the employee
 *    dateOfBirth - employee's date of birth
 *    age - age of the employee that we have to calculate using util class
 *    departmentId - Id of the department
 *    phone number- employee's phoneNumber
 *    mailId - employee's mail Id
 *    experience - experience of the employee
 *    accountNumber - salary account number
 *    ifsc code - ifsc code of the account
 *    projects - list of projects where employee have assigned
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

    @NotBlank
    private String name;

    @Past
    private LocalDate dateOfBirth;
    private String age;
    private int departmentId;

    private String departmentName;

    @NotBlank
    @Size(min = 10, max = 10, message = "Number should contain 10 digits.")
    @Pattern(regexp = "^[6-9]{1}[0-9]{9}$")
    private String phoneNumber;

    @Email
    private String mailId;

    @Min(value = 0)
    @Max(value = 50)
    private int experience;

    private long accountNumber;

    @NotBlank
    private String ifscCode;
    private String projects;
}
