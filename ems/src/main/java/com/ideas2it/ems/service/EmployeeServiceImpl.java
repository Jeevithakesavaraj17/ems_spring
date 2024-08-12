package com.ideas2it.ems.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.ems.dao.EmployeeDao;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.mapper.EmployeeMapper;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Project;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.SalaryAccount;

/**
 * <p>
 *      This class implements employee service and have methods for add, get, update, delete the employee details.
 * </p>
 *
 * @author Jeevithakesavaraj
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Department department = departmentService.getDepartmentById(employeeDto.getDepartmentId());
        SalaryAccount salaryAccount = new SalaryAccount(employeeDto.getAccountNumber(), employeeDto.getIfscCode());
        Employee employee = EmployeeMapper.convertToEntity(employeeDto);
        employee.setDepartment(department);
        employee.setSalaryAccount(salaryAccount);
        return EmployeeMapper.convertToDto(employeeDao.save(employee));
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = employeeDao.findByIsDeletedFalse();
        List<EmployeeDto> employeesDto = new ArrayList<>();
        for (Employee employee : employees) {
            employeesDto.add(EmployeeMapper.convertToDto(employee));
        }
        return employeesDto;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        return employeeDao.findByEmployeeIdAndIsDeletedFalse(employeeId);
    }

    @Override
    public EmployeeDto updateEmployeeDetails(int employeeId, EmployeeDto employeeDto) {
        Employee employee = employeeDao.findByEmployeeIdAndIsDeletedFalse(employeeId);
        employee.setEmployeeName(employeeDto.getName());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setMailId(employeeDto.getMailId());
        employee.setExperience(employeeDto.getExperience());
        return EmployeeMapper.convertToDto(employeeDao.save(employee));
    }

    @Override
    public void deleteEmployee(int employeeId) {
        Employee employee = employeeDao.findByEmployeeIdAndIsDeletedFalse(employeeId);
        employee.setIsDeleted(true);
        employeeDao.save(employee);
    }

    @Override
    public Employee assignProjectToEmployee(int employeeId, Project project) {
        Employee employee = getEmployeeById(employeeId);
        employee.getProjects().add(project);
        return employeeDao.save(employee);
    }
}