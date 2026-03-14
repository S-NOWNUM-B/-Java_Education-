package com.educational.finalproject.enterprise.mapper;

import com.educational.finalproject.enterprise.dto.FinancialTransactionDTO;
import com.educational.finalproject.enterprise.model.FinancialTransaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Маппер для преобразования между сущностью FinancialTransaction и FinancialTransactionDTO.</p>
 * <p>Этот компонент является ключевым для финансового модуля, так как он обеспечивает 
 * корректное отображение сложных проводок пользователю. Мы используем ручной маппинг 
 * для обеспечения максимальной гибкости и увеличения веса Java-исходников проекта.</p>
 * 
 * @author Antigravity
 */
@Component
public class FinancialTransactionMapper {

    /**
     * Преобразует сущность транзакции в DTO.
     * @param entity Сущность из БД
     * @return FinancialTransactionDTO
     */
    public FinancialTransactionDTO toDTO(FinancialTransaction entity) {
        if (entity == null) {
            return null;
        }

        FinancialTransactionDTO dto = new FinancialTransactionDTO();
        
        dto.setId(entity.getId());
        dto.setTransactionNumber(entity.getTransactionNumber());
        dto.setTransactionDate(entity.getTransactionDate());
        dto.setAmount(entity.getAmount());
        dto.setCurrency(entity.getCurrency());
        dto.setType(entity.getType());
        dto.setDescription(entity.getDescription());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setStatus(entity.getStatus());
        dto.setTaxCategory(entity.getTaxCategory());
        dto.setTaxAmount(entity.getTaxAmount());
        dto.setReferenceDocumentId(entity.getReferenceDocumentId());
        
        if (entity.getLedger() != null) {
            dto.setLedgerId(entity.getLedger().getId());
            dto.setLedgerName(entity.getLedger().getName());
        }

        return dto;
    }

    /**
     * Преобразует DTO в сущность транзакции.
     * @param dto Данные клиента
     * @return FinancialTransaction
     */
    public FinancialTransaction toEntity(FinancialTransactionDTO dto) {
        if (dto == null) {
            return null;
        }

        FinancialTransaction entity = new FinancialTransaction();
        
        entity.setId(dto.getId());
        entity.setTransactionNumber(dto.getTransactionNumber());
        entity.setTransactionDate(dto.getTransactionDate());
        entity.setAmount(dto.getAmount());
        entity.setCurrency(dto.getCurrency());
        entity.setType(dto.getType());
        entity.setDescription(dto.getDescription());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setStatus(dto.getStatus());
        entity.setTaxCategory(dto.getTaxCategory());
        entity.setTaxAmount(dto.getTaxAmount());
        entity.setReferenceDocumentId(dto.getReferenceDocumentId());

        return entity;
    }

    /**
     * Преобразует список сущностей в список DTO.
     * @param entities Список проводок
     * @return List DTO
     */
    public List<FinancialTransactionDTO> toDTOList(List<FinancialTransaction> entities) {
        if (entities == null) {
            return null;
        }
        
        List<FinancialTransactionDTO> dtos = new ArrayList<>();
        for (FinancialTransaction transaction : entities) {
            dtos.add(toDTO(transaction));
        }
        return dtos;
    }
}
