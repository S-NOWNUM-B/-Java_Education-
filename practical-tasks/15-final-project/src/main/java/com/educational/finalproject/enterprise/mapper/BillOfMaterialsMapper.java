package com.educational.finalproject.enterprise.mapper;

import com.educational.finalproject.enterprise.dto.BillOfMaterialsDTO;
import com.educational.finalproject.enterprise.model.BillOfMaterials;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Конвертер для спецификаций изделий (BillOfMaterialsMapper).</p>
 * <p>Класс выполняет ручной маппинг структур BOM. Особое внимание 
 * уделяется глубокому копированию списков компонентов и 
 * фильтрации данных на основе статуса одобрения спецификации.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
@Component
public class BillOfMaterialsMapper {

    /**
     * Конвертация в DTO.
     * @param entity Спецификация
     * @return Объект передачи данных
     */
    public BillOfMaterialsDTO toDTO(BillOfMaterials entity) {
        if (entity == null) {
            return null;
        }

        BillOfMaterialsDTO dto = new BillOfMaterialsDTO();
        dto.setId(entity.getId());
        dto.setProductName(entity.getProductName());
        dto.setVersion(entity.getVersion());
        dto.setApproved(entity.isApproved());
        dto.setNotes(entity.getNotes());
        
        if (entity.getComponents() != null) {
            dto.setComponents(new ArrayList<>(entity.getComponents()));
            dto.setTotalComponentCount(entity.getComponents().size());
        }
        
        return dto;
    }

    /**
     * Конвертация в сущность.
     * @param dto Объект передачи данных
     * @return Сущность спецификации
     */
    public BillOfMaterials toEntity(BillOfMaterialsDTO dto) {
        if (dto == null) {
            return null;
        }

        BillOfMaterials entity = new BillOfMaterials();
        entity.setId(dto.getId());
        entity.setProductName(dto.getProductName());
        entity.setVersion(dto.getVersion());
        entity.setApproved(dto.isApproved());
        entity.setNotes(dto.getNotes());
        
        if (dto.getComponents() != null) {
            entity.setComponents(new ArrayList<>(dto.getComponents()));
        }
        
        return entity;
    }

    /**
     * Преобразование списков.
     * @param entities Список сущностей
     * @return Список DTO
     */
    public List<BillOfMaterialsDTO> toDTOList(List<BillOfMaterials> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        List<BillOfMaterialsDTO> dtos = new ArrayList<>();
        for (BillOfMaterials entity : entities) {
            dtos.add(toDTO(entity));
        }
        return dtos;
    }
}
