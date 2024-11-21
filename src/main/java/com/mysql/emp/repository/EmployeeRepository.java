package com.mysql.emp.repository;

import com.mysql.emp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.department = :department")
    List<Employee> findByDepartment(@Param("department") String department);

    @Query(value = "CALL GetEmployeesByDept(:department)", nativeQuery = true)
    List<Employee> getEmployeesByDepartmentUsingProcedure(@Param("department") String department);
}
