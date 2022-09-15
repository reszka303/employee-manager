package com.github.employeemanager.components.repository;

import com.github.employeemanager.components.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeService extends JpaRepository<Employee, Long> {
}
