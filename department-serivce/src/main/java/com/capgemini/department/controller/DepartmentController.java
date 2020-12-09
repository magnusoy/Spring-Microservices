package com.capgemini.department.controller;

import com.capgemini.department.entity.Department;
import com.capgemini.department.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/{id}")
    public ResponseEntity<Department> findDepartmentById(@PathVariable("id") Long departmentId) {
        log.info("Inside findDepartmentById method of DepartmentController");
        Department department = departmentService.findDepartmentById(departmentId);
        if (departmentService.findDepartmentById(departmentId) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(department);
    }

    @PostMapping("/")
    public ResponseEntity<Department> saveDepartment(@RequestBody  Department department) {
        log.info("Inside saveDepartment method of DepartmentController");
        departmentService.saveDepartment(department);
        URI uri = URI.create("/departments/" + department.getDepartmentId());
        return ResponseEntity.created(uri).body(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department) {
        if (departmentService.findDepartmentById(department.getDepartmentId()) == null) {
            return ResponseEntity.notFound().build();
        }
        Department updatedDepartment = departmentService.updateDepartment(department);
        URI uri = URI.create("/departments/" + updatedDepartment.getDepartmentId());
        return ResponseEntity.created(uri).body(updatedDepartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDepartmentById(@PathVariable("id") Long departmentId) {
        if (departmentService.findDepartmentById(departmentId) == null) {
            return ResponseEntity.notFound().build();
        }
        departmentService.deleteDepartmentById(departmentId);
        return ResponseEntity.ok().build();
    }

}
