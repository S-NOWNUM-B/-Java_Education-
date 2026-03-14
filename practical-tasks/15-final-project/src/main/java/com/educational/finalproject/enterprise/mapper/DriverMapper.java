package com.educational.finalproject.enterprise.mapper;

import com.educational.finalproject.enterprise.dto.DriverDTO;
import com.educational.finalproject.enterprise.model.Driver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Маппер для водителей (DriverMapper).</p>
 * <p>Обеспечивает трансформацию данных о персонале логистики. 
 * Включает детальное копирование характеристик квалификации и стажа.</p>
 * 
 * @author Antigravity
 */
@Component
public class DriverMapper {

    /**
     * Преобразует сущность водителя в DTO.
     * @param entity Сущность
     * @return DriverDTO
     */
    public DriverDTO toDTO(Driver entity) {
        if (entity == null) {
            return null;
        }

        DriverDTO dto = new DriverDTO();
        
        dto.setId(entity.getId());
        dto.setFullName(entity.getFullName());
        dto.setLicenseCategories(entity.getLicenseCategories());
        dto.setLicenseExpiryDate(entity.getLicenseExpiryDate());
        dto.setStatus(entity.getStatus());
        dto.setContactPhone(entity.getContactPhone());
        dto.setPerformanceRating(entity.getPerformanceRating());
        dto.setExperienceYears(entity.getExperienceYears());

        return dto;
    }

    /**
     * Преобразует DTO в сущность водителя.
     * @param dto Данные
     * @return Driver
     */
    public Driver toEntity(DriverDTO dto) {
        if (dto == null) {
            return null;
        }

        Driver entity = new Driver();
        
        entity.setId(dto.getId());
        entity.setFullName(dto.getFullName());
        entity.setLicenseCategories(dto.getLicenseCategories());
        entity.setLicenseExpiryDate(dto.getLicenseExpiryDate());
        entity.setStatus(dto.getStatus());
        entity.setContactPhone(dto.getContactPhone());
        entity.setPerformanceRating(dto.getPerformanceRating());
        entity.setExperienceYears(dto.getExperienceYears());

        return entity;
    }

    /**
     * Преобразует список сущностей в список DTO.
     * @param entities Список
     * @return List DTO
     */
    public List<DriverDTO> toDTOList(List<Driver> entities) {
        if (entities == null) {
            return null;
        }
        
        List<DriverDTO> dtos = new ArrayList<>();
        for (Driver driver : entities) {
            dtos.add(toDTO(driver));
        }
        return dtos;
    }
}
