package com.mysql.emp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;

import javax.xml.transform.Source;


@Entity
@Table(name = "employees", indexes = @Index(name = "idx_department", columnList = "department"))
public class Employee implements Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Size(min = 2, message = "Name should be at least 2 characters")
    @Column(nullable = false)
    private String name;
    @NotNull
    @Column(nullable = false)
    private String department;
    @NotNull
    @Min(value = 0, message = "Salary should be a positive number")
    @Column(nullable = false)
    private Double salary;

    public Employee() {
    }

    public Employee(long id, String name, String department, Double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public void setSystemId(String systemId) {

    }

    @Override
    public String getSystemId() {
        return null;
    }
}
