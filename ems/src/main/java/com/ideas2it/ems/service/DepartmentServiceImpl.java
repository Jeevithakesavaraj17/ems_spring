package com.ideas2it.ems.service;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.ems.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.ems.dao.DepartmentDao;
import com.ideas2it.ems.dto.DepartmentDto;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.mapper.DepartmentMapper;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;

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
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.convertDtoToEntity(departmentDto);
        return DepartmentMapper.convertEntityToDto(departmentDao.save(department));
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentDao.findByIsDeletedFalse();
        List<DepartmentDto> departmentsDto = new ArrayList<>();
        for (Department department : departments) {
            departmentsDto.add(DepartmentMapper.convertEntityToDto(department));
        }
        return departmentsDto;
    }

    @Override
    public boolean isDepartmentPresent(int departmentId) {
        Department department = departmentDao.findByDepartmentIdAndIsDeletedFalse(departmentId);
        return null != department;
    }

    @Override
    public DepartmentDto getDepartmentById(int departmentId) {
        Department department = departmentDao.findByDepartmentIdAndIsDeletedFalse(departmentId);
        return DepartmentMapper.convertEntityToDto(department);
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        Department departmentObject = departmentDao.findByDepartmentIdAndIsDeletedFalse(departmentDto.getId());
        departmentObject.setDepartmentName(departmentDto.getName());
        return DepartmentMapper.convertEntityToDto(departmentDao.save(departmentObject));
    }

    @Override
    public void deleteDepartment(int id) {
        Department department = departmentDao.findByDepartmentIdAndIsDeletedFalse(id);
        department.setDeleted(true);
        departmentDao.save(department);
    }

    @Override
    public List<EmployeeDto> getEmployeesByDepartment(int departmentId) {
        Department department = departmentDao.findByDepartmentIdAndIsDeletedFalse(departmentId);
        List<Employee> employees = new ArrayList<>(department.getEmployees());
        List<EmployeeDto> employeesDto = new ArrayList<>();
        for (Employee employee : employees) {
            employeesDto.add(EmployeeMapper.convertEntityToDto(employee));
        }
        return employeesDto;
    }
}
