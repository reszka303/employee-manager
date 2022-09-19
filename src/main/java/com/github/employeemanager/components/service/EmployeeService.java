package com.github.employeemanager.components.service;

import com.github.employeemanager.components.dto.request.EmployeeRequestDto;
import com.github.employeemanager.components.dto.response.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponseDto> getAllEmployees();

    EmployeeResponseDto getEmployeeById(Long id);

    List<EmployeeResponseDto> getEmployeeByName(String name);

    EmployeeResponseDto addEmployee(EmployeeRequestDto request);

    EmployeeResponseDto updateEmployee(EmployeeRequestDto request);

    void deleteEmployee(Long id);
}