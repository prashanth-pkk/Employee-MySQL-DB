package com.mysql.emp.controller;

import com.mysql.emp.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
    }

    @Test
    void testCreateEmployee() throws Exception {
        String employeeJson = """
                {
                    "name": "Jane Doe",
                    "department": "HR",
                    "salary": 60000
                }   
                """;
        mockMvc.perform(post("/api/employees")  // Correct method to perform a POST request
                        .contentType(MediaType.APPLICATION_JSON)  // Set content type to JSON
                        .content(employeeJson))  // Set the request body (employee JSON)
                .andExpect(status().isCreated())  // Expect a 201 Created response
                .andExpect((ResultMatcher) jsonPath("$.name").value("Jane Doe"))  // Verify the 'name' field
                .andExpect((ResultMatcher) jsonPath("$.department").value("HR"));

        assertEquals(1, employeeRepository.count());

    }
}
