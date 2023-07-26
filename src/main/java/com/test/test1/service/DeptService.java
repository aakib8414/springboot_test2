package com.test.test1.service;
import com.test.test1.entity.Department;
import com.test.test1.repository.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

import static java.lang.Thread.sleep;

@AllArgsConstructor
@Slf4j
@Service
public class DeptService implements Serviice {

    private DepartmentRepository departmentRepository;
  @Autowired
  private ModelMapper mapper;
    @CacheEvict(value = "departments", allEntries = true)
    @Override
    public Department saveDepartment(Department department) {

        return departmentRepository.save(department);
    }
    @Cacheable(value = "department", key = "#departmentId")
    @Override
    public Department getDepartmentById(Long departmentId) {

        return departmentRepository.findById(departmentId).orElseThrow(() -> new EntityNotFoundException("Department not exist for id:" + departmentId));

    }


    @CacheEvict(value = "departments", allEntries = true)
    @Override
    public Department updateDepartmentById(Department department, long id) {

        Department updateDept = departmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Department not exist for id " + id));
//        updateDept.setDepartmentName(department.getDepartmentName());
//        updateDept.setDepartmentCode(department.getDepartmentCode());
//        updateDept.setDepartmentAddress(department.getDepartmentAddress());
//        updateDept = departmentRepository.save(updateDept);
        this.mapper.map(department,updateDept);
        updateDept.setId(id);
        updateDept = departmentRepository.save(updateDept);
        return updateDept;
    }

    @Cacheable(value = "departments")
    @Override
    public List<Department> getAllDepartments() throws InterruptedException {
        sleep(1000);
        log.info("Calling service to get Data from db ");
        return departmentRepository.findAll();
    }

    @Override
    public List<Department> getDeptByName(String name) {
        return departmentRepository.findByDepartmentName(name);
    }

    @Override
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }
}