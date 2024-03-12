package com.myproject.employeedirectory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.employeedirectory.entity.Employee;
import com.myproject.employeedirectory.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    // Find employee by ID
    public Optional<Employee> findEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Save or update employee
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Update employee details
    public Optional<Employee> updateEmployee(Long id, Employee employeeDetails) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setFirstName(employeeDetails.getFirstName());
            employee.setLastName(employeeDetails.getLastName());
            employee.setEmailAddress(employeeDetails.getEmailAddress());
            employee.setPhone(employeeDetails.getPhone());
            employee.setBirthDate(employeeDetails.getBirthDate());
            employee.setJobTitle(employeeDetails.getJobTitle());
            employee.setDepartment(employeeDetails.getDepartment());
            employee.setLocation(employeeDetails.getLocation());
            employee.setStartDate(employeeDetails.getStartDate());
            employee.setManagerReporting(employeeDetails.getManagerReporting());
            return employeeRepository.save(employee);
        });
    }

    // Delete employee by ID
    public boolean deleteEmployee(Long id) {
        return employeeRepository.findById(id).map(employee -> {
            employeeRepository.delete(employee);
            return true;
        }).orElse(false);
    }
}
