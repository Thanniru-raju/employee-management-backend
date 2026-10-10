package com.ems.controller;

import com.ems.entity.Employee;
import com.ems.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // 1. Create Employee: POST /api/employees
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    // 2. Get All Employees OR Search: GET /api/employees?search=keyword
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(
            @RequestParam(name = "search", required = false) String search) {

        if (search != null && !search.trim().isEmpty()) {
            return ResponseEntity.ok(employeeService.searchEmployees(search));
        }
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // 3. Get Employee By ID: GET /api/employees/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    // 4. Update Employee: PUT /api/employees/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable("id") Long id,
            @Valid @RequestBody Employee employeeDetails) {

        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }

    // 5. Delete Employee: DELETE /api/employees/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        Map<String, Object> response = new HashMap<>();
        response.put("deleted", true);
        response.put("message", "Employee with ID " + id + " has been deleted successfully.");
        return ResponseEntity.ok(response);
    }

    // 6. Get Total Employee Count: GET /api/employees/count
    @GetMapping("/count")
    public ResponseEntity<Map<String, Object>> getEmployeeCount() {
        long count = employeeService.getTotalEmployeeCount();
        Map<String, Object> response = new HashMap<>();
        response.put("totalEmployees", count);
        return ResponseEntity.ok(response);
    }
}
