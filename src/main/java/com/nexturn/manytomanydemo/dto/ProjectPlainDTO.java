package com.nexturn.manytomanydemo.dto;

import com.nexturn.manytomanydemo.model.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProjectPlainDTO {
    private Long id;
    private String name;
    private String code;
    private Boolean isDeleted;
    public static ProjectPlainDTO from(Project project){
        return ProjectPlainDTO
                .builder()
                .id(project.getId())
                .name(project.getName())
                .code(project.getCode())
                .isDeleted(project.getIsDeleted())
                .build();
    }
}
