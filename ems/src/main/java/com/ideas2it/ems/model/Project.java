package com.ideas2it.ems.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;


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
    private boolean isDeleted = false;

    @ManyToMany(mappedBy = "projects", fetch = FetchType.EAGER)
    private Set<Employee> employees;
    
    public Project() {}

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
   
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) { 
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Project Id : " + projectId
                + "\nProject name : " + projectName;
    }

}