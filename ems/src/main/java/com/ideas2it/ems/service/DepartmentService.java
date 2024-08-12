package com.ideas2it.ems.service;

import java.util.List;

import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;

import org.springframework.stereotype.Service;

/**
 * <p>
 * This interface contains method declaration for add, get, update, delete the
 * department details and get employees in the particular department.
 * </p>
 *
 * @author Jeevithakesavaraj
 */
@Service
public interface DepartmentService {

    /**
     * <p>
     * Add Department details
     * </p>
     *
     * @param department          department details
     * @return Department         If department added, return department
     */
    Department addDepartment(Department department);

    /**
     * <p>
     * Get list of Departments
     * </p>
     *
     * @return List<Department>    list of Departments
     */
    List<Department> getAllDepartments();

    /**
     * <p>
     * Get department by departmentId
     * </p>
     *
     * @param departmentId ID of the department
     * @return department      ID department is present, return true or else return false.
     */
    Department getDepartmentById(int departmentId);

    /**
     * <p>
     * Update department name in the database
     * </p>
     *
     * @param departmentId Id of the department whhich we have to update
     * @param department   DepartmentName
     * @return Department  If department is updated, returns department object
     */
    Department updateDepartment(int departmentId, Department department);

    /**
     * <p>
     * Delete department
     * </p>
     *
     * @param id   department id
     */
    void deleteDepartment(int id);

    /**
     * <p>
     *     Get the list of employees for the particular department
     * </p>
     *
     * @param departmentId    ID of the department which we have to get the employees
     * @return List<Employee>   List of employees in that department
     */
    List<Employee> getEmployeesByDepartment(int departmentId);
}