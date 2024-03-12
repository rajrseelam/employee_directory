package com.myproject.employeedirectory.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @NotBlank(message = "First name is mandatory")
    private String firstName;
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    @NotBlank(message = "Email address is mandatory")
    @Email(message = "Email should be valid")
    private String emailAddress;
    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid phone number format")
    private String phone;
    @PastOrPresent(message = "Birth date cannot be a future date")
    private LocalDate birthDate;
    @NotBlank(message = "Job title is mandatory")
    private String jobTitle;
    @NotBlank(message = "Department is mandatory")
    private String department;
    @NotBlank(message = "Department is mandatory")
    private String location;
    @PastOrPresent(message = "Start Date cannot be a future date")
    private LocalDate startDate;
    private Long managerReporting;
}
