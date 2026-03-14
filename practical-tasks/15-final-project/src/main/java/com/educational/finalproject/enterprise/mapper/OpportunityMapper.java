package com.educational.finalproject.enterprise.mapper;

import com.educational.finalproject.enterprise.dto.OpportunityDTO;
import com.educational.finalproject.enterprise.model.Opportunity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Маппер для сделок (OpportunityMapper).</p>
 * <p>Обеспечивает трансформацию данных воронки продаж. Включает логику 
 * извлечения ID и имен связанных сущностей (Lead) для упрощения DTO.</p>
 * 
 * @author Antigravity
 */
@Component
public class OpportunityMapper {

    /**
     * Преобразует сущность сделки в DTO.
     * @param entity Сущность
     * @return OpportunityDTO
     */
    public OpportunityDTO toDTO(Opportunity entity) {
        if (entity == null) {
            return null;
        }

        OpportunityDTO dto = new OpportunityDTO();
        
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setExpectedRevenue(entity.getExpectedRevenue());
        dto.setProbability(entity.getProbability());
        dto.setStage(entity.getStage());
        dto.setClosedDate(entity.getClosedDate());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setOpportunityType(entity.getOpportunityType());
        dto.setDescription(entity.getDescription());
        
        if (entity.getSourceLead() != null) {
            dto.setLeadId(entity.getSourceLead().getId());
            dto.setLeadName(entity.getSourceLead().getFullName());
        }
        
        dto.setOwnerName(entity.getOwnerName());
        dto.setCustomerBudget(entity.getCustomerBudget());
        dto.setRiskLevel(entity.getRiskLevel());

        return dto;
    }

    /**
     * Преобразует DTO сделки в сущность.
     * @param dto Данные
     * @return Opportunity
     */
    public Opportunity toEntity(OpportunityDTO dto) {
        if (dto == null) {
            return null;
        }

        Opportunity entity = new Opportunity();
        
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setExpectedRevenue(dto.getExpectedRevenue());
        entity.setProbability(dto.getProbability());
        entity.setStage(dto.getStage());
        entity.setClosedDate(dto.getClosedDate());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setOpportunityType(dto.getOpportunityType());
        entity.setDescription(dto.getDescription());
        entity.setOwnerName(dto.getOwnerName());
        entity.setCustomerBudget(dto.getCustomerBudget());
        entity.setRiskLevel(dto.getRiskLevel());

        return entity;
    }

    /**
     * Преобразует список сущностей в список DTO.
     * @param entities Список
     * @return List DTO
     */
    public List<OpportunityDTO> toDTOList(List<Opportunity> entities) {
        if (entities == null) {
            return null;
        }
        
        List<OpportunityDTO> dtos = new ArrayList<>();
        for (Opportunity opp : entities) {
            dtos.add(toDTO(opp));
        }
        return dtos;
    }
}
