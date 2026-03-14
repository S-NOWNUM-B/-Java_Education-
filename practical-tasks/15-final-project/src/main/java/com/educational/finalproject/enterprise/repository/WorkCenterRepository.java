package com.educational.finalproject.enterprise.repository;

import com.educational.finalproject.enterprise.model.WorkCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Репозиторий для рабочих центров (WorkCenterRepository).</p>
 * <p>Обеспечивает управление данными о производственных мощностях. 
 * Содержит методы для поиска активных центров и центров по локации.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
@Repository
public interface WorkCenterRepository extends JpaRepository<WorkCenter, Long> {

    /**
     * Поиск всех активных производственных центров.
     * @param isActive Флаг активности
     * @return Список центров
     */
    List<WorkCenter> findByIsActive(boolean isActive);

    /**
     * Поиск центров по коду локации.
     * @param locationCode Код склада/цеха
     * @return Список центров
     */
    List<WorkCenter> findByLocationCode(String locationCode);
}
