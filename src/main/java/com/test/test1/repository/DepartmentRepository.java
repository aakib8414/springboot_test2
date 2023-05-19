package com.test.test1.repository;

import com.test.test1.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
         List<Department> findByDepartmentName(String deptName);
}