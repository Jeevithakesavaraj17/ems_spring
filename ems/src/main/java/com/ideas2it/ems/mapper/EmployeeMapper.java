package com.ideas2it.ems.mapper;

import com.ideas2it.ems.dto.EmployeeDto;
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
     * @param employeeDto   employee details which we have to convert to employee entity
     * @return employee     employee which we have converted to entity
     */
    public static Employee convertToEntity(EmployeeDto employeeDto) {
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
     * This method is used to convert employeeeEntity to employeeDto
     * @param employee     employee which we have convert to employeeDto
     * @return employeeDto   employee dto which we have converted
     */
    public static EmployeeDto convertToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
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
