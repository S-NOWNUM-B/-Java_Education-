package com.educational.finalproject.enterprise.mapper;

import com.educational.finalproject.enterprise.dto.MachineDTO;
import com.educational.finalproject.enterprise.model.Machine;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Конвертер для оборудования (MachineMapper).</p>
 * <p>Класс предназначен для маппинга данных о производственных агрегатах. 
 * Обрабатывает связи с рабочими центрами и подтягивает связанные наименования.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
@Component
public class MachineMapper {

    /**
     * Маппинг сущности станка в DTO.
     * @param entity Сущность оборудования
     * @return Объект передачи данных
     */
    public MachineDTO toDTO(Machine entity) {
        if (entity == null) {
            return null;
        }

        MachineDTO dto = new MachineDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setModelNumber(entity.getModelNumber());
        dto.setSerialNumber(entity.getSerialNumber());
        dto.setStatus(entity.getStatus());
        dto.setPurchaseDate(entity.getPurchaseDate());
        
        if (entity.getWorkCenter() != null) {
            dto.setWorkCenterId(entity.getWorkCenter().getId());
            dto.setWorkCenterName(entity.getWorkCenter().getName());
        }
        
        return dto;
    }

    /**
     * Маппинг DTO в сущность станка.
     * @param dto Объект передачи данных
     * @return Сущность оборудования
     */
    public Machine toEntity(MachineDTO dto) {
        if (dto == null) {
            return null;
        }

        Machine entity = new Machine();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setModelNumber(dto.getModelNumber());
        entity.setSerialNumber(dto.getSerialNumber());
        entity.setStatus(dto.getStatus());
        entity.setPurchaseDate(dto.getPurchaseDate());
        // WorkCenter устанавливается в сервисном слое через репозиторий
        return entity;
    }

    /**
     * Конвертация списка станков в список DTO.
     * @param entities Коллекция сущностей
     * @return Список объектов передачи данных
     */
    public List<MachineDTO> toDTOList(List<Machine> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        List<MachineDTO> dtos = new ArrayList<>();
        for (Machine entity : entities) {
            dtos.add(toDTO(entity));
        }
        return dtos;
    }
}
