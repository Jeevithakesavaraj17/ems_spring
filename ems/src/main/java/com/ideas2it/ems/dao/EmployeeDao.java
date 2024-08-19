package com.ideas2it.ems.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ideas2it.ems.model.Employee;

/**
 * <p>
 *     It performs CRUD operations for employee.
 * </p>
 *
 * @author  JeevthaKesavaraj
 */
@Repository
public interface EmployeeDao extends CrudRepository<Employee, Integer> {

    /**
     * <p>
     *    Retrieves all the employees by checking their boolean values
     * </p>
     *
     * @return List<Employee>  list of employees
     */
    List<Employee> findByIsDeletedFalse();

    /**
     * <p>
     *     Retrieves single employee by employee Id with boolean value
     * </p>
     *
     * @param employeeId    Id of the employee
     * @return employee     employee details which we have searched
     */
    Employee findByEmployeeIdAndIsDeletedFalse(int employeeId);

}
