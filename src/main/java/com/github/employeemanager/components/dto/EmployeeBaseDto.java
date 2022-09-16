package com.github.employeemanager.components.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class EmployeeBaseDto {

    private Long id;

    private String name;

    private String email;

    private String jobTitle;

    private String phone;

    private String imageUrl;

    private String employeeCode;

}
