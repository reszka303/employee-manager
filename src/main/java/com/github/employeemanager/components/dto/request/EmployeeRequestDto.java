package com.github.employeemanager.components.dto.request;

import com.github.employeemanager.components.dto.EmployeeBaseDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class EmployeeRequestDto extends EmployeeBaseDto {

    public EmployeeRequestDto(Long id, String name, String email, String jobTitle, String phone, String imageUrl, String employeeCode) {
        super(id, name, email, jobTitle, phone, imageUrl, employeeCode);
    }

//    public EmployeeRequestDto() {}

}
