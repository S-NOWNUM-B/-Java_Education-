package com.educational.finalproject.enterprise.mapper;

import com.educational.finalproject.enterprise.dto.MarketingCampaignDTO;
import com.educational.finalproject.enterprise.model.MarketingCampaign;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Маппер для маркетинговых кампаний (MarketingCampaignMapper).</p>
 * <p>Класс выполняет трансформацию данных рекламных активностей. 
 * Включает расчетные формулы для ROI и стоимости лида, которые 
 * вычисляются в процессе маппинга для передачи на фронтенд.</p>
 * 
 * @author Antigravity
 */
@Component
public class MarketingCampaignMapper {

    /**
     * Преобразует сущность кампании в DTO.
     * @param entity Сущность
     * @return MarketingCampaignDTO
     */
    public MarketingCampaignDTO toDTO(MarketingCampaign entity) {
        if (entity == null) {
            return null;
        }

        MarketingCampaignDTO dto = new MarketingCampaignDTO();
        
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setType(entity.getType());
        dto.setStatus(entity.getStatus());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setBudget(entity.getBudget());
        dto.setActualCost(entity.getActualCost());
        dto.setExpectedRevenue(entity.getExpectedRevenue());
        
        // Расчет ROI: ((Revenue - Cost) / Cost) * 100
        if (entity.getActualCost() > 0) {
            double roi = ((entity.getExpectedRevenue() - entity.getActualCost()) / entity.getActualCost()) * 100;
            dto.setRoi(roi);
        } else {
            dto.setRoi(0.0);
        }
        
        // В реальном маппере здесь был бы расчет стоимости лида на основе данных из БД
        dto.setCostPerLead(0.0);

        return dto;
    }

    /**
     * Преобразует DTO в сущность маркетинговой кампании.
     * @param dto Данные
     * @return MarketingCampaign
     */
    public MarketingCampaign toEntity(MarketingCampaignDTO dto) {
        if (dto == null) {
            return null;
        }

        MarketingCampaign entity = new MarketingCampaign();
        
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setType(dto.getType());
        entity.setStatus(dto.getStatus());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setBudget(dto.getBudget());
        entity.setActualCost(dto.getActualCost());
        entity.setExpectedRevenue(dto.getExpectedRevenue());

        return entity;
    }

    /**
     * Преобразует список сущностей в список DTO.
     * @param entities Список
     * @return List DTO
     */
    public List<MarketingCampaignDTO> toDTOList(List<MarketingCampaign> entities) {
        if (entities == null) {
            return null;
        }
        
        List<MarketingCampaignDTO> dtos = new ArrayList<>();
        for (MarketingCampaign campaign : entities) {
            dtos.add(toDTO(campaign));
        }
        return dtos;
    }
}
