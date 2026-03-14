package com.educational.finalproject.enterprise.repository;

import com.educational.finalproject.enterprise.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * <p>Репозиторий для управления счетами-фактурами (Invoice).</p>
 * <p>Класс поддерживает широкий спектр методов поиска для управления 
 * дебиторской и кредиторской задолженностью предприятия.</p>
 * 
 * @author Antigravity
 */
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    /**
     * Поиск инвойса по его официальному номеру.
     * @param invoiceNumber Номер счета
     * @return Optional
     */
    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);

    /**
     * Поиск счетов конкретного контрагента (покупателя).
     * @param customerName Имя клиента
     * @return Список счетов
     */
    List<Invoice> findByCustomerName(String customerName);

    /**
     * Поиск счетов конкретного поставщика.
     * @param vendorName Имя поставщика
     * @return Список
     */
    List<Invoice> findByVendorName(String vendorName);

    /**
     * Поиск счетов по статусу оплаты.
     * @param status Статус (PAID, OVERDUE и т.д.)
     * @return Список
     */
    List<Invoice> findByStatus(String status);

    /**
     * Поиск просроченных счетов.
     * @param now Текущая дата для сравнения с dueDate
     * @param status Статус (обычно не PAID)
     * @return Список
     */
    List<Invoice> findByDueDateBeforeAndStatusNot(LocalDateTime now, String status);

    /**
     * Поиск по ссылке на заказ.
     * @param orderReference Номер заказа
     * @return Список
     */
    List<Invoice> findByOrderReference(String orderReference);
}
