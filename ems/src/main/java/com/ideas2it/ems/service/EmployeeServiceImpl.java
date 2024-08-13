package com.ideas2it.ems.service;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.ems.mapper.DepartmentMapper;
import com.ideas2it.ems.mapper.ProjectMapper;
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

    @Autowired
    private ProjectService projectService;

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Department department = DepartmentMapper.convertDtoToEntity(departmentService.getDepartmentById(employeeDto.getDepartmentId()));
        SalaryAccount salaryAccount = new SalaryAccount(employeeDto.getAccountNumber(), employeeDto.getIfscCode());
        Employee employee = EmployeeMapper.convertDtoToEntity(employeeDto);
        employee.setDepartment(department);
        employee.setSalaryAccount(salaryAccount);
        Employee savedEmployee = employeeDao.save(employee);
        return EmployeeMapper.convertEntityToDto(savedEmployee);
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
        return EmployeeMapper.convertEntityToDto(employeeDao.findByEmployeeIdAndIsDeletedFalse(employeeId));
    }

    @Override
    public EmployeeDto updateEmployeeDetails(EmployeeDto employeeDto) {
        Employee employee = employeeDao.findByEmployeeIdAndIsDeletedFalse(employeeDto.getId());
        employee.setEmployeeName(employeeDto.getName());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setMailId(employeeDto.getMailId());
        employee.setExperience(employeeDto.getExperience());
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
        employee.getProjects().add(project);
        Employee savedEmployee = employeeDao.save(employee);
        return EmployeeMapper.convertEntityToDto(savedEmployee);
    }
}