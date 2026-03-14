package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.dto.MachineDTO;
import com.educational.finalproject.enterprise.dto.MaintenanceLogDTO;
import com.educational.finalproject.enterprise.mapper.MachineMapper;
import com.educational.finalproject.enterprise.mapper.MaintenanceLogMapper;
import com.educational.finalproject.enterprise.model.Machine;
import com.educational.finalproject.enterprise.model.MaintenanceLog;
import com.educational.finalproject.enterprise.repository.MachineRepository;
import com.educational.finalproject.enterprise.repository.MaintenanceLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>Сервис технического обслуживания оборудования (MaintenanceService).</p>
 * <p>Класс отвечает за мониторинг состояния станков и регистрацию 
 * сервисных воздействий. Включает логику блокировки оборудования 
 * при проведении регламентных работ.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
@Service
public class MaintenanceService {

    private final MachineRepository machineRepository;
    private final MaintenanceLogRepository logRepository;
    private final MachineMapper machineMapper;
    private final MaintenanceLogMapper logMapper;

    public MaintenanceService(MachineRepository machineRepository, 
                              MaintenanceLogRepository logRepository, 
                              MachineMapper machineMapper, 
                              MaintenanceLogMapper logMapper) {
        this.machineRepository = machineRepository;
        this.logRepository = logRepository;
        this.machineMapper = machineMapper;
        this.logMapper = logMapper;
    }

    /**
     * Постановка оборудования на плановое ТО.
     * @param machineId ID станка
     * @param technician Имя техника
     * @return Информационный лог о постановке на ТО
     */
    @Transactional
    public MaintenanceLogDTO scheduleMaintenance(Long machineId, String technician) {
        Machine machine = machineRepository.findById(machineId)
                .orElseThrow(() -> new RuntimeException("Machine not found"));

        machine.setStatus("MAINTENANCE");
        machineRepository.save(machine);

        MaintenanceLog mLog = new MaintenanceLog();
        mLog.setMachine(machine);
        mLog.setMaintenanceDate(LocalDateTime.now());
        mLog.setTechnicianName(technician);
        mLog.setDescription("Плановое техническое обслуживание и диагностика.");
        
        MaintenanceLog savedLog = logRepository.save(mLog);
        return logMapper.toDTO(savedLog);
    }

    /**
     * Регистрация завершения ремонтных работ.
     * @param logId ID записи из журнала ТО
     * @param cost Стоимость запчастей и работ
     */
    @Transactional
    public void completeMaintenance(Long logId, double cost) {
        MaintenanceLog mLog = logRepository.findById(logId)
                .orElseThrow(() -> new RuntimeException("Maintenance record not found"));
        
        mLog.setTotalCost(cost);
        mLog.setDescription(mLog.getDescription() + " Работы завершены. Стоимость: " + cost);
        
        Machine machine = mLog.getMachine();
        if (machine != null) {
            machine.setStatus("OPERATIONAL");
            machineRepository.save(machine);
        }
        
        logRepository.save(mLog);
    }

    /**
     * Получение списка всего оборудования в рабочем состоянии.
     * @return Список DTO исправных станков
     */
    public List<MachineDTO> getOperationalMachines() {
        List<Machine> machines = machineRepository.findByStatus("OPERATIONAL");
        return machineMapper.toDTOList(machines);
    }
}
