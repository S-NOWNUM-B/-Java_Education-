package com.educational.finalproject.enterprise.repository;

import com.educational.finalproject.enterprise.model.MaintenanceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Репозиторий для логов обслуживания (MaintenanceLogRepository).</p>
 * <p>Хранит историю технических работ. Позволяет извлекать отчеты 
 * по конкретным техникам или оборудованию.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
@Repository
public interface MaintenanceLogRepository extends JpaRepository<MaintenanceLog, Long> {

    /**
     * Поиск всех записей об обслуживании конкретного станка.
     * @param machineId ID оборудования
     * @return Список логов
     */
    List<MaintenanceLog> findByMachineId(Long machineId);

    /**
     * Поиск работ, выполненных конкретным техником.
     * @param technicianName Имя мастера
     * @return Список логов
     */
    List<MaintenanceLog> findByTechnicianName(String technicianName);
}
