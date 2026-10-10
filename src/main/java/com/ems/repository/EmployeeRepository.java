package com.ems.repository;

import com.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Check if an employee exists with a given email (case-insensitive)
    boolean existsByEmailIgnoreCase(String email);

    // Find employee by email
    Optional<Employee> findByEmailIgnoreCase(String email);

    // Search employees by name, email, department, or designation
    @Query("SELECT e FROM Employee e WHERE " +
           "LOWER(e.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(e.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(e.department) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(e.designation) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Employee> searchEmployees(@Param("keyword") String keyword);
}
