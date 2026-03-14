package com.educational.finalproject.enterprise.controller;

import com.educational.finalproject.enterprise.dto.VehicleDTO;
import com.educational.finalproject.enterprise.service.VehicleManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>REST контроллер управления автопарком (FleetController).</p>
 * <p>Класс предназначен для операционного управления транспортными 
 * средствами предприятия. Он предоставляет API для регистрации новых машин, 
 * обновления их пробега и контроля периодов технического обслуживания.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@RestController
@RequestMapping("/api/logistics/fleet")
public class FleetController {

    private final VehicleManagementService vehicleService;

    /**
     * Конструктор контроллера автопарка.
     */
    public FleetController(VehicleManagementService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * Регистрирует новое ТС в парке компании.
     * @param dto Данные автомобиля
     * @return Зарегистрированное ТС
     */
    @PostMapping("/vehicles")
    public ResponseEntity<VehicleDTO> register(@RequestBody VehicleDTO dto) {
        return ResponseEntity.ok(vehicleService.registerVehicle(dto));
    }

    /**
     * Направляет автомобиль на ТО.
     * @param id ID ТС
     * @return Ответ 200 OK
     */
    @PostMapping("/vehicles/{id}/service")
    public ResponseEntity<Void> sendToService(@PathVariable Long id) {
        vehicleService.sendToService(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Обновляет текущий пробег ТС.
     * @param id ID ТС
     * @param mileage Добавленный пробег (км)
     * @return Ответ 200 OK
     */
    @PutMapping("/vehicles/{id}/mileage")
    public ResponseEntity<Void> updateMileage(@PathVariable Long id, @RequestParam long mileage) {
        vehicleService.trackMileage(id, mileage);
        return ResponseEntity.ok().build();
    }

    /**
     * Возвращает список доступных для рейса автомобилей.
     * @return Список DTO
     */
    @GetMapping("/vehicles/available")
    public ResponseEntity<List<VehicleDTO>> getAvailable() {
        return ResponseEntity.ok(vehicleService.getAvailableFleet());
    }

    /**
     * Возвращает список ТС, требующих техобслуживания.
     * @return Список DTO
     */
    @GetMapping("/vehicles/maintenance-alerts")
    public ResponseEntity<List<VehicleDTO>> getAlerts() {
        return ResponseEntity.ok(vehicleService.getVehiclesRequiringService());
    }
}
