package com.ideas2it.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ideas2it.ems.dto.DepartmentDto;
import com.ideas2it.ems.dto.EmployeeDto;

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
     * @param departmentDto          {@link DepartmentDto}
     * @return DepartmentDto         If department added, return department
     */
    DepartmentDto addDepartment(DepartmentDto departmentDto);

    /**
     * <p>
     * Get list of Departments
     * </p>
     *
     * @return List<Department>    list of Departments
     */
    List<DepartmentDto> getAllDepartments();

    /**
     * <p>
     *     Checks if the department is present or not
     * </p>
     *
     * @param departmentId     Id of the department which we have to check
     * @return boolean         If department is present, return true or return false
     */
    boolean isDepartmentPresent(int departmentId);

    /**
     * <p>
     * Get department by departmentId
     * </p>
     *
     * @param departmentId     ID of the department
     * @return department      ID department is present, return true or else return false.
     */
    DepartmentDto getDepartmentById(int departmentId);

    /**
     * <p>
     * Update department name in the database
     * </p>
     *
     * @param departmentDto {@link DepartmentDto}
     * @return DepartmentDto  If department is updated, returns department object
     */
    DepartmentDto updateDepartment(DepartmentDto departmentDto);

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
    List<EmployeeDto> getEmployeesByDepartment(int departmentId);
}