package com.myproject.employeedirectory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.employeedirectory.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
