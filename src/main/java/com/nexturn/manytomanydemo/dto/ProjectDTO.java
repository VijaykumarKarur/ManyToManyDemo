package com.nexturn.manytomanydemo.dto;

import com.nexturn.manytomanydemo.model.Employee;
import com.nexturn.manytomanydemo.model.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProjectDTO {
    private Long id;
    private String name;
    private String code;
    private Boolean isDeleted;
    private List<EmployeePlainDTO> employees = new ArrayList<>();

    public static ProjectDTO from(Project project){
        List<Employee> employees = project.getEmployees();
        List<EmployeePlainDTO> employeePlainDTOs = employees.stream().map(EmployeePlainDTO::from).toList();
        return ProjectDTO
                .builder()
                .id(project.getId())
                .name(project.getName())
                .code(project.getCode())
                .isDeleted(project.getIsDeleted())
                .employees(employeePlainDTOs)
                .build();
    }
}
