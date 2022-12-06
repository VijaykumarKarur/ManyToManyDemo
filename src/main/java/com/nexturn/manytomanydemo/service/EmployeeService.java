package com.nexturn.manytomanydemo.service;

import com.nexturn.manytomanydemo.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployee();
    Employee getEmployeeById(Long id);
    Employee saveEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    Employee deleteEmployee(Long id);

    Employee addProjectToEmployee(Long employeeId, Long projectId);

    Employee removeProjectFromEmployee(Long employeeId, Long projectId);
}
