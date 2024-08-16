package com.ideas2it.ems.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ideas2it.ems.dao.DepartmentDao;
import com.ideas2it.ems.dto.DepartmentDto;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.mapper.DepartmentMapper;

@Service
public class DepartmentServiceImplTest implements DepartmentTest {

    private DepartmentDao departmentDao;

    public List<DepartmentDto> getDepartments() {
        List<DepartmentDto> departmentDtos = new ArrayList<>();
        List<Department> departments = departmentDao.findAll();
        for (Department department : departments) {
            departmentDtos.add(DepartmentMapper.convertEntityToDto(department));
        }
        return departmentDtos;
    }
}

