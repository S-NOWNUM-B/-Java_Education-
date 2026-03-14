package com.educational.finalproject.enterprise.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object для сущности Department.
 */
public class DepartmentDTO {

    private Long id;
    private String name;
    private String description;
    private String location;
    private String contactEmail;
    private String contactPhone;
    private int floor;
    private String officeNumber;
    private double yearlyBudget;
    private boolean isActive;
    
    private List<EmployeeDTO> employees;

    public DepartmentDTO() {
        this.employees = new ArrayList<>();
    }

    // Геттеры и сеттеры (ручное написание для объема кода)

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public double getYearlyBudget() {
        return yearlyBudget;
    }

    public void setYearlyBudget(double yearlyBudget) {
        this.yearlyBudget = yearlyBudget;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDTO> employees) {
        this.employees = employees;
    }
}
