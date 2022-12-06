package com.nexturn.manytomanydemo.service;

import com.nexturn.manytomanydemo.exception.ProjectNotFoundException;
import com.nexturn.manytomanydemo.model.Employee;
import com.nexturn.manytomanydemo.model.Project;
import com.nexturn.manytomanydemo.repository.EmployeeRepository;
import com.nexturn.manytomanydemo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, EmployeeRepository employeeRepository){
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }
    @Override
    public List<Project> getAllProject() {
        return projectRepository.findAllByIsDeleted(false);
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository
                .findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Project project) {
        return null;
    }

    @Override
    public Project deleteProject(Long id) {
        Project project = getProjectById(id);
        project.setIsDeleted(true);
        List<Employee> employees = project.getEmployees();
        for(Employee employee: employees){
            employee.getProjects().remove(project);
            employeeRepository.save(employee);
        }
        return projectRepository.save(project);
    }
}
