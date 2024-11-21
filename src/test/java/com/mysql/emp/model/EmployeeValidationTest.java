package com.mysql.emp.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EmployeeValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testInvalidEmployee() throws IOException, SAXException {
        Employee employee = new Employee();
        employee.setName("A"); // Invalid (too short)
        employee.setDepartment(null); // Invalid (null)
        employee.setSalary(2000.0); // Invalid (below minimum)

        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);

        assertEquals(3, violations.size());
    }
}

