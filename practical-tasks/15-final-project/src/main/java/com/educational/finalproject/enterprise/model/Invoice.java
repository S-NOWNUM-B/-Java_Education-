package com.educational.finalproject.enterprise.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * <p>Сущность Invoice представляет собой счет-фактуру, выставленную клиенту или полученную от поставщика.</p>
 * <p>Инвойс является первичным учетным документом, на основании которого совершаются финансовые транзакции 
 * и формируется налоговая отчетность. Данный класс имитирует сложную структуру коммерческого документа, 
 * принятого в международной практике.</p>
 * 
 * <p>Поля класса покрывают реквизиты сторон (Billing Details), условия оплаты (Terms), 
 * статус оплаты и детальный расчет стоимостей с учетом налогов и скидок.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Entity
@Table(name = "invoices")
public class Invoice {

    /**
     * Системный идентификатор инвойса.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Официальный номер счета (например, "INV-2024-8891").
     */
    @Column(nullable = false, unique = true)
    private String invoiceNumber;

    /**
     * Дата выставления счета.
     */
    private LocalDateTime issueDate;

    /**
     * Срок оплаты счета (Due Date).
     */
    private LocalDateTime dueDate;

    /**
     * Дата фактической оплаты (если применимо).
     */
    private LocalDateTime paymentDate;

    /**
     * Тип счета: SALES (исходящий), PURCHASE (входящий).
     */
    private String invoiceType;

    /**
     * Статус счета: DRAFT, SENT, PARTIALLY_PAID, PAID, OVERDUE, VOID.
     */
    private String status;

    /**
     * Название компании-продавца.
     */
    private String vendorName;

    /**
     * Налоговый номер продавца (ИНН/VAT ID).
     */
    private String vendorTaxId;

    /**
     * Название компании-покупателя.
     */
    private String customerName;

    /**
     * Налоговый номер покупателя (ИНН/VAT ID).
     */
    private String customerTaxId;

    /**
     * Адрес для выставления счета.
     */
    private String billingAddress;

    /**
     * Адрес доставки товаров/услуг.
     */
    private String shippingAddress;

    /**
     * Сумма без налога (Net Amount).
     */
    private double netAmount;

    /**
     * Общая сумма налога (Tax Total).
     */
    private double taxTotal;

    /**
     * Общая сумма со скидкой (Discount Total).
     */
    private double discountTotal;

    /**
     * Итоговая сумма к оплате (Gross Amount).
     */
    private double grossAmount;

    /**
     * Валюта инвойса.
     */
    private String currency;

    /**
     * Условия оплаты (например, "Net 30", "Cash on Delivery").
     */
    private String paymentTerms;

    /**
     * Ссылка на заказ в системе (Order Reference).
     */
    private String orderReference;

    /**
     * Публичное примечание для клиента.
     */
    @Column(length = 1000)
    private String clientNotes;

    /**
     * Внутреннее примечание для администратора.
     */
    @Column(length = 1000)
    private String internalNotes;

    /**
     * Конструктор по умолчанию.
     */
    public Invoice() {
        this.issueDate = LocalDateTime.now();
        this.status = "DRAFT";
    }

    /**
     * Конструктор для быстрой инициализации.
     */
    public Invoice(String invoiceNumber, String vendorName, String customerName) {
        this();
        this.invoiceNumber = invoiceNumber;
        this.vendorName = vendorName;
        this.customerName = customerName;
    }

    // --- MANUAL GETTERS AND SETTERS FOR VOLUME ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public LocalDateTime getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDateTime issueDate) { this.issueDate = issueDate; }

    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }

    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }

    public String getInvoiceType() { return invoiceType; }
    public void setInvoiceType(String invoiceType) { this.invoiceType = invoiceType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getVendorName() { return vendorName; }
    public void setVendorName(String vendorName) { this.vendorName = vendorName; }

    public String getVendorTaxId() { return vendorTaxId; }
    public void setVendorTaxId(String vendorTaxId) { this.vendorTaxId = vendorTaxId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerTaxId() { return customerTaxId; }
    public void setCustomerTaxId(String customerTaxId) { this.customerTaxId = customerTaxId; }

    public String getBillingAddress() { return billingAddress; }
    public void setBillingAddress(String billingAddress) { this.billingAddress = billingAddress; }

    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }

    public double getNetAmount() { return netAmount; }
    public void setNetAmount(double netAmount) { this.netAmount = netAmount; }

    public double getTaxTotal() { return taxTotal; }
    public void setTaxTotal(double taxTotal) { this.taxTotal = taxTotal; }

    public double getDiscountTotal() { return discountTotal; }
    public void setDiscountTotal(double discountTotal) { this.discountTotal = discountTotal; }

    public double getGrossAmount() { return grossAmount; }
    public void setGrossAmount(double grossAmount) { this.grossAmount = grossAmount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getPaymentTerms() { return paymentTerms; }
    public void setPaymentTerms(String paymentTerms) { this.paymentTerms = paymentTerms; }

    public String getOrderReference() { return orderReference; }
    public void setOrderReference(String orderReference) { this.orderReference = orderReference; }

    public String getClientNotes() { return clientNotes; }
    public void setClientNotes(String clientNotes) { this.clientNotes = clientNotes; }

    public String getInternalNotes() { return internalNotes; }
    public void setInternalNotes(String internalNotes) { this.internalNotes = internalNotes; }
}
