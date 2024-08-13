package com.ideas2it.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.service.EmployeeService;
import com.ideas2it.ems.service.DepartmentService;
import com.ideas2it.ems.service.ProjectService;


/**
 * <p>
 * This class
 * have some methods for getting employee details, add and display employee, employees.
 * </p>
 *
 * @author Jeevithakesavaraj
 */
@Controller
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ProjectService projectService;

    /**
     * <p>
     * Get employee details and add employee data to the list.
     * </p>
     *
     * @param employeeDto          {@link EmployeeDto}
     * @return savedEmployeeDto    {@link EmployeeDto} employee details which we have added
     */
    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployeeDetails(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployeeDto = employeeService.addEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
    }

    /**
     * <p>
     * Display list of employees
     * </p>
     *
     * @return employeesDto   {@link EmployeeDto} List of employee Dto
     */
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> displayEmployees() {
        List<EmployeeDto> employeesDto = employeeService.getEmployees();
        return new ResponseEntity<>(employeesDto, HttpStatus.OK);
    }

    /**
     * <p>
     * Get Id of the employee and update their details.
     * </p>
     *
     * @param employeeDto     {@link EmployeeDto}
     * @return EmployeeDto   {@link EmployeeDto} employee details which we have updated
     */
    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployeeDetails(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto updatedEmployeeDto = employeeService.updateEmployeeDetails(employeeDto);
        return new ResponseEntity<>(updatedEmployeeDto, HttpStatus.OK);
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

    /**
     * <p>
     *     This method is for add project to employee
     * </p>
     * @param employeeId     Id of the employee
     * @param projectId      Id of the project
     * @return  employeeDto   {@link EmployeeDto}
     */
    @PutMapping("/{employeeId}/project/{projectId}")
    public ResponseEntity<EmployeeDto> addProjectToEmployee(@PathVariable int employeeId, @PathVariable int projectId) {
        EmployeeDto employeeDto = employeeService.assignProjectToEmployee(employeeId, projectId);
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }

}