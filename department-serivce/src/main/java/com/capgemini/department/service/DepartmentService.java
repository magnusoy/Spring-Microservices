package com.capgemini.department.service;

import com.capgemini.department.entity.Department;
import com.capgemini.department.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DepartmentService implements DepartmentServiceInterface{

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {
        log.info("Inside saveDepartment method of DepartmentService");
        return departmentRepository.save(department);
    }

    public Department findDepartmentById(Long departmentId) {
        log.info("Inside findDepartmentById method of DepartmentService");
        return departmentRepository.findByDepartmentId(departmentId);
    }

    public void deleteDepartmentById(Long departmentId) {
        log.info("Inside deleteDepartmentById method of DepartmentService");
        departmentRepository.deleteById(departmentId);
    }

    public Department updateDepartment(Department department) {
        log.info("Inside updateDepartment method of DepartmentService");
        return departmentRepository.save(department);
    }
}
