package com.capgemini.department.service;

import com.capgemini.department.entity.Department;

public interface DepartmentServiceInterface {

    Department saveDepartment(Department department);

    Department findDepartmentById(Long departmentId);

    void deleteDepartmentById(Long departmentId);

    Department updateDepartment(Department department);
}
