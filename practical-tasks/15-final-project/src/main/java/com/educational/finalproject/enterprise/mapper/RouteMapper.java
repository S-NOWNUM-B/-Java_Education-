package com.educational.finalproject.enterprise.mapper;

import com.educational.finalproject.enterprise.dto.RouteDTO;
import com.educational.finalproject.enterprise.model.Route;
import com.educational.finalproject.enterprise.model.Shipment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Маппер для маршрутов (RouteMapper).</p>
 * <p>Обеспечивает трансформацию данных о рейсах. 
 * Включает логику извлечения идентификаторов отгрузок для DTO, 
 * что делает API более легким и удобным для клиента.</p>
 * 
 * @author Antigravity
 */
@Component
public class RouteMapper {

    /**
     * Преобразует сущность маршрута в DTO.
     * @param entity Сущность
     * @return RouteDTO
     */
    public RouteDTO toDTO(Route entity) {
        if (entity == null) {
            return null;
        }

        RouteDTO dto = new RouteDTO();
        
        dto.setId(entity.getId());
        dto.setRouteName(entity.getRouteName());
        dto.setTotalDistanceKm(entity.getTotalDistanceKm());
        dto.setDepartureTime(entity.getDepartureTime());
        dto.setArrivalTime(entity.getArrivalTime());
        dto.setStatus(entity.getStatus());
        
        if (entity.getShipments() != null) {
            dto.setShipmentIds(entity.getShipments().stream()
                    .map(Shipment::getId)
                    .collect(Collectors.toList()));
        }
        
        if (entity.getPrimaryDriver() != null) {
            dto.setDriverName(entity.getPrimaryDriver().getFullName());
        }
        
        dto.setEstimatedFuelConsumption(entity.getEstimatedFuelConsumption());
        dto.setEstimatedTollCosts(entity.getEstimatedTollCosts());
        dto.setWaypoints(entity.getWaypoints());

        return dto;
    }

    /**
     * Преобразует DTO в сущность маршрута.
     * @param dto Данные
     * @return Route
     */
    public Route toEntity(RouteDTO dto) {
        if (dto == null) {
            return null;
        }

        Route entity = new Route();
        
        entity.setId(dto.getId());
        entity.setRouteName(dto.getRouteName());
        entity.setTotalDistanceKm(dto.getTotalDistanceKm());
        entity.setDepartureTime(dto.getDepartureTime());
        entity.setArrivalTime(dto.getArrivalTime());
        entity.setStatus(dto.getStatus());
        entity.setEstimatedFuelConsumption(dto.getEstimatedFuelConsumption());
        entity.setEstimatedTollCosts(dto.getEstimatedTollCosts());
        entity.setWaypoints(dto.getWaypoints());

        return entity;
    }

    /**
     * Преобразует список сущностей в список DTO.
     * @param entities Список
     * @return List DTO
     */
    public List<RouteDTO> toDTOList(List<Route> entities) {
        if (entities == null) {
            return null;
        }
        
        List<RouteDTO> dtos = new ArrayList<>();
        for (Route route : entities) {
            dtos.add(toDTO(route));
        }
        return dtos;
    }
}
