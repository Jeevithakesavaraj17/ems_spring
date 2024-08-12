package com.ideas2it.ems.controller;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.mapper.EmployeeMapper;
import com.ideas2it.ems.model.SalaryAccount;
import com.ideas2it.ems.service.EmployeeService;
import com.ideas2it.ems.service.DepartmentService;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * This class
 * have some methods for getting employee details, add and display employee, employees.
 * </p>
 *
 * @author Jeevithakesavaraj
 */
@Controller
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

    /**
     * <p>
     * Get employee details and add employee data to the list.
     * </p>
     *
     * @param employeeDto  employee details
     * @return savedEmployeeDto    employee details which we have added
     */
    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployeeDetails(@RequestBody EmployeeDto employeeDto) {
        Department department = departmentService.getDepartmentById(employeeDto.getDepartmentId());
        SalaryAccount salaryAccount = new SalaryAccount(employeeDto.getAccountNumber(), employeeDto.getIfscCode());
        Employee employee = EmployeeMapper.convertToEntity(employeeDto);
        employee.setDepartment(department);
        employee.setSalaryAccount(salaryAccount);
        Employee employeeObject = employeeService.addEmployee(employee);
        EmployeeDto savedEmployeeDto = EmployeeMapper.convertToDto(employeeObject);
        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
    }

    /**
     * <p>
     * Display list of employees
     * </p>
     *
     * @return employeesDto   List of employee Dto
     */
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> displayEmployees() {
        List<Employee> employees = employeeService.getEmployees();
        List<EmployeeDto> employeesDto = new ArrayList<>();
        for (Employee employee : employees) {
            employeesDto.add(EmployeeMapper.convertToDto(employee));
        }
        return new ResponseEntity<>(employeesDto, HttpStatus.OK);
    }

    /**
     * <p>
     * Get Id of the employee and update their details.
     * </p>
     *
     * @param employeeId     Id of the employee
     * @return EmployeeDto   employee details which we have updated
     */
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployeeDetails(@PathVariable("id") int employeeId, @RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeService.updateEmployeeDetails(employeeId, EmployeeMapper.convertToEntity(employeeDto));
        return new ResponseEntity<>(EmployeeMapper.convertToDto(employee), HttpStatus.OK);
    }

    /**
     * <p>
     * Get employee Id and delete that Employee.
     * </p>
     *
     * @param employeeId    Id of the employee which we have to delete
     */
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") int employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

}