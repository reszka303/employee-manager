package com.github.employeemanager.components.resource;

import com.github.employeemanager.components.dto.response.EmployeeResponseDto;
import com.github.employeemanager.components.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/employees")
public class EmployeeResource {

    private final EmployeeService service;

    @Autowired
    public EmployeeResource(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getEmployees(
            @RequestParam(required = false) String name) {

        List<EmployeeResponseDto> users;

        if (name != null) {
            users = service.getEmployeeByName(name);
        } else {
           users = service.getAllEmployees();
        }

        return new ResponseEntity<>(users, OK);
    }

}
