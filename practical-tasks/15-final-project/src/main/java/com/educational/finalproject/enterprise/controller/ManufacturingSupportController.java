package com.educational.finalproject.enterprise.controller;

import com.educational.finalproject.enterprise.dto.MachineDTO;
import com.educational.finalproject.enterprise.dto.MaintenanceLogDTO;
import com.educational.finalproject.enterprise.service.MaintenanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>REST контроллер вспомогательных служб производства (ManufacturingSupportController).</p>
 * <p>Предоставляет эндпоинты для управления оборудованием, 
 * регистрации технического обслуживания и контроля исправности 
 * станков в рабочих центрах.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/manufacturing-support")
public class ManufacturingSupportController {

    private final MaintenanceService maintenanceService;

    public ManufacturingSupportController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    /**
     * Получение списка исправного оборудования.
     * @return Список доступных станков
     */
    @GetMapping("/machines/operational")
    public ResponseEntity<List<MachineDTO>> getOperationalMachines() {
        return ResponseEntity.ok(maintenanceService.getOperationalMachines());
    }

    /**
     * Регистрация постановки станка на ремонт/ТО.
     * @param machineId ID оборудования
     * @param technician Имя ответственного лица
     * @return Данные о записи в журнале обслуживания
     */
    @PostMapping("/maintenance/schedule")
    public ResponseEntity<MaintenanceLogDTO> scheduleMaintenance(@RequestParam Long machineId, 
                                                                @RequestParam String technician) {
        return ResponseEntity.ok(maintenanceService.scheduleMaintenance(machineId, technician));
    }

    /**
     * Завершение работ по обслуживанию.
     * @param logId ID записи из журнала
     * @param totalCost Стоимость работ
     * @return Подтверждение завершения
     */
    @PostMapping("/maintenance/{logId}/complete")
    public ResponseEntity<String> completeMaintenance(@PathVariable Long logId, 
                                                     @RequestParam double totalCost) {
        maintenanceService.completeMaintenance(logId, totalCost);
        return ResponseEntity.ok("Maintenance completed successfully.");
    }
}
