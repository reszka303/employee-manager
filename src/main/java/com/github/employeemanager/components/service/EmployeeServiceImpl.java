package com.github.employeemanager.components.service;

import com.github.employeemanager.components.domain.Employee;
import com.github.employeemanager.components.dto.request.EmployeeRequestDto;
import com.github.employeemanager.components.dto.response.EmployeeResponseDto;
import com.github.employeemanager.components.exception.EmployeeCodeDuplicateException;
import com.github.employeemanager.components.exception.EmployeeNotFoundException;
import com.github.employeemanager.components.mapper.EmployeeMapper;
import com.github.employeemanager.components.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        return repository.findAll().stream().map(EmployeeMapper::toResponse).toList();
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Long id) {

        Optional<Employee> byId = repository.findById(id);
        byId.orElseThrow( () -> new EmployeeNotFoundException("No employee with " + id + " id"));

        Employee employee = byId.get();

        return EmployeeMapper.toResponse(employee);
    }

    @Override
    public List<EmployeeResponseDto> getEmployeeByName(String name) {

        return repository
                .findAll()
                .stream()
                .filter(employee -> employee.getName().toLowerCase().contains(name.toLowerCase()))
                .map(EmployeeMapper::toResponse)
                .toList();
    }

    @Override
    public EmployeeResponseDto addEmployee(EmployeeRequestDto request) {

        Optional<Employee> byEmployeeCode = repository.findEmployeeByEmployeeCode(request.getEmployeeCode());
        byEmployeeCode.ifPresent(ifEmployeeCodeDuplicateIs -> {
            throw new EmployeeCodeDuplicateException();
        });

        Employee employee = EmployeeMapper.toEntity(request);
        repository.save(employee);

        return EmployeeMapper.toResponse(employee);
    }

    @Override
    public EmployeeResponseDto updateEmployee(EmployeeRequestDto request) {

        Optional<Employee> byId = repository.findById(request.getId());
        byId.orElseThrow( () ->  {
            throw new EmployeeNotFoundException("No employee with " + request.getId() + " id");
        });

        Employee employee = EmployeeMapper.toEntity(request);
        repository.save(employee);

        return EmployeeMapper.toResponse(employee);
    }

    @Override
    public void deleteEmployee(Long id) {

        Optional<Employee> byId = repository.findById(id);
        byId.orElseThrow( () -> {
            throw new EmployeeNotFoundException("No employee with " + id + " id");
        });

        repository.deleteById(id);
    }

}
