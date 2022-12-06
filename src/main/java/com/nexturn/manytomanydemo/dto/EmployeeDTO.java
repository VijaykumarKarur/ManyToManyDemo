package com.nexturn.manytomanydemo.dto;

import com.nexturn.manytomanydemo.model.Employee;
import com.nexturn.manytomanydemo.model.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private Boolean isDeleted;
    private List<ProjectPlainDTO> projects = new ArrayList<>();

    public static EmployeeDTO from(Employee employee){
        List<Project> projects = employee.getProjects();
        List<ProjectPlainDTO> projectPlainDTOS = projects
                .stream()
                .map(ProjectPlainDTO::from)
                .toList();

        return EmployeeDTO
                .builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .isDeleted(employee.getIsDeleted())
                .projects(projectPlainDTOS)
                .build();
    }
}
