package com.nexturn.manytomanydemo.dto;

import com.nexturn.manytomanydemo.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmployeePlainDTO {
    private Long id;
    private String name;
    private String email;
    private Boolean isDeleted;

    public static EmployeePlainDTO from(Employee employee){
        return EmployeePlainDTO
                .builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .isDeleted(employee.getIsDeleted())
                .build();
    }
}
