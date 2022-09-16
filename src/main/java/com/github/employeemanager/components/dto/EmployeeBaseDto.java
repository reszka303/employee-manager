package com.github.employeemanager.components.dto;

import lombok.*;

@ToString
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public abstract class EmployeeBaseDto {

    private Long id;

    private String name;

    private String email;

    private String jobTitle;

    private String phone;

    private String imageUrl;

    private String employeeCode;
}
