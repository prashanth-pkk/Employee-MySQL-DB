package com.mysql.emp.service;

import com.mysql.emp.model.Employee;
import com.mysql.emp.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Cacheable("employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(Long id, Employee updateEmployee) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(updateEmployee.getName());
                    employee.setDepartment(updateEmployee.getDepartment());
                    employee.setSalary(updateEmployee.getSalary());
                    return employeeRepository.save(employee);
                }).orElseThrow(() -> new EntityNotFoundException("Employee not found"));
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public Page<Employee> getEmployees(Pageable pageable) {
        return employeeRepository.findAll((org.springframework.data.domain.Pageable) pageable);
    }

    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    @Transactional
    public void batchUpdateSalaries(List<Long> ids, Double increment) {
        ids.forEach(id -> {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            employee.setSalary(employee.getSalary() + increment);
            employeeRepository.save(employee);
        });
    }

    public List<Employee> getEmployeesByDepartmentUsingProcedure(String department) {
        return employeeRepository.getEmployeesByDepartmentUsingProcedure(department);
    }

}

