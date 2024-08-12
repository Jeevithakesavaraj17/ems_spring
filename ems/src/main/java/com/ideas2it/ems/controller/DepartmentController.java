package com.ideas2it.ems.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.ems.dto.DepartmentDto;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.mapper.DepartmentMapper;
import com.ideas2it.ems.mapper.EmployeeMapper;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;
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
    @Autowired
    private DepartmentService departmentService;

    /**
     * <p>
     *      Add the department to the database
     * </p>
     *
     * @param departmentDto      {@link DepartmentDto}
     * @return savedDepartmentDto    Id and name of the department which we have added
     */
    @PostMapping
    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto savedDepartmentDto = departmentService.addDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartmentDto, HttpStatus.CREATED);
    }

    /**
     * <p>
     * get list of departments
     * </p>
     *
     * @return List<DepartmentDto>   list of department Dto
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
        if (null == departmentDto) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    /**
     * <p>
     * Update department name by getting department Id and new name for the department
     * </p>
     *
     * @param departmentId    Id of the department
     * @param departmentDto   New name for the department
     * @return DepartmentDto   Id and name of the department which we have updated
     */
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") int departmentId, @RequestBody DepartmentDto departmentDto) {
        return new ResponseEntity<>(departmentService.updateDepartment(departmentId,departmentDto), HttpStatus.OK);
    }

    /**
     * <p>
     * Delete the department by department Id
     * </p>
     *
     * @param departmentId     Id of the department
     */
    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable("id") int departmentId) {
            departmentService.deleteDepartment(departmentId);
    }

    /**
     * <p>
     *    Get list of employees for the department by departmentId
     * </p>
     * @param departmentId    Id of the department
     * @return   List<EmployeeDto>  list of employees for the particular department
     */
    @GetMapping("/employees/{id}")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByDepartment(@PathVariable("id") int departmentId){
        return new ResponseEntity<>(departmentService.getEmployeesByDepartment(departmentId), HttpStatus.OK);
    }

}
