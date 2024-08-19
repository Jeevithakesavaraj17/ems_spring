package com.ideas2it.ems.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.ems.dto.CreateEmployeeDto;
import com.ideas2it.ems.dao.EmployeeDao;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.mapper.DepartmentMapper;
import com.ideas2it.ems.mapper.EmployeeMapper;
import com.ideas2it.ems.mapper.ProjectMapper;
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
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ProjectService projectService;

    @Override
    public CreateEmployeeDto addEmployee(EmployeeDto employeeDto) {
        SalaryAccount salaryAccount = new SalaryAccount(employeeDto.getAccountNumber(), employeeDto.getIfscCode());
        Employee employee = EmployeeMapper.convertDtoToEntity(employeeDto);
        employee.setSalaryAccount(salaryAccount);
        Employee savedEmployee = employeeDao.save(employee);
        return EmployeeMapper.convertEntityToCreateEmployeeDto(savedEmployee);
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = employeeDao.findByIsDeletedFalse();
        List<EmployeeDto> employeesDto = new ArrayList<>();
        for (Employee employee : employees) {
            employeesDto.add(EmployeeMapper.convertEntityToDto(employee));
        }
        return employeesDto;
    }

    @Override
    public EmployeeDto getEmployeeById(int employeeId) {
        Employee employee = employeeDao.findByEmployeeIdAndIsDeletedFalse(employeeId);
        if (null == employee) {
            logger.warn("No Employee Found in this Id :{}", employeeId);
            throw new NoSuchElementException("Employee is not found in this Id: " + employeeId);
        }
        return EmployeeMapper.convertEntityToDto(employee);
    }

    @Override
    public EmployeeDto updateEmployeeDetails(EmployeeDto employeeDto) {
        Employee employee = employeeDao.findByEmployeeIdAndIsDeletedFalse(employeeDto.getId());
        employee.setEmployeeName(employeeDto.getName());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setMailId(employeeDto.getMailId());
        employee.setExperience(employeeDto.getExperience());
        SalaryAccount salaryAccount = employee.getSalaryAccount();
        salaryAccount.setAccountNumber(employeeDto.getAccountNumber());
        salaryAccount.setIfscCode(employeeDto.getIfscCode());
        employee.setSalaryAccount(salaryAccount);
        return EmployeeMapper.convertEntityToDto(employeeDao.save(employee));
    }

    @Override
    public void deleteEmployee(int employeeId) {
        Employee employee = employeeDao.findByEmployeeIdAndIsDeletedFalse(employeeId);
        employee.setDeleted(true);
        employeeDao.save(employee);
    }

    @Override
    public EmployeeDto assignProjectToEmployee(int employeeId, int projectId) {
        Employee employee = employeeDao.findByEmployeeIdAndIsDeletedFalse(employeeId);
        Project project = ProjectMapper.convertDtoToEntity(projectService.getProjectById(projectId));
        if (null == employee) {
            if (null == project) {
                logger.warn("No project found in this Id: {}", projectId);
                throw new NoSuchElementException("Project is not found: " + projectId);
            }
            logger.warn("No Employee found{}", employeeId);
        } else {
            for (Project projectObject : employee.getProjects()) {
                if (projectObject.getProjectId() == projectId) {
                    logger.warn("Employee{}is already assigned in this project {}", employeeId, projectId);
                    throw new EmployeeException("Employee" + employeeId + " is already assigned in this project " + projectId);
                }
             }
        }
        employee.getProjects().add(project);
        Employee savedEmployee = employeeDao.save(employee);
        return EmployeeMapper.convertEntityToDto(savedEmployee);
    }
}