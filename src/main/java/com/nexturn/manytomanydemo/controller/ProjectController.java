package com.nexturn.manytomanydemo.controller;

import com.nexturn.manytomanydemo.dto.ProjectDTO;
import com.nexturn.manytomanydemo.model.Project;
import com.nexturn.manytomanydemo.repository.ProjectRepository;
import com.nexturn.manytomanydemo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/projects")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProject(){
        List<Project> projects = projectService.getAllProject();
        List<ProjectDTO> projectDTOs = projects.stream().map(ProjectDTO::from).toList();
        return ResponseEntity.status(HttpStatus.OK).body(projectDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable("id") final Long id){
        Project project = projectService.getProjectById(id);
        ProjectDTO projectDTO = ProjectDTO.from(project);
        return ResponseEntity.status(HttpStatus.OK).body(projectDTO);
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> saveProject(@RequestBody final ProjectDTO projectDTO){
        Project project = projectService.saveProject(Project.from(projectDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(ProjectDTO.from(project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProjectDTO> deleteProject(@PathVariable final Long id){
        Project project = projectService.deleteProject(id);
        return ResponseEntity.status(HttpStatus.OK).body(ProjectDTO.from(project));
    }
}
