package com.ideas2it.ems.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ideas2it.ems.dao.DepartmentDao;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * This class implements Department service which have method for add, get, update and delete
 * department details and get employees in the particular department.
 * </p>
 *
 * @author Jeevithakesavaraj
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private final DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }


    @Override
    public Department addDepartment(Department department) {
        return departmentDao.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return (List<Department>) departmentDao.findAll();
    }

    @Override
    public Department getDepartmentById(int departmentId){
        return departmentDao.findById(departmentId).orElseThrow(() -> new IllegalArgumentException(("Department not found with ID:" + departmentId)));
    }

    @Override
    public Department updateDepartment(int departmentId, Department department) {
        Department departmentObject = departmentDao.findById(departmentId).orElseThrow(() -> new IllegalArgumentException(("Department not found with ID:" + departmentId)));
        departmentObject.setDepartmentName(department.getDepartmentName());
        return departmentDao.save(departmentObject);
    }

    @Override
    public void deleteDepartment(int id) {
        Department department = departmentDao.findById(id).orElseThrow(() -> new IllegalArgumentException(("Department not found with ID:" + id)));
        department.setIsDeleted(true);
        departmentDao.save(department);
    }
    @Override
    public List<Employee> getEmployeesByDepartment(int departmentId) {
        Department department = getDepartmentById(departmentId);
        return new ArrayList<>(department.getEmployees());
    }
}
