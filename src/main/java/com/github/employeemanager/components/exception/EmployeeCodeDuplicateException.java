package com.github.employeemanager.components.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "The same employee code already exists")
public class EmployeeCodeDuplicateException extends RuntimeException {

}
