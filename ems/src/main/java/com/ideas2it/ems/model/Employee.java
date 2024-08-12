package com.ideas2it.ems.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ideas2it.ems.util.DateUtil;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Set;

/**
 * <p>
 * Employee is a person who is working in the company
 * This class is used for employee details
 * </p>
 *
 * employeeId     Id of the employee
 * employeeName   Name of the employee
 * DateOfBirth    employee's date of birth
 * department     department details where employee have allotted
 * salaryAccount  Salary account details
 * phoneNumber    employee's mobile number
 * mailId         employee's mail id
 * experience     experience of the employee in years
 * Set<Project>   List of projects that employee have assigned
 *
 * @author Jeevithakesavaraj
 */

@Entity
@Table(name = "employee")
public class Employee {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int employeeId;

    @Column(name = "name")
    private String employeeName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    @JsonBackReference
    private Department department;

    @OneToOne(targetEntity = SalaryAccount.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private SalaryAccount salaryAccount;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "mail_id")
    private String mailId;

    @Column(name = "experience")
    private int experience;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "employee_project",
               joinColumns = @JoinColumn(name = "employee_id"),
               inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projects;

    public Employee() {}

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
 
    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setSalaryAccount(SalaryAccount salaryAccount) {
        this.salaryAccount = salaryAccount;
    }
 
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Department getDepartment() {
        return department;
    }

    public SalaryAccount getSalaryAccount() {
        return salaryAccount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMailId() {
        return mailId;
    }

    public int getExperience() {
        return experience;
    }
    
    public boolean getIsDeleted() {
        return isDeleted;
    }

    public Set<Project> getProjects() {
        return projects;
    }
    
    /**
     * Get the set of projects from employee
     * String projectNames Names of the projects where employee has assigned
     */
    public String getProjectDetails() {
        StringBuilder projectDetails = new StringBuilder();
        for (Project project : getProjects()) {
            projectDetails.append(project.getProjectName()).append(", ");
        }
        return projectDetails.toString();
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    /**
     * <p>
     * To calculate age from Date of Birth 
     * </p>
     * @return String  calculate age and return in years and months.
     */
    public String getAge() {
        return DateUtil.calculateDifferenceBetweenDates(getDateOfBirth());
    }

    @Override
    public String toString() {
        return "Employee Id :" + employeeId
              + "\nEmployee Name : " + employeeName 
              + "\nAge : " + getAge()
              + "\nDepartment Name : "
              + department.getDepartmentName()
              + "\nSalary Account Number : "
              + salaryAccount.getAccountNumber()
              + "\nPhone Number : " + phoneNumber
              + "\nMail Id : "+ mailId 
              + "\nExperience : " + experience;
    }
}