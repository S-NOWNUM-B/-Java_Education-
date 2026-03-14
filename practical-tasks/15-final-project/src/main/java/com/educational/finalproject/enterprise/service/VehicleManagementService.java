package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.dto.VehicleDTO;
import com.educational.finalproject.enterprise.mapper.VehicleMapper;
import com.educational.finalproject.enterprise.model.Vehicle;
import com.educational.finalproject.enterprise.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>Сервис управления автопарком (VehicleManagementService).</p>
 * <p>Данный класс обеспечивает контроль за техническим состоянием 
 * транспортных средств предприятия. Он реализует логику постановки 
 * машин на ремонт, планирования техобслуживания и мониторинга пробега.</p>
 * 
 * <p>Интеграция с логистическим модулем позволяет системе блокировать 
 * назначение неисправных ТС на маршруты, снижая риски задержек.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Service
public class VehicleManagementService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    /**
     * Конструктор сервиса управления автопарком.
     */
    public VehicleManagementService(VehicleRepository vehicleRepository, 
                                    VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
    }

    /**
     * <p>Регистрирует новое транспортное средство в системе.</p>
     * @param dto Данные ТС
     * @return VehicleDTO
     */
    @Transactional
    public VehicleDTO registerVehicle(VehicleDTO dto) {
        Vehicle vehicle = vehicleMapper.toEntity(dto);
        vehicle.setStatus("AVAILABLE");
        
        if (vehicle.getLastServiceDate() != null) {
            vehicle.setNextServiceDate(vehicle.getLastServiceDate().plusMonths(6));
        }
        
        Vehicle saved = vehicleRepository.save(vehicle);
        return vehicleMapper.toDTO(saved);
    }

    /**
     * <p>Направляет автомобиль на техническое обслуживание.</p>
     * @param id ID ТС
     */
    @Transactional
    public void sendToService(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Транспортное средство не найдено"));
        
        vehicle.setStatus("UNDER_REPAIR");
        vehicle.setLastServiceDate(LocalDateTime.now());
        vehicle.setNextServiceDate(LocalDateTime.now().plusMonths(6));
        
        vehicleRepository.save(vehicle);
        System.out.println("[Fleet] Vehicle " + vehicle.getLicensePlate() + " sent to service.");
    }

    /**
     * <p>Обновляет текущий пробег и проверяет необходимость ТО.</p>
     * @param id ID ТС
     * @param mileage Добавленный пробег
     */
    @Transactional
    public void trackMileage(Long id, long mileage) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Транспортное средство не найдено"));
        
        vehicle.setCurrentMileage(vehicle.getCurrentMileage() + mileage);
        
        // Каждые 15,000 км рекомендуется проверка
        if (vehicle.getCurrentMileage() % 15000 < mileage) {
            System.out.println("[Warning] Vehicle " + vehicle.getLicensePlate() + " requires inspection!");
        }
        
        vehicleRepository.save(vehicle);
    }

    /**
     * <p>Возвращает список всех доступных для рейса автомобилей.</p>
     * @return List DTO
     */
    @Transactional(readOnly = true)
    public List<VehicleDTO> getAvailableFleet() {
        return vehicleMapper.toDTOList(vehicleRepository.findByStatus("AVAILABLE"));
    }

    /**
     * <p>Возвращает список ТС с просроченным или близким ТО.</p>
     * @return List DTO
     */
    @Transactional(readOnly = true)
    public List<VehicleDTO> getVehiclesRequiringService() {
        LocalDateTime threshold = LocalDateTime.now().plusDays(14);
        return vehicleRepository.findAll().stream()
                .filter(v -> v.getNextServiceDate() != null && v.getNextServiceDate().isBefore(threshold))
                .map(vehicleMapper::toDTO)
                .toList();
    }
}
