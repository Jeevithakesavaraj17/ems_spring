package com.ideas2it.ems.controller;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.ems.dto.DepartmentDto;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.mapper.DepartmentMapper;
import com.ideas2it.ems.mapper.EmployeeMapper;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * This class have methods for get department details and
 * displaying the details of department and list of departments.
 * </p>
 *
 * @author Jeevithakesavaraj
 */
@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    /**
     * <p>
     * Add the department to the database
     * </p>
     *
     * @param departmentDto     Name of the department
     * @return savedDepartmentDto    Id and name of the department which we have added
     */
    @PostMapping
    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentDto departmentDto) {
        Department department = DepartmentMapper.convertToEntity(departmentDto);
        DepartmentDto savedDepartmentDto = DepartmentMapper.convertToDto(departmentService.addDepartment(department));
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
        List<DepartmentDto> departmentsDto = new ArrayList<>();
        List<Department> departments = departmentService.getAllDepartments();
        for (Department department : departments) {
            departmentsDto.add(DepartmentMapper.convertToDto(department));
        }
        return new ResponseEntity<>(departmentsDto, HttpStatus.OK);
    }

    /**
     *  <p>
     *      Get the department by departmentId
     *  </p>
     *
     * @param  departmentId      Id of the department which we have search
     * @return DepartmentDto     Id and name of the department by given departmentId
     */
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") int departmentId) {
        return new ResponseEntity<>(DepartmentMapper.convertToDto(departmentService.getDepartmentById(departmentId)), HttpStatus.OK);
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
        return new ResponseEntity<>(DepartmentMapper.convertToDto(departmentService.updateDepartment(departmentId, DepartmentMapper.convertToEntity(departmentDto))), HttpStatus.OK);
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
        List<Employee> employees = departmentService.getEmployeesByDepartment(departmentId);
        List<EmployeeDto> employeesDto = new ArrayList<>();
        for (Employee employee : employees) {
            employeesDto.add(EmployeeMapper.convertToDto(employee));
        }
        return new ResponseEntity<>(employeesDto, HttpStatus.OK);
    }

}
