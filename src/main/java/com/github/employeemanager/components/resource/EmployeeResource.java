package com.github.employeemanager.components.resource;

import com.github.employeemanager.components.dto.request.EmployeeRequestDto;
import com.github.employeemanager.components.dto.response.EmployeeResponseDto;
import com.github.employeemanager.components.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    @GetMapping("")
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

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable Long id) {

        EmployeeResponseDto response = service.getEmployeeById(id);

        if (response.getId() != null) {
            return new ResponseEntity<>(response, OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<EmployeeResponseDto> addEmployee(
            @RequestBody EmployeeRequestDto request) {

        if (request.getId() != null) {
            throw new ResponseStatusException(
                    BAD_REQUEST, "Employee shouldn't have id set before saving");
        }

        EmployeeResponseDto response = service.addEmployee(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(
            @PathVariable Long id, @RequestBody EmployeeRequestDto request) {

        if (!request.getId().equals(id)) {
            throw new ResponseStatusException(
                    BAD_REQUEST,
                    "Employee's id should be equal with id in the path resource"
            );
        }

        EmployeeResponseDto response = service.updateEmployee(request);

        return new ResponseEntity<>(response, OK);
    }

}
