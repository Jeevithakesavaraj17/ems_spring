package com.ideas2it.ems.service;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.ems.dao.EmployeeDao;
import com.ideas2it.ems.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * This class implements employee service and have methods for add, get, update, delete the employee details.
 * </p>
 *
 * @author Jeevithakesavaraj
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return (List<Employee>) employeeDao.findAll();
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        return employeeDao.findById(employeeId).orElseThrow(()-> new IllegalArgumentException("Employee is not present with ID:" + employeeId));
    }

    @Override
    public Employee updateEmployeeDetails(int employeeId, Employee employee) {
        Employee employeeObject = employeeDao.findById(employeeId).orElseThrow(()-> new IllegalArgumentException("Employee is not present with ID:" + employeeId));
        employeeObject.setEmployeeName(employee.getEmployeeName());
        employeeObject.setDateOfBirth(employee.getDateOfBirth());
        employeeObject.setPhoneNumber(employee.getPhoneNumber());
        employeeObject.setMailId(employee.getMailId());
        employeeObject.setExperience(employee.getExperience());
        return employeeDao.save(employeeObject);
    }

    @Override
    public void deleteEmployee(int employeeId) {
        Employee employee = employeeDao.findById(employeeId).orElseThrow(()-> new IllegalArgumentException("Employee is not present with ID:" + employeeId));
        employee.setIsDeleted(true);
        employeeDao.save(employee);
    }
}