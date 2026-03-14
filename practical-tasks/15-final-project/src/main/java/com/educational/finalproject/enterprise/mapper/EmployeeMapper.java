package com.educational.finalproject.enterprise.mapper;

import com.educational.finalproject.enterprise.dto.EmployeeDTO;
import com.educational.finalproject.enterprise.model.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Маппер для преобразования между Employee и EmployeeDTO.
 * Реализован вручную для демонстрации явного копирования полей и увеличения объема кода.
 */
@Component
public class EmployeeMapper {

    /**
     * Преобразует сущность Employee в EmployeeDTO.
     */
    public EmployeeDTO toDTO(Employee entity) {
        if (entity == null) {
            return null;
        }

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setMiddleName(entity.getMiddleName());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setAddress(entity.getAddress());
        dto.setCity(entity.getCity());
        dto.setCountry(entity.getCountry());
        dto.setZipCode(entity.getZipCode());
        dto.setBirthDate(entity.getBirthDate());
        dto.setHireDate(entity.getHireDate());
        dto.setPosition(entity.getPosition());
        dto.setSalary(entity.getSalary());
        dto.setCurrency(entity.getCurrency());
        
        // Вычисляемые поля
        dto.setFullName(entity.getFirstName() + " " + (entity.getMiddleName() != null ? entity.getMiddleName() + " " : "") + entity.getLastName());
        
        if (entity.getDepartment() != null) {
            dto.setDepartmentId(entity.getDepartment().getId());
            dto.setDepartmentName(entity.getDepartment().getName());
        }

        return dto;
    }

    /**
     * Преобразует EmployeeDTO в сущность Employee.
     */
    public Employee toEntity(EmployeeDTO dto) {
        if (dto == null) {
            return null;
        }

        Employee entity = new Employee();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setMiddleName(dto.getMiddleName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setAddress(dto.getAddress());
        entity.setCity(dto.getCity());
        entity.setCountry(dto.getCountry());
        entity.setZipCode(dto.getZipCode());
        entity.setBirthDate(dto.getBirthDate());
        entity.setHireDate(dto.getHireDate());
        entity.setPosition(dto.getPosition());
        entity.setSalary(dto.getSalary());
        entity.setCurrency(dto.getCurrency());

        return entity;
    }

    /**
     * Преобразование списков.
     */
    public List<EmployeeDTO> toDTOList(List<Employee> entities) {
        if (entities == null) return null;
        List<EmployeeDTO> dtos = new ArrayList<>();
        for (Employee e : entities) {
            dtos.add(toDTO(e));
        }
        return dtos;
    }
}
