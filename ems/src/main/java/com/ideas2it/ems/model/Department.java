package com.ideas2it.ems.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;


/**
 * <p>
 * Department is nothing but a domain where group of employees working.
 * This class represents department details
 * </p>
 *
 * departmentId   ID of the department
 * departmentName Name of the department
 * Set<Employee>  set of employees in the particular department
 *
 * @author Jeevithakesavaraj
 */

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int departmentId;

    @Column(name = "name")
    private String departmentName;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    @JsonManagedReference
    Set<Employee> employees;

    public Department() {}

    public void setDepartmentId(int id) {
        this.departmentId = id;
    }

    public void setDepartmentName(String name) {
        this.departmentName = name;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department Id : " + departmentId
                + "\nDepartmentName : " + departmentName;
    }

}