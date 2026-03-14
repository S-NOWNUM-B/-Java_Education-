package com.educational.finalproject.enterprise.repository;

import com.educational.finalproject.enterprise.model.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * <p>Репозиторий для управления сущностями Ledger (Главная книга).</p>
 * <p>Интерфейс предоставляет стандартные методы для работы с базой данных 
 * через Spring Data JPA, а также специализированные методы поиска бухгалтерских книг 
 * по их атрибутам.</p>
 * 
 * @author Antigravity
 */
@Repository
public interface LedgerRepository extends JpaRepository<Ledger, Long> {

    /**
     * Поиск книги по уникальному имени.
     * @param name Название книги
     * @return Optional с книгой
     */
    Optional<Ledger> findByName(String name);

    /**
     * Поиск активных/неактивных книг.
     * @param isActive Флаг активности
     * @return Список книг
     */
    List<Ledger> findByIsActive(boolean isActive);

    /**
     * Поиск книг по типу (Asset, Liability и т.д.).
     * @param ledgerType Тип книги
     * @return Список книг
     */
    List<Ledger> findByLedgerType(String ledgerType);

    /**
     * Поиск книг по валюте учета.
     * @param currency Код валюты
     * @return Список книг
     */
    List<Ledger> findByCurrency(String currency);
}
