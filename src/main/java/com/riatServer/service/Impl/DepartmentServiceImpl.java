package com.riatServer.service.Impl;

import com.riatServer.domain.Department;
import com.riatServer.exception.ServiceException;
import com.riatServer.repo.DepartmentsRepo;
import com.riatServer.service.DepartmentService;
import com.riatServer.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService, EntityService<Department, Long> {
    @Autowired
    DepartmentsRepo departmentRepo;


    @Override
    public List<Department> getAll() {
        return  departmentRepo.findAll();
    }

    @Override
    public Department getById(Long id) {
        return departmentRepo.findById(id).orElse(null);
    }

    @Override
    public Department save(Department department) {
        department.setUpdateDate(LocalDateTime.now());
        return departmentRepo.save(department);
    }

    @Override
    public Department create(Department department) {
        department.setCreateDate(LocalDateTime.now());
        department.setUpdateDate(LocalDateTime.now());
        return departmentRepo.save(department);
    }

    @Override
    public void delete(Long id) throws IOException, ServiceException {
        Department department = getById(id);
        departmentRepo.deleteById(id);
    }
}
