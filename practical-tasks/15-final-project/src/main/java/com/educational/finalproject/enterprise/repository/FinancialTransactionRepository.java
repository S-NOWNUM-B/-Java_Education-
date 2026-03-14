package com.educational.finalproject.enterprise.repository;

import com.educational.finalproject.enterprise.model.FinancialTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * <p>Репозиторий для управления финансовыми транзакциями (FinancialTransaction).</p>
 * <p>Поддерживает сложные запросы для финансовой аналитики, включая поиск 
 * по временным интервалам, типам операций и статусам.</p>
 * 
 * @author Antigravity
 */
@Repository
public interface FinancialTransactionRepository extends JpaRepository<FinancialTransaction, Long> {

    /**
     * Поиск по уникальному номеру транзакции.
     * @param transactionNumber Номер
     * @return Optional
     */
    Optional<FinancialTransaction> findByTransactionNumber(String transactionNumber);

    /**
     * Поиск транзакций конкретной бухгалтерской книги.
     * @param ledgerId ID книги
     * @return Список проводок
     */
    List<FinancialTransaction> findByLedgerId(Long ledgerId);

    /**
     * Поиск транзакций по типу в рамках книги.
     * @param ledgerId ID книги
     * @param type Тип операции
     * @return Список
     */
    List<FinancialTransaction> findByLedgerIdAndType(Long ledgerId, String type);

    /**
     * Поиск транзакций за заданный период.
     * @param start Начало периода
     * @param end Конец периода
     * @return Список
     */
    List<FinancialTransaction> findByTransactionDateBetween(LocalDateTime start, LocalDateTime end);

    /**
     * Поиск транзакций по статусу.
     * @param status Стаус (COMPLETED, PENDING и т.д.)
     * @return Список
     */
    List<FinancialTransaction> findByStatus(String status);
}
