package com.ideas2it.ems.dao;

import com.ideas2it.ems.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *     It performs CRUD operations for department.
 * </p>
 *
 * @author  JeevthaKesavaraj
 */
@Repository
public interface DepartmentDao extends JpaRepository<Department, Integer> {

}
