package com.educational.finalproject.enterprise.mapper;

import com.educational.finalproject.enterprise.dto.MaintenanceLogDTO;
import com.educational.finalproject.enterprise.model.MaintenanceLog;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Конвертер для логов обслуживания (MaintenanceLogMapper).</p>
 * <p>Выполняет трансформацию данных о сервисных операциях. 
 * Включает логику связывания с сущностью Machine через идентификаторы.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
@Component
public class MaintenanceLogMapper {

    /**
     * Преобразование сущности лога в DTO.
     * @param entity Лог обслуживания
     * @return DTO обслуживания
     */
    public MaintenanceLogDTO toDTO(MaintenanceLog entity) {
        if (entity == null) {
            return null;
        }

        MaintenanceLogDTO dto = new MaintenanceLogDTO();
        dto.setId(entity.getId());
        dto.setMaintenanceDate(entity.getMaintenanceDate());
        dto.setDescription(entity.getDescription());
        dto.setTechnicianName(entity.getTechnicianName());
        dto.setTotalCost(entity.getTotalCost());
        
        if (entity.getMachine() != null) {
            dto.setMachineId(entity.getMachine().getId());
            dto.setMachineName(entity.getMachine().getName());
        }
        
        return dto;
    }

    /**
     * Преобразование DTO в сущность лога.
     * @param dto DTO обслуживания
     * @return Сущность обслуживания
     */
    public MaintenanceLog toEntity(MaintenanceLogDTO dto) {
        if (dto == null) {
            return null;
        }

        MaintenanceLog entity = new MaintenanceLog();
        entity.setId(dto.getId());
        entity.setMaintenanceDate(dto.getMaintenanceDate());
        entity.setDescription(dto.getDescription());
        entity.setTechnicianName(dto.getTechnicianName());
        entity.setTotalCost(dto.getTotalCost());
        // Machine должен устанавливаться через соответствующий репозиторий в сервисе
        return entity;
    }

    /**
     * Маппинг списков логов.
     * @param entities Список сущностей
     * @return Список DTO
     */
    public List<MaintenanceLogDTO> toDTOList(List<MaintenanceLog> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        List<MaintenanceLogDTO> dtos = new ArrayList<>();
        for (MaintenanceLog entity : entities) {
            dtos.add(toDTO(entity));
        }
        return dtos;
    }
}
