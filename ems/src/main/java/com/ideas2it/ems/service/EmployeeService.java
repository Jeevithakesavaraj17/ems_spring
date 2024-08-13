package com.ideas2it.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ideas2it.ems.dto.CreateEmployeeDto;
import com.ideas2it.ems.dto.EmployeeDto;

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
     * @param employeeDto       {@link CreateEmployeeDto}
     * @return EmployeeDto        If employee is added, return employee object
     */
    CreateEmployeeDto addEmployee(EmployeeDto employeeDto);

    /**
     * <p>
     *     Get list of employees
     * </p>
     *
     * @return List<EmployeeDto>   {@link EmployeeDto} list of employees
     */
    List<EmployeeDto> getEmployees();

    /**
     * <p>
     *     Get employee details by employeeId
     * </p>
     *
     * @param employeeId     ID of the employee
     * @return EmployeeDto      {@link EmployeeDto}
     */
    EmployeeDto getEmployeeById(int employeeId);

    /**
     * <p>
     *     Checks employee is present or not
     * </p>
     *
     * @param employeeId    Id of the employee to be searched
     * @return boolean      If employee is present, return true or else return false
     */
    boolean isEmployeePresent(int employeeId);

    /**
     * <p>
     *     Update employee name by their employee id
     * </p>
     *
     * @param employeeDto      {@link EmployeeDto}
     * @return EmployeeDto     If employee detail is updated, return employee
     */
    EmployeeDto updateEmployeeDetails(EmployeeDto employeeDto);

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
     * @param employeeId  Id of the employee who we have to assign project
     * @param projectId  Id of the project
     * @return EmployeeDto
     */
    EmployeeDto assignProjectToEmployee(int employeeId, int projectId);
}