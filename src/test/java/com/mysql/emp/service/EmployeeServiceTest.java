package com.mysql.emp.service;

import com.mysql.emp.model.Employee;
import com.mysql.emp.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setName("pk");
        employee.setDepartment("IT");
        employee.setSalary(50000.0);

        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);

        Employee saveEmployee = employeeService.saveEmployee(employee);

        assertNotNull(saveEmployee);
        assertEquals("pk", saveEmployee.getName());
        Mockito.verify(employeeRepository, Mockito.times(1)).save(employee);
    }

    @Test
    void testGetEmployeesWithPagination() {
        Page<Employee> employeePage = new PageImpl<>(List.of(new Employee(), new Employee()));
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(employeeRepository.findAll(pageable)).thenReturn(employeePage);

        Page<Employee> result = employeeService.getEmployees(pageable);

        assertEquals(2, result.getContent().size());
        Mockito.verify(employeeRepository, Mockito.times(1)).findAll(pageable);
    }

}
