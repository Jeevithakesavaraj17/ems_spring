package com.ideas2it.ems.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ideas2it.ems.model.Department;

/**
 * <p>
 *     It performs CRUD operations for department.
 * </p>
 *
 * @author  JeevthaKesavaraj
 */
@Repository
public interface DepartmentDao extends JpaRepository<Department, Integer>{

    /**
     * <p>
     *    Retrieves all the departments by checking their boolean values
     * </p>
     *
     * @return List<Department>  list of departments
     */
    List<Department> findByIsDeletedFalse();

    /**
     * <p>
     *     Retrieves single department by department Id
     * </p>
     *
     * @param departmentId    Id of the department
     * @return department    department details which we have searched
     */
    Department findByDepartmentIdAndIsDeletedFalse(int departmentId);
}
