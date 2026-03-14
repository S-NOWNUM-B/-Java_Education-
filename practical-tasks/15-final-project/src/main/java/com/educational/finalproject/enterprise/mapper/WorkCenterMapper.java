package com.educational.finalproject.enterprise.mapper;

import com.educational.finalproject.enterprise.dto.WorkCenterDTO;
import com.educational.finalproject.enterprise.model.WorkCenter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Конвертер для рабочих центров (WorkCenterMapper).</p>
 * <p>Обеспечивает трансформацию данных о производственных мощностях. 
 * Включает методы для обработки коллекций и одиночных объектов.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
@Component
public class WorkCenterMapper {

    /**
     * Трансформация сущности в DTO.
     * @param entity Рабочий центр
     * @return DTO рабочего центра
     */
    public WorkCenterDTO toDTO(WorkCenter entity) {
        if (entity == null) {
            return null;
        }

        WorkCenterDTO dto = new WorkCenterDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setCapacityPerHour(entity.getCapacityPerHour());
        dto.setLocationCode(entity.getLocationCode());
        dto.setSupervisorName(entity.getSupervisorName());
        dto.setActive(entity.isActive());
        return dto;
    }

    /**
     * Трансформация DTO в сущность.
     * @param dto DTO рабочего центра
     * @return Сущность рабочего центра
     */
    public WorkCenter toEntity(WorkCenterDTO dto) {
        if (dto == null) {
            return null;
        }

        WorkCenter entity = new WorkCenter();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setCapacityPerHour(dto.getCapacityPerHour());
        entity.setLocationCode(dto.getLocationCode());
        entity.setSupervisorName(dto.getSupervisorName());
        entity.setActive(dto.isActive());
        return entity;
    }

    /**
     * Массовое преобразование в DTO.
     * @param entities Список сущностей
     * @return Список объектов передачи данных
     */
    public List<WorkCenterDTO> toDTOList(List<WorkCenter> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        List<WorkCenterDTO> dtos = new ArrayList<>();
        for (WorkCenter entity : entities) {
            dtos.add(toDTO(entity));
        }
        return dtos;
    }
}
