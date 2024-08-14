package com.ideas2it.ems.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.ems.dto.DepartmentDto;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.service.DepartmentService;

/**
 * <p>
 *      This class have methods for get department details and displaying the details of department and list of departments.
 * </p>
 *
 * @author Jeevithakesavaraj
 */
@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    private DepartmentService departmentService;

    /**
     * <p>
     *      Add the department to the database
     * </p>
     *
     * @param departmentDto      {@link DepartmentDto}
     * @return savedDepartmentDto   DepartmentDto
     */
    @PostMapping
    public ResponseEntity<DepartmentDto> addDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        DepartmentDto savedDepartmentDto = departmentService.addDepartment(departmentDto);
        logger.info("Department added successfully{}", savedDepartmentDto.getName());
        return new ResponseEntity<>(savedDepartmentDto, HttpStatus.CREATED);
    }

    /**
     * <p>
     *      get list of departments
     * </p>
     *
     * @return List<DepartmentDto>   {@link DepartmentDto} list of department Dto
     */
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getDepartments() {
        return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    /**
     *  <p>
     *      Get the department by departmentId
     *  </p>
     *
     * @param  departmentId      Id of the department which we have search
     * @return DepartmentDto     {@link DepartmentDto}
     */
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") int departmentId) {
        DepartmentDto departmentDto = departmentService.getDepartmentById(departmentId);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    /**
     * <p>
     *      Update department name by getting department Id and new name for the department
     * </p>
     *
     * @param departmentDto   {@link DepartmentDto}
     * @return DepartmentDto   {@link DepartmentDto} which we have updated
     */
    @PatchMapping
    public ResponseEntity<DepartmentDto> updateDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        logger.info("Department name updated successfully for this Id: {}", departmentDto.getId());
        return new ResponseEntity<>(departmentService.updateDepartment(departmentDto), HttpStatus.OK);
    }

    /**
     * <p>
     *      Delete the department by department Id
     * </p>
     *
     * @param departmentId     Id of the department which we want to delete
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable("id") int departmentId) {
        departmentService.deleteDepartment(departmentId);
        logger.info("Department deleted successfully.{}", departmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * <p>
     *    Get list of employees for the department by departmentId
     * </p>
     *
     * @param departmentId    Id of the department which we want employees
     * @return   List<EmployeeDto>  {@link EmployeeDto} list of employees for the particular department
     */
    @GetMapping("/employees/{id}")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByDepartment(@PathVariable("id") int departmentId) {
        return new ResponseEntity<>(departmentService.getEmployeesByDepartment(departmentId), HttpStatus.OK);
    }

}
