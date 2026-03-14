package com.educational.finalproject.enterprise.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * <p>Сущность FinancialTransaction представляет собой отдельную финансовую операцию (проводку).</p>
 * <p>Каждая транзакция фиксирует изменение баланса в конкретной бухгалтерской книге (Ledger). 
 * Данный класс охватывает широкий спектр атрибутов, необходимых для детального финансового учета, 
 * включая аудит, налоговую классификацию и привязку к внешним документам.</p>
 * 
 * <p>Транзакции поддерживают различные типы: DEBIT (дебет) и CREDIT (кредит), что позволяет 
 * реализовать классическую бухгалтерию.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Entity
@Table(name = "financial_transactions")
public class FinancialTransaction {

    /**
     * Уникальный системный идентификатор транзакции.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Уникальный номер транзакции для внешних отчетов (например, "TXN-2024-0001").
     */
    @Column(nullable = false, unique = true)
    private String transactionNumber;

    /**
     * Дата и время совершения финансовой операции.
     */
    @Column(nullable = false)
    private LocalDateTime transactionDate;

    /**
     * Сумма транзакции. Положительная для CREDIT, отрицательная для DEBIT (или по типу).
     */
    private double amount;

    /**
     * Валюта транзакции (может отличаться от валюты книги при мультивалютном учете).
     */
    private String currency;

    /**
     * Тип транзакции: INCOME, EXPENSE, TRANSFER, TAX_PAYMENT, SALARY.
     */
    private String type;

    /**
     * Описание сути операции (например, "Оплата счета №45 за канцтовары").
     */
    private String description;

    /**
     * Имя пользователя или системного процесса, инициировавшего транзакцию (для аудита).
     */
    private String createdBy;

    /**
     * Ссылка на главную книгу, к которой относится эта транзакция.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ledger_id")
    private Ledger ledger;

    /**
     * Статус транзакции: PENDING, COMPLETED, REVERSED, CANCELLED.
     */
    private String status;

    /**
     * Налоговая категория операции (например, "VAT_20", "EXEMPT").
     */
    private String taxCategory;

    /**
     * Сумма налога, включенная в транзакцию.
     */
    private double taxAmount;

    /**
     * Ссылка на связанный документ (например, ID заказа или ID инвойса).
     */
    private String referenceDocumentId;

    /**
     * Конструктор по умолчанию. Инициализирует системные поля.
     */
    public FinancialTransaction() {
        this.transactionDate = LocalDateTime.now();
        this.status = "PENDING";
    }

    /**
     * Конструктор с основными параметрами.
     * @param transactionNumber Номер
     * @param amount Сумма
     * @param type Тип
     */
    public FinancialTransaction(String transactionNumber, double amount, String type) {
        this();
        this.transactionNumber = transactionNumber;
        this.amount = amount;
        this.type = type;
    }

    // --- MANUAL GETTERS AND SETTERS FOR VOLUME ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Ledger getLedger() {
        return ledger;
    }

    public void setLedger(Ledger ledger) {
        this.ledger = ledger;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaxCategory() {
        return taxCategory;
    }

    public void setTaxCategory(String taxCategory) {
        this.taxCategory = taxCategory;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getReferenceDocumentId() {
        return referenceDocumentId;
    }

    public void setReferenceDocumentId(String referenceDocumentId) {
        this.referenceDocumentId = referenceDocumentId;
    }
}
