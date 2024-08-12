package com.ideas2it.ems.mapper;

import com.ideas2it.ems.dto.DepartmentDto;
import com.ideas2it.ems.model.Department;

/**
 * <p>
 *     This class have methods for conversion of DepartmentDto to DepartmentEntity and DepartmentEntity to DepartmentDto
 * </p>
 *
 * @author JeevithaKesavaraj
 */
public class DepartmentMapper {

    /**
     * <p>
     *     This method is for conversion of DepartmentDto to DepartmentEntity
     * </p>
     * @param departmentDto   department details which we have to convert to department entity
     * @return department   object which we have converted to entity
     */
    public static Department convertDtoToEntity(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setDepartmentId(departmentDto.getId());
        department.setDepartmentName(departmentDto.getName());
        return department;
    }

    /**
     * This method is used to convert departmentEntity to departmentDto
     * @param department     department which we have convert to departmentDto
     * @return departmentDto   department dto which we have converted
     */
    public static DepartmentDto convertEntityToDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto(department.getDepartmentId(),
                department.getDepartmentName());
        return departmentDto;
    }
}
