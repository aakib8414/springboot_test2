package com.test.test1.service;

import com.test.test1.entity.Department;
import com.test.test1.repository.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
class DeptServiceTest {

    @Mock
    private DepartmentRepository repository;
    //    @InjectMocks
   //  private DeptService serviice;
    private Serviice serviice;
    private AutoCloseable autoCloseable;
    private Department department;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        serviice = new DeptService(repository,new ModelMapper());
        department = new Department(Long.valueOf(1), "CSE", "Hyderabad", "TS001");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSaveDepartment() {
        mock(Department.class);
        mock(DepartmentRepository.class);
        when(repository.save(department)).thenReturn(department);
        assertThat(serviice.saveDepartment(department).getDepartmentName()).
                isEqualTo(department.getDepartmentName());
    }

    @Test
    void testGetDepartmentById() {
        mock(Department.class);
        mock(DepartmentRepository.class);
        Exception exception = assertThrows(EntityNotFoundException.class, () -> serviice.getDepartmentById(Long.valueOf(1)));
        assertEquals("Department not exist for id:1", exception.getMessage());
    }

    @Test
    void testGetAllDepartments() throws InterruptedException {
        mock(DepartmentRepository.class);
        mock(Serviice.class);
        when(repository.findAll()).thenReturn(new ArrayList<Department>(Collections.singletonList(department)));
        assertThat(serviice.getAllDepartments().get(0).getId()).isEqualTo(department.getId());
    }

    @Test
    void testGetDeptByName() {
        mock(Department.class);
        mock(DepartmentRepository.class);
        when(repository.findByDepartmentName("CSE")).thenReturn(new ArrayList<Department>(Collections.singletonList(department)));
        List<Department> list = serviice.getDeptByName("CSE");
        assertThat(list.get(0).getDepartmentCode()).isEqualTo(department.getDepartmentCode());
    }

//    @Test
//    void testDeleteBydId() {
//        mock(Department.class);
//        mock(DepartmentRepository.class, Mockito.CALLS_REAL_METHODS);
//        doAnswer(Answers.CALLS_REAL_METHODS).when(repository).deleteById(any());
//        assertThat(serviice.deleteById(Long.valueOf(1))).isEqualTo("Success");
//    }


    @Test
    void testDeleteBydId() {
        mock(Department.class);
        mock(DepartmentRepository.class, Mockito.CALLS_REAL_METHODS);
        serviice.deleteById(Long.valueOf(1));
        verify(repository, times(1)).deleteById(Long.valueOf(1));
    }

    @Test
    void testUpdateDeptById() {

        mock(Department.class);
        mock(DepartmentRepository.class);
        final Department updatedDept = Department.builder().departmentCode("TS004")
                .departmentAddress("Hyderabad")
                .departmentName("CSE")
                .build();
        when(repository.findById(Long.valueOf(1))).thenReturn(Optional.ofNullable(department));
        when(repository.save(department)).thenReturn(department);
        Department updated = serviice.updateDepartmentById(updatedDept, Long.valueOf(1));
        assertThat(updated.getDepartmentAddress()).isEqualTo("Hyderabad");
    }
}