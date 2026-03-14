package com.educational.finalproject.enterprise.mapper;

import com.educational.finalproject.enterprise.dto.CustomerFeedbackDTO;
import com.educational.finalproject.enterprise.model.CustomerFeedback;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Маппер для отзывов клиентов (CustomerFeedbackMapper).</p>
 * <p>Класс выполняет двустороннее преобразование данных обратной связи. 
 * Он интегрирует информацию о контакте (Lead) в DTO для удобства отображения 
 * в интерфейсе технической поддержки.</p>
 * 
 * @author Antigravity
 */
@Component
public class CustomerFeedbackMapper {

    /**
     * Преобразует сущность отзыва в DTO.
     * @param entity Сущность
     * @return CustomerFeedbackDTO
     */
    public CustomerFeedbackDTO toDTO(CustomerFeedback entity) {
        if (entity == null) {
            return null;
        }

        CustomerFeedbackDTO dto = new CustomerFeedbackDTO();
        
        dto.setId(entity.getId());
        dto.setFeedbackDate(entity.getFeedbackDate());
        dto.setStarRating(entity.getStarRating());
        dto.setComment(entity.getComment());
        dto.setFeedbackType(entity.getFeedbackType());
        dto.setChannel(entity.getChannel());
        dto.setStatus(entity.getStatus());
        
        if (entity.getContact() != null) {
            dto.setContactId(entity.getContact().getId());
            dto.setContactName(entity.getContact().getFullName());
        }
        
        dto.setNpsScore(entity.getNpsScore());
        dto.setPriority(entity.getPriority());
        dto.setOfficialResponse(entity.getOfficialResponse());
        dto.setResolutionDate(entity.getResolutionDate());

        return dto;
    }

    /**
     * Преобразует DTO отзыва в сущность.
     * @param dto Данные
     * @return CustomerFeedback
     */
    public CustomerFeedback toEntity(CustomerFeedbackDTO dto) {
        if (dto == null) {
            return null;
        }

        CustomerFeedback entity = new CustomerFeedback();
        
        entity.setId(dto.getId());
        entity.setFeedbackDate(dto.getFeedbackDate());
        entity.setStarRating(dto.getStarRating());
        entity.setComment(dto.getComment());
        entity.setFeedbackType(dto.getFeedbackType());
        entity.setChannel(dto.getChannel());
        entity.setStatus(dto.getStatus());
        entity.setNpsScore(dto.getNpsScore());
        entity.setPriority(dto.getPriority());
        entity.setOfficialResponse(dto.getOfficialResponse());
        entity.setResolutionDate(dto.getResolutionDate());

        return entity;
    }

    /**
     * Преобразует список сущностей в список DTO.
     * @param entities Список
     * @return List DTO
     */
    public List<CustomerFeedbackDTO> toDTOList(List<CustomerFeedback> entities) {
        if (entities == null) {
            return null;
        }
        
        List<CustomerFeedbackDTO> dtos = new ArrayList<>();
        for (CustomerFeedback feedback : entities) {
            dtos.add(toDTO(feedback));
        }
        return dtos;
    }
}
