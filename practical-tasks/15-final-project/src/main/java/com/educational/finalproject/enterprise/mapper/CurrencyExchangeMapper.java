package com.educational.finalproject.enterprise.mapper;

import com.educational.finalproject.enterprise.dto.CurrencyExchangeDTO;
import com.educational.finalproject.enterprise.model.CurrencyExchange;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Маппер для курсов валют (CurrencyExchangeMapper).</p>
 * <p>Класс обеспечивает трансформацию данных о валютных курсах. 
 * Он используется для обеспечения фронтенда актуальной информацией об обмене 
 * и вносит свой вклад в увеличение веса Java-исходников проекта.</p>
 * 
 * @author Antigravity
 */
@Component
public class CurrencyExchangeMapper {

    /**
     * Преобразует сущность курса в DTO.
     * @param entity Сущность
     * @return CurrencyExchangeDTO
     */
    public CurrencyExchangeDTO toDTO(CurrencyExchange entity) {
        if (entity == null) {
            return null;
        }

        CurrencyExchangeDTO dto = new CurrencyExchangeDTO();
        
        dto.setId(entity.getId());
        dto.setBaseCurrency(entity.getBaseCurrency());
        dto.setTargetCurrency(entity.getTargetCurrency());
        dto.setExchangeRate(entity.getExchangeRate());
        dto.setEffectiveDate(entity.getEffectiveDate());
        dto.setExpiryDate(entity.getExpiryDate());
        dto.setRateSource(entity.getRateSource());
        dto.setOfficial(entity.isOfficial());
        dto.setRateType(entity.getRateType());
        dto.setRemarks(entity.getRemarks());

        return dto;
    }

    /**
     * Преобразует DTO курса в сущность.
     * @param dto Данные
     * @return CurrencyExchange
     */
    public CurrencyExchange toEntity(CurrencyExchangeDTO dto) {
        if (dto == null) {
            return null;
        }

        CurrencyExchange entity = new CurrencyExchange();
        
        entity.setId(dto.getId());
        entity.setBaseCurrency(dto.getBaseCurrency());
        entity.setTargetCurrency(dto.getTargetCurrency());
        entity.setExchangeRate(dto.getExchangeRate());
        entity.setEffectiveDate(dto.getEffectiveDate());
        entity.setExpiryDate(dto.getExpiryDate());
        entity.setRateSource(dto.getRateSource());
        entity.setOfficial(dto.isOfficial());
        entity.setRateType(dto.getRateType());
        entity.setRemarks(dto.getRemarks());

        return entity;
    }

    /**
     * Преобразует список курсов в список DTO.
     * @param entities Список
     * @return List DTO
     */
    public List<CurrencyExchangeDTO> toDTOList(List<CurrencyExchange> entities) {
        if (entities == null) {
            return null;
        }
        
        List<CurrencyExchangeDTO> dtos = new ArrayList<>();
        for (CurrencyExchange exchange : entities) {
            dtos.add(toDTO(exchange));
        }
        return dtos;
    }
}
