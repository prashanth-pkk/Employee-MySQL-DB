package com.mysql.emp.controller;

import com.mysql.emp.dto.BatchSalaryUpdateRequest;
import com.mysql.emp.model.Employee;
import com.mysql.emp.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        try {
            System.out.println("Received employee: " + employee);
            Employee createdEmployee = employeeService.saveEmployee(employee);
            System.out.println("Created employee: " + createdEmployee);
            return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Employee>> getPaginatedEmployees(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = (Pageable) PageRequest.of(page, size);
        return ResponseEntity.ok(employeeService.getEmployees(pageable));
    }

    @GetMapping("/by-department")
    public ResponseEntity<List<Employee>> getByDepartment(@RequestParam String department) {
        return ResponseEntity.ok(employeeService.getEmployeesByDepartment(department));
    }

    @PutMapping("/update-salaries")
    public ResponseEntity<Void> batchUpdateSalaries(@RequestBody BatchSalaryUpdateRequest request) {
        employeeService.batchUpdateSalaries(request.getIds(), request.getIncrement());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<Employee>> getEmployeesByDeptUsingProcedure(@RequestParam String department) {
        return ResponseEntity.ok(employeeService.getEmployeesByDepartmentUsingProcedure(department));
    }
}
