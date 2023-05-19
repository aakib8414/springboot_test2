package com.test.test1.service;


import com.test.test1.entity.Department;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface Serviice {
    Department saveDepartment(Department department);

    Department getDepartmentById(Long departmentId);

    Department updateDepartmentById(Department department, long id);

    List<Department> getAllDepartments() throws InterruptedException;

    List<Department> getDeptByName(String name);

    void deleteById(Long id);
}