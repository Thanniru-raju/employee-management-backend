package com.ems.service;

import com.ems.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);

    List<Employee> searchEmployees(String keyword);

    long getTotalEmployeeCount();
}
