package com.test.test1.controller;

import com.test.test1.entity.Department;
import com.test.test1.service.Serviice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("api/departments")
@Tag(name = "Departments ", description = "Department management APIs")
public class DepartmentController {


    private Serviice service;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        Department savedDepartment = service.saveDepartment(department);
        log.info("Data saved successfully : {}", department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Retrieve a Department by Id",
            description = "Get a Department object by specifying its id. The response is Department object with id, title, description and published status.",
            tags = {"Departments", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Department.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/department/{id}")

    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long departmentId) throws InterruptedException {

//        sleep(1000);
        Department department = service.getDepartmentById(departmentId);
        if (department == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        log.info("department: {}", department);
        return ResponseEntity.ok(department);
    }

    @PutMapping("{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") long id, @Valid @RequestBody Department department) {
        Department updatedDept = service.updateDepartmentById(department, id);
        return ResponseEntity.ok(updatedDept);
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() throws InterruptedException {

        List<Department> departmentList = service.getAllDepartments();
        if (departmentList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(departmentList);
    }

    @GetMapping("/name/{name}")

    public ResponseEntity<List<Department>> getDeptByName(@PathVariable("name") String name) {
        log.info("Message invoked");
        List<Department> deptByName = service.getDeptByName(name);
        return ResponseEntity.ok(deptByName);
    }

}