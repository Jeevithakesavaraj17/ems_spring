package com.ideas2it.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ideas2it.ems.dto.DepartmentDto;

@Service
interface DepartmentTest {

    /**
     * Get all departments.
     *
     * @return List of DepartmentDto  {@link DepartmentDto}
     */
    List<DepartmentDto> getDepartments();
}