package com.nexturn.manytomanydemo.service;

import com.nexturn.manytomanydemo.exception.EmployeeNotFoundException;
import com.nexturn.manytomanydemo.model.Employee;
import com.nexturn.manytomanydemo.model.Project;
import com.nexturn.manytomanydemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    private final ProjectService projectService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ProjectService projectService){
        this.employeeRepository = employeeRepository;
        this.projectService = projectService;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAllByIsDeleted(false);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository
                .findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return null;
    }

    @Override
    public Employee deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employee.setProjects(new ArrayList<>());
        employee.setIsDeleted(true);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee addProjectToEmployee(Long employeeId, Long projectId) {
        Employee employee = getEmployeeById(employeeId);
        Project project = projectService.getProjectById(projectId);
        employee.addProjectToEmployee(project);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee removeProjectFromEmployee(Long employeeId, Long projectId) {
        Employee employee = getEmployeeById(employeeId);
        Project project = projectService.getProjectById(projectId);
        employee.removeProjectFromEmployee(project);
        return employeeRepository.save(employee);
    }
}
