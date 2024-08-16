package com.ideas2it.ems.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ideas2it.ems.dto.DepartmentDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int departmentId;

    @Column(name = "name", unique = true, nullable = false)
    private String departmentName;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    @JsonManagedReference
    Set<Employee> employees;

    @Override
    public String toString() {
        return "Department Id : " + departmentId
                + "\nDepartmentName : " + departmentName;
    }
}