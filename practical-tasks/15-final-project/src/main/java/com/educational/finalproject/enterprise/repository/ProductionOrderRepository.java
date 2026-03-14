package com.educational.finalproject.enterprise.repository;

import com.educational.finalproject.enterprise.model.ProductionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * <p>Репозиторий для производственных заказов (ProductionOrderRepository).</p>
 * <p>Интерфейс обеспечивает доступ к данным о планах производства. 
 * Включает методы для поиска по номеру заказа и фильтрации по статусу.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
@Repository
public interface ProductionOrderRepository extends JpaRepository<ProductionOrder, Long> {

    /**
     * Поиск заказа по уникальному номеру.
     * @param orderNumber Номер заказа
     * @return Optional с заказом
     */
    Optional<ProductionOrder> findByOrderNumber(String orderNumber);

    /**
     * Поиск заказов с определенным статусом (например, "OPEN", "IN_PROGRESS").
     * @param status Статус выполнения
     * @return Список заказов
     */
    List<ProductionOrder> findByStatus(String status);
}
