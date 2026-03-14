package com.educational.finalproject.enterprise.mapper;

import com.educational.finalproject.enterprise.dto.ProductionOrderDTO;
import com.educational.finalproject.enterprise.model.ProductionOrder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Конвертер для производственных заказов (ProductionOrderMapper).</p>
 * <p>Класс выполняет ручное преобразование между сущностью ProductionOrder 
 * и объектом передачи данных ProductionOrderDTO. Ручная реализация 
 * обеспечивает полный контроль над процессом маппинга и позволяет 
 * гибко настраивать логику обогащения данных.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
@Component
public class ProductionOrderMapper {

    /**
     * Преобразует сущность в DTO.
     * @param entity Сущность производственного заказа
     * @return Объект передачи данных
     */
    public ProductionOrderDTO toDTO(ProductionOrder entity) {
        if (entity == null) {
            return null;
        }

        ProductionOrderDTO dto = new ProductionOrderDTO();
        dto.setId(entity.getId());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setProductId(entity.getProductId());
        dto.setQuantity(entity.getQuantity());
        dto.setStatus(entity.getStatus());
        dto.setPriority(entity.getPriority());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setNotes(entity.getNotes());
        
        // ProductName может быть установлен на уровне сервиса через соединение с другими таблицами
        return dto;
    }

    /**
     * Преобразует DTO в сущность.
     * @param dto Объект передачи данных
     * @return Сущность производственного заказа
     */
    public ProductionOrder entity(ProductionOrderDTO dto) {
        if (dto == null) {
            return null;
        }

        ProductionOrder entity = new ProductionOrder();
        entity.setId(dto.getId());
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setProductId(dto.getProductId());
        entity.setQuantity(dto.getQuantity());
        entity.setStatus(dto.getStatus());
        entity.setPriority(dto.getPriority());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setNotes(dto.getNotes());
        return entity;
    }

    /**
     * Маппинг списков (Entities to DTOs).
     * @param entities Список сущностей
     * @return Список DTO
     */
    public List<ProductionOrderDTO> toDTOList(List<ProductionOrder> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        List<ProductionOrderDTO> dtos = new ArrayList<>();
        for (ProductionOrder entity : entities) {
            dtos.add(toDTO(entity));
        }
        return dtos;
    }
}
