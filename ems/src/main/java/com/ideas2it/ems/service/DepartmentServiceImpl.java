package com.ideas2it.ems.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.ideas2it.ems.exception.EmployeeException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.ems.dao.DepartmentDao;
import com.ideas2it.ems.dto.DepartmentDto;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.mapper.DepartmentMapper;
import com.ideas2it.ems.mapper.EmployeeMapper;
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
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.convertDtoToEntity(departmentDto);
        if (departmentDao.existsByDepartmentName(departmentDto.getName())) {
            throw new EmployeeException("This department is already exists.");
        }
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
    public DepartmentDto getDepartmentById(int departmentId) {
        Department department = departmentDao.findByDepartmentIdAndIsDeletedFalse(departmentId);
        if (null == department) {
            logger.warn("No department found for this Id: " + departmentId);
            throw new NoSuchElementException("Department is not found for this Id: " + departmentId);
        }
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
