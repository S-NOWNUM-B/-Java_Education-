package com.educational.finalproject.enterprise.mapper;

import com.educational.finalproject.enterprise.dto.InteractionLogDTO;
import com.educational.finalproject.enterprise.model.InteractionLog;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Маппер для журналов взаимодействия (InteractionLogMapper).</p>
 * <p>Класс обеспечивает преобразование данных о контактах с клиентами. 
 * Ручной маппинг позволяет нам гибко управлять ссылками на Lead и Opportunity, 
 * обеспечивая корректное отображение истории в CRM.</p>
 * 
 * @author Antigravity
 */
@Component
public class InteractionLogMapper {

    /**
     * Преобразует сущность лога в DTO.
     * @param entity Сущность
     * @return InteractionLogDTO
     */
    public InteractionLogDTO toDTO(InteractionLog entity) {
        if (entity == null) {
            return null;
        }

        InteractionLogDTO dto = new InteractionLogDTO();
        
        dto.setId(entity.getId());
        dto.setInteractionDate(entity.getInteractionDate());
        dto.setInteractionType(entity.getInteractionType());
        dto.setSubject(entity.getSubject());
        dto.setContent(entity.getContent());
        
        if (entity.getLead() != null) {
            dto.setLeadId(entity.getLead().getId());
            dto.setLeadName(entity.getLead().getFullName());
        }
        
        if (entity.getOpportunity() != null) {
            dto.setOpportunityId(entity.getOpportunity().getId());
            dto.setOpportunityName(entity.getOpportunity().getName());
        }
        
        dto.setStaffMemberName(entity.getStaffMemberName());
        dto.setOutcome(entity.getOutcome());
        dto.setDurationMinutes(entity.getDurationMinutes());
        dto.setInbound(entity.isInbound());

        return dto;
    }

    /**
     * Преобразует DTO в сущность лога.
     * @param dto Данные
     * @return InteractionLog
     */
    public InteractionLog toEntity(InteractionLogDTO dto) {
        if (dto == null) {
            return null;
        }

        InteractionLog entity = new InteractionLog();
        
        entity.setId(dto.getId());
        entity.setInteractionDate(dto.getInteractionDate());
        entity.setInteractionType(dto.getInteractionType());
        entity.setSubject(dto.getSubject());
        entity.setContent(dto.getContent());
        entity.setStaffMemberName(dto.getStaffMemberName());
        entity.setOutcome(dto.getOutcome());
        entity.setDurationMinutes(dto.getDurationMinutes());
        entity.setInbound(dto.isInbound());

        return entity;
    }

    /**
     * Преобразует список сущностей в список DTO.
     * @param entities Список
     * @return List DTO
     */
    public List<InteractionLogDTO> toDTOList(List<InteractionLog> entities) {
        if (entities == null) {
            return null;
        }
        
        List<InteractionLogDTO> dtos = new ArrayList<>();
        for (InteractionLog log : entities) {
            dtos.add(toDTO(log));
        }
        return dtos;
    }
}
