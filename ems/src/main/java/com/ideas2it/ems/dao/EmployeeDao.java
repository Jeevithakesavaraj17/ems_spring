package com.ideas2it.ems.dao;

import com.ideas2it.ems.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *     It performs CRUD operations for employee.
 * </p>
 *
 * @author  JeevthaKesavaraj
 */
@Repository
public interface EmployeeDao extends CrudRepository<Employee, Integer>{
}
