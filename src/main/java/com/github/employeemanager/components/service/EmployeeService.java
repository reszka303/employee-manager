package com.github.employeemanager.components.service;

import com.github.employeemanager.components.dto.request.EmployeeRequestDto;
import com.github.employeemanager.components.dto.response.EmployeeResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    List<EmployeeResponseDto> getAllEmployees();

    EmployeeResponseDto findEmployeeById(Long id);

    EmployeeResponseDto findEmployeeByName(String name);

    EmployeeResponseDto addEmployee(EmployeeRequestDto request);

    EmployeeResponseDto updateEmployee(EmployeeRequestDto request);

    void deleteEmployee(Long id);
}
