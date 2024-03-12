package com.myproject.employeedirectory.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.employeedirectory.ApiResponseEntity;
import com.myproject.employeedirectory.entity.Employee;
import com.myproject.employeedirectory.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    public EmployeeService employeeService;

    @GetMapping
    @Operation(summary = "Get all employees", description = "Returns a list of all employees in the system.")
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    // Get Employee by ID
    @GetMapping("/{id}")
    @Operation(summary = "Get employee by ID", description = "Returns all the details of an employee, if exists in the system.")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.findEmployeeById(id);
        if (!employee.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseEntity("Employee not found"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    // Create a New Employee
    @PostMapping
    @Operation(summary = "Create Employee", description = "Creates a new Employee record in the system, if it meets all the requirements")
    public ResponseEntity<?> createEmployee(@Valid @RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseEntity("Employee successfully created with id " + savedEmployee.getEmployeeId()));

    }

    // Update an Existing Employee
    @PutMapping("/{id}")
    @Operation(summary = "Update Employee", description = "Updates an employee record, if it exists in the system.")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id,
            @Valid @RequestBody Employee employeeDetails) {

        Optional<Employee> updatedEmployee = employeeService.updateEmployee(id, employeeDetails);

        if (!updatedEmployee.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseEntity("Employee not found"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseEntity("Employee successfully updated"));
    }

    // Delete an Employee
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Employee", description = "Deletes an employee record, if it exists in the system.")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id)
                ? new ResponseEntity<>(new ApiResponseEntity("Employee successfully deleted"), HttpStatus.OK)
                : new ResponseEntity<>(new ApiResponseEntity("Employee not found"), HttpStatus.NOT_FOUND);
    }
}
