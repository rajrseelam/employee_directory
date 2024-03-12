package com.myproject.employeedirectory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.myproject.employeedirectory.entity.Employee;
import com.myproject.employeedirectory.repository.EmployeeRepository;
import com.myproject.employeedirectory.service.EmployeeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Employee generateEmployee() {
        Employee employee = new Employee();

        employee.setEmployeeId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmailAddress("john.doe@example.com");
        employee.setPhone("1234567890");
        employee.setBirthDate(LocalDate.of(1990, 1, 1));
        employee.setJobTitle("Software Engineer");
        employee.setDepartment("Engineering");
        employee.setLocation("New York");
        employee.setStartDate(LocalDate.of(2022, 1, 10));

        return employee;
    }

    @Test
    public void testFindEmployeeByIdFound() {
        Employee employee = generateEmployee();
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Optional<Employee> found = employeeService.findEmployeeById(1L);

        assertTrue(found.isPresent());
        assertEquals(employee, found.get());
    }

    @Test
    public void testFindEmployeeByIdNotFound() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Employee> found = employeeService.findEmployeeById(1L);

        assertFalse(found.isPresent());
    }

    @Test
    public void testFindAllEmployees() {
        Employee employee = generateEmployee();
        List<Employee> expectedEmployeeList = new ArrayList<>();
        expectedEmployeeList.add(employee);

        when(employeeRepository.findAll()).thenReturn(expectedEmployeeList);

        List<Employee> foundEmployees = employeeService.findAllEmployees();

        assertEquals(foundEmployees.size(), expectedEmployeeList.size());
    }

    @Test
    public void testFindAllEmployees_NotFound() {
        List<Employee> expectedEmployeeList = new ArrayList<>();

        when(employeeRepository.findAll()).thenReturn(expectedEmployeeList);

        List<Employee> foundEmployees = employeeService.findAllEmployees();

        assertTrue(foundEmployees.isEmpty());
        assertEquals(foundEmployees.size(), expectedEmployeeList.size());
    }

    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Test");

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee savedEmployee = employeeService.saveEmployee(employee);

        assertNotNull(savedEmployee);
        assertEquals("Test", savedEmployee.getFirstName());
    }

    @Test
    public void testUpdateEmployeeFound() {
        Employee existingEmployee = new Employee();
        existingEmployee.setEmployeeId(1L);
        existingEmployee.setFirstName("John");

        Employee updatedDetails = new Employee();
        updatedDetails.setFirstName("Jane");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedDetails);

        Optional<Employee> updatedEmployee = employeeService.updateEmployee(1L, updatedDetails);

        assertTrue(updatedEmployee.isPresent());
        assertEquals("Jane", updatedEmployee.get().getFirstName());
    }

    @Test
    public void testUpdateEmployeeNotFound() {
        Employee updatedDetails = new Employee();
        updatedDetails.setFirstName("Jane");

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Employee> updatedEmployee = employeeService.updateEmployee(1L, updatedDetails);

        assertFalse(updatedEmployee.isPresent());
    }

    @Test
    public void testDeleteEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        doNothing().when(employeeRepository).delete(employee);

        employeeService.deleteEmployee(employee.getEmployeeId());

        verify(employeeRepository, times(1)).delete(employee);
    }
}
