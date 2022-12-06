package com.nexturn.manytomanydemo.model;

import com.nexturn.manytomanydemo.dto.ProjectDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Project {
    @Id
    @SequenceGenerator(
            name = "seq_project",
            sequenceName = "seq_project",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_project")
    private Long id;

    private String name;
    private String code;
    private Boolean isDeleted;

    @ManyToMany(mappedBy = "projects", fetch = FetchType.EAGER)
    List<Employee> employees = new ArrayList<>();

    public static Project from(ProjectDTO projectDTO){
        return Project
                .builder()
                .id(projectDTO.getId())
                .name(projectDTO.getName())
                .code(projectDTO.getCode())
                .isDeleted(projectDTO.getIsDeleted())
                .employees(new ArrayList<>())
                .build();
    }
}
