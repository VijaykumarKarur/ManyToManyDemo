package com.nexturn.manytomanydemo.model;

import com.nexturn.manytomanydemo.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Employee {
    @Id
    @SequenceGenerator(
            name = "seq_employee",
            sequenceName = "seq_employee",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_employee")
    private Long id;

    private String name;
    private String email;
    private Boolean isDeleted;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "employee_project",
            joinColumns = {
                    @JoinColumn(name = "employee_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "project_id", referencedColumnName = "id")
            }
    )
    private List<Project> projects = new ArrayList<>();

    public static Employee from(EmployeeDTO employeeDTO){
        return Employee
                .builder()
                .id(employeeDTO.getId())
                .name(employeeDTO.getName())
                .email(employeeDTO.getEmail())
                .isDeleted(employeeDTO.getIsDeleted())
                .projects(new ArrayList<>())
                .build();
    }

    public void addProjectToEmployee(Project project){
        this.getProjects().add(project);
    }

    public void removeProjectFromEmployee(Project project){
        this.getProjects().remove(project);
    }
}
