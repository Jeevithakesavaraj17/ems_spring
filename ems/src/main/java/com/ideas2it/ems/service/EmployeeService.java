package com.ideas2it.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Project;

/**
 * <p>
 *     This interface consists of method declaration for add, get, update, delete the employee details.
 * </p>
 *
 * @author Jeevithakesavaraj
 */
@Service
public interface EmployeeService {

    /**
     * <p>
     *     Add employee details
     * </p>
     *
     * @param employee        employee details which we have to add
     * @return Employee        If employee is added, return employee object
     */
    EmployeeDto addEmployee(EmployeeDto employeeDto);

    /**
     * <p>
     *     Get list of employees
     * </p>
     *
     * @return List<Employee>    list of employees
     */
    List<EmployeeDto> getEmployees();

    /**
     * <p>
     *     Get employee details by employeeId
     * </p>
     *
     * @param employeeId     ID of the employee
     * @return Employee      If employee present, return employee details
     *                      else return null.
     */
    Employee getEmployeeById(int employeeId);

    /**
     * <p>
     *     Update employee name by their employee id
     * </p>
     *
     * @param employeeId   ID of the employee who we have to update
     * @param employee      employee details which we have to update
     * @return Employee     If employee detail is updated, return employee
     */
    EmployeeDto updateEmployeeDetails(int employeeId, EmployeeDto employeeDto);

    /**
     * <p>
     * Delete employee by their employeeId
     * </p>
     *
     * @param employeeId   ID of the employee
     */
    void deleteEmployee(int employeeId);

    /**
     * <p>
     *     Assign project to employee
     * </p>
     *
     *
     */
    Employee assignProjectToEmployee(int employeeId, Project project);
}