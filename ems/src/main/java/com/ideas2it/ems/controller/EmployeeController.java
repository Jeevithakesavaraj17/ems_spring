package com.ideas2it.ems.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
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

import com.ideas2it.ems.dto.CreateEmployeeDto;
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
    private static final Logger logger = LogManager.getLogger();
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
     * @return savedEmployeeDto    {@link CreateEmployeeDto} employee details which we have added
     */
    @PostMapping
    public ResponseEntity<CreateEmployeeDto> addEmployeeDetails(@Valid @RequestBody EmployeeDto employeeDto) {
        CreateEmployeeDto savedEmployeeDto = employeeService.addEmployee(employeeDto);
        logger.info("Employee added successfully :{}", employeeDto.getName());
        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
    }

    /**
     * <p>
     * Display list of employees
     * </p>
     *
     * @return employeeDtos   {@link EmployeeDto} List of employee Dto
     */
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> displayEmployees() {
        List<EmployeeDto> employeeDtos = employeeService.getEmployees();
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }

    /**
     * <p>
     *     Get Employee by employee Id
     * </p>
     *
     * @param employeeId     Id of the employee to be searched
     * @return EmployeeDto    {@link EmployeeDto}
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") int employeeId) {
        return new ResponseEntity<>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    /**
     * <p>
     *      Get Id of the employee and update their details.
     * </p>
     *
     * @param employeeDto     {@link EmployeeDto}
     * @return EmployeeDto   {@link EmployeeDto} employee details which we have updated
     */
    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployeeDetails(@Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto updatedEmployeeDto = employeeService.updateEmployeeDetails(employeeDto);
        logger.info("Employee details updated successfully for this Id: {}", employeeDto.getId());
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
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") int employeeId) {
        employeeService.deleteEmployee(employeeId);
        logger.info("Employee deleted successfully: {}", employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * <p>
     *     This method is for add project to employee
     * </p>
     * @param employeeId     Id of the employee to whom we have to assign the project
     * @param projectId      Id of the project
     * @return  employeeDto   {@link EmployeeDto}
     */
    @PutMapping("/{employeeId}/project/{projectId}")
    public ResponseEntity<EmployeeDto> addProjectToEmployee(@PathVariable int employeeId, @PathVariable int projectId) {
        EmployeeDto employeeDto = employeeService.assignProjectToEmployee(employeeId, projectId);
        logger.info("Employee{}is assigned in project{}", employeeId, projectId);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

}