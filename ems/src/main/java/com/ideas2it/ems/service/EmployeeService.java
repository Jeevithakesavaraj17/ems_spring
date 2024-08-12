package com.ideas2it.ems.service;

import java.util.List;

import com.ideas2it.ems.model.Employee;
import org.springframework.stereotype.Service;

/**
 * <p>
 * This interface consists of method declaration for add, get, update, delete the employee details.
 * </p>
 *
 * @author Jeevithakesavaraj
 */
@Service
public interface EmployeeService {

    /**
     * <p>
     * Add employee details
     * </p>
     *
     * @param employee        employee details which we have to add
     * @return Employee        If employee is added, return employee object
     */
    Employee addEmployee(Employee employee);

    /**
     * <p>
     * Get list of employees
     * </p>
     *
     * @return List<Employee>    list of employees
     */
    List<Employee> getEmployees();

    /**
     * <p>
     * Get employee details by employeeId
     * </p>
     *
     * @param employeeId     ID of the employee
     * @return Employee      If employee present, return employee details
     *                      else return null.
     */
    Employee getEmployeeById(int employeeId);

    /**
     * <p>
     * Update employee name by their employee id
     * </p>
     *
     * @param employeeId   ID of the employee who we have to update
     * @param employee      employee details which we have to update
     * @return Employee     If employee detail is updated, return employee
     */
    Employee updateEmployeeDetails(int employeeId, Employee employee);

    /**
     * <p>
     * Delete employee by their employeeId
     * </p>
     *
     * @param employeeId   ID of the employee
     */
    void deleteEmployee(int employeeId);
}