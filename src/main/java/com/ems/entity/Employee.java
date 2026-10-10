package com.ems.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Employee name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Email address is required")
    @Email(message = "Please enter a valid email address")
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @NotBlank(message = "Department is required")
    @Column(nullable = false, length = 50)
    private String department;

    @NotBlank(message = "Designation is required")
    @Column(nullable = false, length = 50)
    private String designation;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be a positive number")
    @Column(nullable = false)
    private Double salary;

    // Default No-Args Constructor (Required by JPA)
    public Employee() {
    }

    // All-Args Constructor
    public Employee(Long id, String name, String email, String department, String designation, Double salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
    }

    // Constructor without ID (convenient for creation)
    public Employee(String name, String email, String department, String designation, Double salary) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", designation='" + designation + '\'' +
                ", salary=" + salary +
                '}';
    }
}
