package com.educational.finalproject.enterprise.repository;

import com.educational.finalproject.enterprise.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * <p>Репозиторий для оборудования (MachineRepository).</p>
 * <p>Управляет данными о станках. Включает методы для поиска по 
 * серийным номерам и фильтрации по техсостоянию оборудования.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {

    /**
     * Поиск станка по уникальному серийному номеру.
     * @param serialNumber Серийный номер
     * @return Optional с оборудованием
     */
    Optional<Machine> findBySerialNumber(String serialNumber);

    /**
     * Поиск всех станков в конкретном рабочем центре.
     * @param workCenterId ID рабочего центра
     * @return Список оборудования
     */
    List<Machine> findByWorkCenterId(Long workCenterId);

    /**
     * Поиск оборудования по текущему статусу.
     * @param status Статус (OPERATIONAL, BROKEN и т.д.)
     * @return Список оборудования
     */
    List<Machine> findByStatus(String status);
}
