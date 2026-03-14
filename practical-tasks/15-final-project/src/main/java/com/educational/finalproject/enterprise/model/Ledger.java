package com.educational.finalproject.enterprise.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Сущность Ledger представляет собой главную бухгалтерскую книгу предприятия.</p>
 * <p>Этот класс является центральным элементом финансового модуля ERP-системы. 
 * Он предназначен для консолидации всех финансовых проводок, ведения баланса и 
 * подготовки данных для налоговой отчетности.</p>
 * 
 * <p>В реальной enterprise-системе главная книга обеспечивает целостность данных 
 * согласно принципам двойной записи. Данный класс имитирует эту сложность, 
 * предоставляя детализированные поля для аудита и контроля.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Entity
@Table(name = "ledgers")
public class Ledger {

    /**
     * Уникальный идентификатор записи в базе данных.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Уникальное имя или код бухгалтерской книги (например, "GENERAL_LEDGER_2024").
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Описание назначения данной книги, включая юридические ссылки на нормативы.
     */
    @Column(length = 2000)
    private String description;

    /**
     * Тип бухгалтерской книги (Asset, Liability, Equity, Revenue, Expense).
     */
    private String ledgerType;

    /**
     * Текущий баланс по книге. Рассчитывается как сумма всех транзакций.
     */
    private double currentBalance;

    /**
     * Валюта, в которой ведется учет в данной книге (например, RUB, USD, EUR).
     */
    private String currency;

    /**
     * Дата и время создания книги в системе.
     */
    private LocalDateTime openingDate;

    /**
     * Дата и время последнего закрытия периода.
     */
    private LocalDateTime lastClosingDate;

    /**
     * Список всех транзакций, привязанных к данной книге.
     * Используется связь One-To-Many с каскадным обновлением.
     */
    @OneToMany(mappedBy = "ledger", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FinancialTransaction> transactions;

    /**
     * Флаг, указывающий, активна ли книга для новых записей.
     */
    private boolean isActive;

    /**
     * Поле для хранения метаданных в формате JSON или XML (имитация).
     */
    @Column(columnDefinition = "TEXT")
    private String metadata;

    /**
     * Конструктор по умолчанию для JPA.
     */
    public Ledger() {
        this.transactions = new ArrayList<>();
        this.openingDate = LocalDateTime.now();
        this.isActive = true;
    }

    /**
     * Расширенный конструктор для быстрой инициализации.
     * @param name Имя книги
     * @param currency Валюта учета
     */
    public Ledger(String name, String currency) {
        this();
        this.name = name;
        this.currency = currency;
    }

    // --- MANUAL GETTERS AND SETTERS FOR VOLUME ---

    /**
     * Получает идентификатор книги.
     * @return Long id
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор книги.
     * @param id Уникальный ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Получает имя книги.
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает имя книги.
     * @param name Название
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получает описание книги.
     * @return String description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Устанавливает описание книги.
     * @param description Текст описания
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Получает тип книги.
     * @return String type
     */
    public String getLedgerType() {
        return ledgerType;
    }

    /**
     * Устанавливает тип книги.
     * @param ledgerType Тип (Asset/Liability/etc)
     */
    public void setLedgerType(String ledgerType) {
        this.ledgerType = ledgerType;
    }

    /**
     * Получает текущий баланс.
     * @return double balance
     */
    public double getCurrentBalance() {
        return currentBalance;
    }

    /**
     * Устанавливает текущий баланс.
     * @param currentBalance Актуальная сумма
     */
    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    /**
     * Получает валюту.
     * @return String currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Устанавливает валюту.
     * @param currency Код валюты
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Получает дату открытия.
     * @return LocalDateTime
     */
    public LocalDateTime getOpeningDate() {
        return openingDate;
    }

    /**
     * Устанавливает дату открытия.
     * @param openingDate Время открытия
     */
    public void setOpeningDate(LocalDateTime openingDate) {
        this.openingDate = openingDate;
    }

    /**
     * Получает дату последнего закрытия.
     * @return LocalDateTime
     */
    public LocalDateTime getLastClosingDate() {
        return lastClosingDate;
    }

    /**
     * Устанавливает дату последнего закрытия.
     * @param lastClosingDate Время закрытия
     */
    public void setLastClosingDate(LocalDateTime lastClosingDate) {
        this.lastClosingDate = lastClosingDate;
    }

    /**
     * Получает список транзакций.
     * @return List of FinancialTransaction
     */
    public List<FinancialTransaction> getTransactions() {
        return transactions;
    }

    /**
     * Устанавливает список транзакций.
     * @param transactions Список проводок
     */
    public void setTransactions(List<FinancialTransaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * Проверяет, активна ли книга.
     * @return boolean
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Устанавливает статус активности.
     * @param active Флаг активности
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Получает метаданные.
     * @return String metadata
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * Устанавливает метаданные.
     * @param metadata Строка метаданных
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * Вспомогательный метод для добавления транзакции.
     * @param transaction Финансовая операция
     */
    public void addTransaction(FinancialTransaction transaction) {
        this.transactions.add(transaction);
        transaction.setLedger(this);
        this.currentBalance += transaction.getAmount();
    }
}
