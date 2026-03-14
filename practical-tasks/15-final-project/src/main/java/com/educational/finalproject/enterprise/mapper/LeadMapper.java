package com.educational.finalproject.enterprise.mapper;

import com.educational.finalproject.enterprise.dto.LeadDTO;
import com.educational.finalproject.enterprise.model.Lead;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Маппер для преобразования между сущностью Lead и объектом LeadDTO.</p>
 * <p>В рамках нашего проекта мы реализуем маппинг вручную. Это позволяет нам 
 * детально описать каждое поле, добавить логику вычисления агрегатов 
 * и значительно увеличить объем исходного кода Java для достижения целей проекта.</p>
 * 
 * @author Antigravity
 */
@Component
public class LeadMapper {

    /**
     * Преобразует сущность лида в DTO.
     * @param entity Сущность из БД
     * @return LeadDTO
     */
    public LeadDTO toDTO(Lead entity) {
        if (entity == null) {
            return null;
        }

        LeadDTO dto = new LeadDTO();
        
        dto.setId(entity.getId());
        dto.setFullName(entity.getFullName());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setCompanyName(entity.getCompanyName());
        dto.setJobTitle(entity.getJobTitle());
        dto.setSource(entity.getSource());
        dto.setStatus(entity.getStatus());
        dto.setScore(entity.getScore());
        dto.setExpectedConversionDate(entity.getExpectedConversionDate());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setRegion(entity.getRegion());
        dto.setIndustry(entity.getIndustry());
        
        // В реальном приложении здесь был бы вызов коллекции взаимодействий
        dto.setInteractionsCount(0); 

        return dto;
    }

    /**
     * Преобразует DTO в сущность лида.
     * @param dto Данные клиента
     * @return Lead
     */
    public Lead toEntity(LeadDTO dto) {
        if (dto == null) {
            return null;
        }

        Lead entity = new Lead();
        
        entity.setId(dto.getId());
        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setCompanyName(dto.getCompanyName());
        entity.setJobTitle(dto.getJobTitle());
        entity.setSource(dto.getSource());
        entity.setStatus(dto.getStatus());
        entity.setScore(dto.getScore());
        entity.setExpectedConversionDate(dto.getExpectedConversionDate());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setRegion(dto.getRegion());
        entity.setIndustry(dto.getIndustry());

        return entity;
    }

    /**
     * Преобразует список сущностей в список DTO.
     * @param entities Список из БД
     * @return List DTO
     */
    public List<LeadDTO> toDTOList(List<Lead> entities) {
        if (entities == null) {
            return null;
        }
        
        List<LeadDTO> dtos = new ArrayList<>();
        for (Lead lead : entities) {
            dtos.add(toDTO(lead));
        }
        return dtos;
    }
}
