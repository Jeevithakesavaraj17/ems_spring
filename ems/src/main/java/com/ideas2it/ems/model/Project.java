package com.ideas2it.ems.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * This class represents Project Details and set of employees who are all in same projectId.
 * projectId    Id of the project 
 * ProjectName   Name of the Project
 * Set<Employee>  Set of employees in the particular project
 * </p>
 *
 * @author Jeevithakesavaraj
 */

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int projectId;

    @Column(name = "name")
    private String projectName;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToMany(mappedBy = "projects", fetch = FetchType.EAGER)
    private Set<Employee> employees;

    @Override
    public String toString() {
        return "Project Id : " + projectId
                + "\nProject name : " + projectName;
    }
}