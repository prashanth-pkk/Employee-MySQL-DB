package com.mysql.emp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;

import javax.xml.transform.Source;
import java.time.LocalDate;


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

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @JoinColumn(name = "department_id", nullable = false)
    private String department;

    @Column(nullable = false)
    private String jobTitle;

    @NotNull
    @Min(value = 0, message = "Salary should be a positive number")
    @Column(nullable = false)
    private Double salary;

    private LocalDate hireDate;
    private String phoneNumber;

    private boolean isActive;

    public Employee() {
    }

    public Employee(String name, String email, String department, String jobTitle, Double salary, LocalDate hireDate, String phoneNumber, boolean isActive) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.hireDate = hireDate;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public void setSystemId(String systemId) {

    }

    @Override
    public String getSystemId() {
        return null;
    }
}
