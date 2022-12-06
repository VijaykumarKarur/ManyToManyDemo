package com.nexturn.manytomanydemo.service;

import com.nexturn.manytomanydemo.model.Project;

import java.util.List;

public interface ProjectService {
    List<Project> getAllProject();
    Project getProjectById(Long id);
    Project saveProject(Project project);
    Project updateProject(Project project);
    Project deleteProject(Long id);
}
