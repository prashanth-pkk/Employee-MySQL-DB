package com.mysql.emp.config;

import com.mysql.emp.model.Department;
import com.mysql.emp.model.Employee;
import com.mysql.emp.repository.DepartmentRepository;
import com.mysql.emp.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class DataSeeder {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public DataSeeder(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Bean
    public CommandLineRunner seedDatabase() {
        return args -> {
            if (departmentRepository.count() == 0) {
                List<Department> departments = List.of(
                        new Department("IT"),
                        new Department("HR"),
                        new Department("Finace"),
                        new Department("Marketing"),
                        new Department("Operations")
                );
                departmentRepository.saveAll(departments);
            }

            if (employeeRepository.count() == 0) {
                List<Department> departments = departmentRepository.findAll();
                List<Employee> employees = new ArrayList<>();
                Random random = new Random();

                // Generate realistic data
                for (int i = 1; i <= 1000; i++) {
                    Employee employee = new Employee();
                    employee.setName("Employee " + i);
                    employee.setDepartment(String.valueOf(departments.get(random.nextInt(departments.size()))));
                    employee.setSalary(30000 + random.nextDouble() * 50000);
                    employee.setActive(random.nextBoolean());
                    employees.add(employee);
                }

                // Save all employees to the database
                employeeRepository.saveAll(employees);
                System.out.println("1000 employees inserted with realistic data.");
            } else {
                System.out.println("Data already exists. Skipping seeder.");
            }
        };
    }
}
