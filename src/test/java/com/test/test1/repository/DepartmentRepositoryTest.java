package com.test.test1.repository;

import com.test.test1.entity.Department;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository repository;
    private Department department;

    @AfterEach
    void tearDown() {
        department=null;
        repository.deleteAll();
        System.out.println("After test Case executed");
    }

    @BeforeEach
    void setUp() {
        department = new Department(Long.valueOf(1), "CSE", "Hyderabad", "TS001");
        Department save = repository.save(department);
        System.out.println("Before test Case executed" +save);
    }

    @Test
    void findByDepartmentName() {
        List<Department> list = this.repository.findByDepartmentName("CSE");
        assertThat(list.get(0).getDepartmentName()).
                isEqualTo(department.getDepartmentName());
    }
}