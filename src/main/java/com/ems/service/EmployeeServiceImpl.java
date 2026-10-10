package com.ems.service;

import com.ems.entity.Employee;
import com.ems.exception.DuplicateEmailException;
import com.ems.exception.ResourceNotFoundException;
import com.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        // Enforce duplicate email prevention
        if (employeeRepository.existsByEmailIgnoreCase(employee.getEmail().trim())) {
            throw new DuplicateEmailException("An employee with email '" + employee.getEmail().trim() + "' already exists.");
        }
        // Normalize fields
        employee.setName(employee.getName().trim());
        employee.setEmail(employee.getEmail().trim().toLowerCase());
        employee.setDepartment(employee.getDepartment().trim());
        employee.setDesignation(employee.getDesignation().trim());

        return employeeRepository.save(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));
    }

    @Override
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));

        String newEmail = employeeDetails.getEmail().trim().toLowerCase();
        // If email has been changed, verify that the new email isn't taken by another employee
        if (!existingEmployee.getEmail().equalsIgnoreCase(newEmail)) {
            if (employeeRepository.existsByEmailIgnoreCase(newEmail)) {
                throw new DuplicateEmailException("Email '" + newEmail + "' is already in use by another employee.");
            }
        }

        existingEmployee.setName(employeeDetails.getName().trim());
        existingEmployee.setEmail(newEmail);
        existingEmployee.setDepartment(employeeDetails.getDepartment().trim());
        existingEmployee.setDesignation(employeeDetails.getDesignation().trim());
        existingEmployee.setSalary(employeeDetails.getSalary());

        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot delete: Employee not found with ID: " + id));

        employeeRepository.delete(existingEmployee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> searchEmployees(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return employeeRepository.findAll();
        }
        return employeeRepository.searchEmployees(keyword.trim());
    }

    @Override
    @Transactional(readOnly = true)
    public long getTotalEmployeeCount() {
        return employeeRepository.count();
    }
}
