package com.educational.finalproject.enterprise.mapper;

import com.educational.finalproject.enterprise.dto.DepartmentDTO;
import com.educational.finalproject.enterprise.model.Department;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Маппер для преобразования между Department и DepartmentDTO.
 */
@Component
public class DepartmentMapper {

    private final EmployeeMapper employeeMapper;

    public DepartmentMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    /**
     * Преобразует сущность Department в DepartmentDTO.
     */
    public DepartmentDTO toDTO(Department entity) {
        if (entity == null) {
            return null;
        }

        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setLocation(entity.getLocation());
        dto.setContactEmail(entity.getContactEmail());
        dto.setContactPhone(entity.getContactPhone());
        dto.setFloor(entity.getFloor());
        dto.setOfficeNumber(entity.getOfficeNumber());
        dto.setYearlyBudget(entity.getYearlyBudget());
        dto.setActive(entity.isActive());

        if (entity.getEmployees() != null) {
            dto.setEmployees(employeeMapper.toDTOList(entity.getEmployees()));
        }

        return dto;
    }

    /**
     * Преобразует DepartmentDTO в сущность Department.
     */
    public Department toEntity(DepartmentDTO dto) {
        if (dto == null) {
            return null;
        }

        Department entity = new Department();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setLocation(dto.getLocation());
        entity.setContactEmail(dto.getContactEmail());
        entity.setContactPhone(dto.getContactPhone());
        entity.setFloor(dto.getFloor());
        entity.setOfficeNumber(dto.getOfficeNumber());
        entity.setYearlyBudget(dto.getYearlyBudget());
        entity.setActive(dto.isActive());

        return entity;
    }

    public List<DepartmentDTO> toDTOList(List<Department> entities) {
        if (entities == null) return null;
        List<DepartmentDTO> dtos = new ArrayList<>();
        for (Department d : entities) {
            dtos.add(toDTO(d));
        }
        return dtos;
    }
}
