package com.github.employeemanager.components.mapper;

import com.github.employeemanager.components.domain.Employee;
import com.github.employeemanager.components.dto.request.EmployeeRequestDto;
import com.github.employeemanager.components.dto.response.EmployeeResponseDto;

public class EmployeeMapper {

    public static Employee toEntity(EmployeeRequestDto request) {

        Employee entity = new Employee();

        entity.setId(request.getId());
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        entity.setJobTitle(request.getJobTitle());
        entity.setPhone(request.getPhone());
        entity.setImageUrl(request.getImageUrl());
        entity.setEmployeeCode(request.getEmployeeCode());

        return entity;
    }

    public static EmployeeResponseDto toResponse(Employee entity) {

        EmployeeResponseDto response = new EmployeeResponseDto();

        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setEmail(entity.getEmail());
        response.setJobTitle(entity.getJobTitle());
        response.setPhone(entity.getPhone());
        response.setImageUrl(entity.getImageUrl());
        response.setEmployeeCode(entity.getEmployeeCode());

        return response;
    }

}
