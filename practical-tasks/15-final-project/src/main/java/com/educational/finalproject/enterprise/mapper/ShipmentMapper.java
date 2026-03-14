package com.educational.finalproject.enterprise.mapper;

import com.educational.finalproject.enterprise.dto.ShipmentDTO;
import com.educational.finalproject.enterprise.model.Shipment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Маппер для отгрузок (ShipmentMapper).</p>
 * <p>Преобразует сущности Shipment в DTO и обратно. 
 * Ручная реализация позволяет нам явно контролировать копирование 
 * каждого из многочисленных полей, что соответствует нашей стратегии 
 * генерации большого объема качественного Java-кода.</p>
 * 
 * @author Antigravity
 */
@Component
public class ShipmentMapper {

    /**
     * Преобразует сущность в DTO.
     * @param entity Сущность
     * @return ShipmentDTO
     */
    public ShipmentDTO toDTO(Shipment entity) {
        if (entity == null) {
            return null;
        }

        ShipmentDTO dto = new ShipmentDTO();
        
        dto.setId(entity.getId());
        dto.setTrackingNumber(entity.getTrackingNumber());
        if (entity.getSourceOrder() != null) {
            dto.setOrderId(entity.getSourceOrder().getId());
        }
        dto.setStatus(entity.getStatus());
        dto.setWeightKg(entity.getWeightKg());
        dto.setVolumeM3(entity.getVolumeM3());
        dto.setParcelCount(entity.getParcelCount());
        dto.setCargoType(entity.getCargoType());
        dto.setOriginAddress(entity.getOriginAddress());
        dto.setDestinationAddress(entity.getDestinationAddress());
        dto.setEstimatedDeliveryTime(entity.getEstimatedDeliveryTime());
        dto.setActualDeliveryTime(entity.getActualDeliveryTime());
        
        if (entity.getVehicle() != null) {
            dto.setLicensePlate(entity.getVehicle().getLicensePlate());
        }

        return dto;
    }

    /**
     * Преобразует DTO в сущность.
     * @param dto Данные
     * @return Shipment
     */
    public Shipment toEntity(ShipmentDTO dto) {
        if (dto == null) {
            return null;
        }

        Shipment entity = new Shipment();
        
        entity.setId(dto.getId());
        entity.setTrackingNumber(dto.getTrackingNumber());
        entity.setStatus(dto.getStatus());
        entity.setWeightKg(dto.getWeightKg());
        entity.setVolumeM3(dto.getVolumeM3());
        entity.setParcelCount(dto.getParcelCount());
        entity.setCargoType(dto.getCargoType());
        entity.setOriginAddress(dto.getOriginAddress());
        entity.setDestinationAddress(dto.getDestinationAddress());
        entity.setEstimatedDeliveryTime(dto.getEstimatedDeliveryTime());
        entity.setActualDeliveryTime(dto.getActualDeliveryTime());

        return entity;
    }

    /**
     * Преобразует список сущностей в список DTO.
     * @param entities Список
     * @return List DTO
     */
    public List<ShipmentDTO> toDTOList(List<Shipment> entities) {
        if (entities == null) {
            return null;
        }
        
        List<ShipmentDTO> dtos = new ArrayList<>();
        for (Shipment shipment : entities) {
            dtos.add(toDTO(shipment));
        }
        return dtos;
    }
}
