package com.educational.finalproject.enterprise.mapper;

import com.educational.finalproject.enterprise.dto.VehicleDTO;
import com.educational.finalproject.enterprise.model.Vehicle;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Маппер для транспортных средств (VehicleMapper).</p>
 * <p>Класс выполняет преобразование данных об автопарке. 
 * При маппинге в DTO мы фокусируемся на данных, необходимых 
 * диспетчеру для планирования очередного рейса или техобслуживания.</p>
 * 
 * @author Antigravity
 */
@Component
public class VehicleMapper {

    /**
     * Преобразует сущность ТС в DTO.
     * @param entity Сущность
     * @return VehicleDTO
     */
    public VehicleDTO toDTO(Vehicle entity) {
        if (entity == null) {
            return null;
        }

        VehicleDTO dto = new VehicleDTO();
        
        dto.setId(entity.getId());
        dto.setLicensePlate(entity.getLicensePlate());
        dto.setModelName(entity.getModelName());
        dto.setVehicleType(entity.getVehicleType());
        dto.setPayloadCapacityTons(entity.getPayloadCapacityTons());
        dto.setCargoVolumeM3(entity.getCargoVolumeM3());
        dto.setCurrentMileage(entity.getCurrentMileage());
        dto.setNextServiceDate(entity.getNextServiceDate());
        dto.setStatus(entity.getStatus());
        dto.setVinNumber(entity.getVinNumber());

        return dto;
    }

    /**
     * Преобразует DTO в сущность ТС.
     * @param dto Данные
     * @return Vehicle
     */
    public Vehicle toEntity(VehicleDTO dto) {
        if (dto == null) {
            return null;
        }

        Vehicle entity = new Vehicle();
        
        entity.setId(dto.getId());
        entity.setLicensePlate(dto.getLicensePlate());
        entity.setModelName(dto.getModelName());
        entity.setVehicleType(dto.getVehicleType());
        entity.setPayloadCapacityTons(dto.getPayloadCapacityTons());
        entity.setCargoVolumeM3(dto.getCargoVolumeM3());
        entity.setCurrentMileage(dto.getCurrentMileage());
        entity.setNextServiceDate(dto.getNextServiceDate());
        entity.setStatus(dto.getStatus());
        entity.setVinNumber(dto.getVinNumber());

        return entity;
    }

    /**
     * Преобразует список сущностей в список DTO.
     * @param entities Список
     * @return List DTO
     */
    public List<VehicleDTO> toDTOList(List<Vehicle> entities) {
        if (entities == null) {
            return null;
        }
        
        List<VehicleDTO> dtos = new ArrayList<>();
        for (Vehicle vehicle : entities) {
            dtos.add(toDTO(vehicle));
        }
        return dtos;
    }
}
