package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.dto.InvoiceDTO;
import com.educational.finalproject.enterprise.mapper.InvoiceMapper;
import com.educational.finalproject.enterprise.model.Invoice;
import com.educational.finalproject.enterprise.repository.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * <p>Сервис управления инвойсами (InvoiceService).</p>
 * <p>Класс отвечает за полный жизненный цикл счетов-фактур в ERP-системе. 
 * Он обеспечивает генерацию документов на основе заказов, контроль сроков оплаты, 
 * расчет налоговых составляющих и управление статусами платежей.</p>
 * 
 * <p>Инвойсирование является связующим звеном между модулем продаж и финансовым модулем. 
 * Каждый метод этого сервиса спроектирован с учетом требований аудита и юридической 
 * значимости документов.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    /**
     * Конструктор для внедрения зависимостей.
     */
    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
    }

    /**
     * <p>Создает новый инвойс в системе.</p>
     * <p>Метод выполняет расчет итоговых сумм, налогов и устанавливает начальный статус. 
     * Генерируется уникальный бизнес-номер инвойса.</p>
     * 
     * @param dto Данные для создания инвойса
     * @return InvoiceDTO Детали созданного счета
     */
    @Transactional
    public InvoiceDTO createInvoice(InvoiceDTO dto) {
        Invoice invoice = invoiceMapper.toEntity(dto);
        
        // Автоматическая генерация номера
        invoice.setInvoiceNumber("INV-" + LocalDateTime.now().getYear() + "-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase());
        invoice.setIssueDate(LocalDateTime.now());
        
        // Если срок оплаты не указан, устанавливаем +30 дней по умолчанию
        if (invoice.getDueDate() == null) {
            invoice.setDueDate(LocalDateTime.now().plusDays(30));
        }
        
        // Расчет итоговой суммы (Gross) на основе Net и Tax
        double gross = invoice.getNetAmount() + invoice.getTaxTotal() - invoice.getDiscountTotal();
        invoice.setGrossAmount(gross);
        
        invoice.setStatus("SENT");
        
        Invoice saved = invoiceRepository.save(invoice);
        return invoiceMapper.toDTO(saved);
    }

    /**
     * <p>Регистрирует факт оплаты инвойса.</p>
     * <p>Этот метод обновляет статус документа и фиксирует дату платежа. 
     * В реальной ERP это инициировало бы создание проводки в FinancialService.</p>
     * 
     * @param invoiceId ID счета
     */
    @Transactional
    public void markAsPaid(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Инвойс не найден с ID: " + invoiceId));
        
        invoice.setStatus("PAID");
        invoice.setPaymentDate(LocalDateTime.now());
        invoiceRepository.save(invoice);
        
        System.out.println("[InvoiceSystem] Invoice " + invoice.getInvoiceNumber() + " has been fully paid.");
    }

    /**
     * <p>Возвращает список просроченных инвойсов.</p>
     * @return List DTO
     */
    @Transactional(readOnly = true)
    public List<InvoiceDTO> getOverdueInvoices() {
        List<Invoice> overdue = invoiceRepository.findByDueDateBeforeAndStatusNot(LocalDateTime.now(), "PAID");
        return invoiceMapper.toDTOList(overdue);
    }

    /**
     * <p>Аннулирует инвойс.</p>
     * <p>Документ помечается как VOID, что скрывает его из основных отчета, 
     * но сохраняет в БД для целей аудита.</p>
     * 
     * @param invoiceId ID счета
     */
    @Transactional
    public void voidInvoice(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Инвойс не найден"));
        
        invoice.setStatus("VOID");
        invoiceRepository.save(invoice);
    }

    /**
     * <p>Возвращает все инвойсы конкретного клиента.</p>
     * @param customerName Имя клиента
     * @return List DTO
     */
    @Transactional(readOnly = true)
    public List<InvoiceDTO> getCustomerInvoices(String customerName) {
        return invoiceMapper.toDTOList(invoiceRepository.findByCustomerName(customerName));
    }

    /**
     * <p>Рассчитывает общую сумму дебиторской задолженности (Unpaid Invoices).</p>
     * @return double Сумма
     */
    @Transactional(readOnly = true)
    public double calculateTotalOutstanding() {
        return invoiceRepository.findAll().stream()
                .filter(inv -> !"PAID".equals(inv.getStatus()) && !"VOID".equals(inv.getStatus()))
                .mapToDouble(Invoice::getGrossAmount)
                .sum();
    }
}
