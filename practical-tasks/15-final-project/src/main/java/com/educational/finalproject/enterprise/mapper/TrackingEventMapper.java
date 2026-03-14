package com.educational.finalproject.enterprise.mapper;

import com.educational.finalproject.enterprise.dto.TrackingEventDTO;
import com.educational.finalproject.enterprise.model.TrackingEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Маппер для событий трекинга (TrackingEventMapper).</p>
 * <p>Класс выполняет преобразование событий перемещения груза. 
 * При маппинге в DTO извлекается номер отслеживания (Tracking Number) 
 * из связанной сущности Shipment для удобства отображения.</p>
 * 
 * @author Antigravity
 */
@Component
public class TrackingEventMapper {

    /**
     * Преобразует сущность события в DTO.
     * @param entity Сущность
     * @return TrackingEventDTO
     */
    public TrackingEventDTO toDTO(TrackingEvent entity) {
        if (entity == null) {
            return null;
        }

        TrackingEventDTO dto = new TrackingEventDTO();
        
        dto.setId(entity.getId());
        if (entity.getShipment() != null) {
            dto.setShipmentId(entity.getShipment().getId());
            dto.setTrackingNumber(entity.getShipment().getTrackingNumber());
        }
        dto.setEventTimestamp(entity.getEventTimestamp());
        dto.setEventType(entity.getEventType());
        dto.setLocation(entity.getLocation());
        dto.setDescription(entity.getDescription());
        dto.setProblematic(entity.isProblematic());
        dto.setPublicDisplayMessage(entity.getPublicDisplayMessage());

        return dto;
    }

    /**
     * Преобразует DTO в сущность события.
     * @param dto Данные
     * @return TrackingEvent
     */
    public TrackingEvent toEntity(TrackingEventDTO dto) {
        if (dto == null) {
            return null;
        }

        TrackingEvent entity = new TrackingEvent();
        
        entity.setId(dto.getId());
        entity.setEventTimestamp(dto.getEventTimestamp());
        entity.setEventType(dto.getEventType());
        entity.setLocation(dto.getLocation());
        entity.setDescription(dto.getDescription());
        entity.setProblematic(dto.isProblematic());
        entity.setPublicDisplayMessage(dto.getPublicDisplayMessage());

        return entity;
    }

    /**
     * Преобразует список сущностей в список DTO.
     * @param entities Список
     * @return List DTO
     */
    public List<TrackingEventDTO> toDTOList(List<TrackingEvent> entities) {
        if (entities == null) {
            return null;
        }
        
        List<TrackingEventDTO> dtos = new ArrayList<>();
        for (TrackingEvent event : entities) {
            dtos.add(toDTO(event));
        }
        return dtos;
    }
}
