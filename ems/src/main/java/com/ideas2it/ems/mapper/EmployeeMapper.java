package com.ideas2it.ems.mapper;

import com.ideas2it.ems.dto.CreateEmployeeDto;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;

/**
 * <p>
 *     This class have methods for conversion of EmployeeDto to EmployeeEntity and EmployeeEntity to EmployeeDto
 * </p>
 *
 * @author JeevithaKesavaraj
 */
public class EmployeeMapper {

    /**
     * <p>
     *     This method is for conversion of EmployeeDto to EmployeeEntity
     * </p>
     * @param employeeDto   {@link EmployeeDto}which we have to convert to employee entity
     * @return employee     which we have converted to entity
     */
    public static Employee convertDtoToEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDto.getId());
        employee.setEmployeeName(employeeDto.getName());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setMailId(employeeDto.getMailId());
        employee.setExperience(employeeDto.getExperience());
        return employee;
    }

    /**
     * This method is used to convert employee Entity to employeeDto
     * @param employee     employee which we have to convert to employeeDto
     * @return employeeDto   {@link EmployeeDto} which we have converted
     */
    public static EmployeeDto convertEntityToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getEmployeeId());
        employeeDto.setName(employee.getEmployeeName());
        employeeDto.setAge(employee.getAge());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        employeeDto.setMailId(employee.getMailId());
        employeeDto.setExperience(employee.getExperience());
        Department department = new Department();
        department.setDepartmentId(employeeDto.getDepartmentId());
        department.setDepartmentName(employeeDto.getDepartmentName());
        employee.setDepartment(department);
        employeeDto.setDepartmentName(employee.getDepartment().getDepartmentName());
        employeeDto.setProjects(employee.getProjectDetails());
        return employeeDto;
    }

    /**
     * This method is used to convert employee Entity to employeeDto
     * @param employee     employee which we have to convert to employeeDto
     * @return employeeDto   {@link CreateEmployeeDto}which we have converted
     */
    public static CreateEmployeeDto convertEntityToCreateEmployeeDto(Employee employee) {
        CreateEmployeeDto employeeDto = new CreateEmployeeDto();
        employeeDto.setId(employee.getEmployeeId());
        employeeDto.setName(employee.getEmployeeName());
        employeeDto.setAge(employee.getAge());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        employeeDto.setMailId(employee.getMailId());
        employeeDto.setExperience(employee.getExperience());
        employeeDto.setDepartmentName(employee.getDepartment().getDepartmentName());
        return employeeDto;
    }
}
