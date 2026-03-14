package com.educational.finalproject.enterprise.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * <p>Сущность MarketingCampaign представляет собой маркетинговую инициативу предприятия.</p>
 * <p>Данный класс служит для планирования, запуска и анализа эффективности рекламных 
 * и информационных кампаний. Это позволяет CRM-системе отслеживать возврат инвестиций (ROI) 
 * и связывать входящие лиды с конкретными активностями отдела маркетинга.</p>
 * 
 * <p>Кампания может охватывать различные каналы коммуникации: Email, Social Media, 
 * Webinars, Direct Mail и т.д.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Entity
@Table(name = "crm_campaigns")
public class MarketingCampaign {

    /**
     * Системный идентификатор кампании.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название кампании (например, "Spring Promo 2024").
     */
    @Column(nullable = false)
    private String name;

    /**
     * Тип кампании: EMAIL, SOCIAL_MEDIA, EVENT, ADVERTISING, OTHER.
     */
    private String type;

    /**
     * Текущий статус: PLANNED, ACTIVE, PAUSED, COMPLETED, CANCELLED.
     */
    private String status;

    /**
     * Дата и время официального старта кампании.
     */
    private LocalDateTime startDate;

    /**
     * Планируемая дата завершения кампании.
     */
    private LocalDateTime endDate;

    /**
     * Выделенный бюджет на проведение кампании.
     */
    private double budget;

    /**
     * Фактические затраты на кампанию (на момент запроса).
     */
    private double actualCost;

    /**
     * Ожидаемая выручка от сделок, инициированных данной кампанией.
     */
    private double expectedRevenue;

    /**
     * Целевая аудитория (краткое описание, сегмент).
     */
    private String targetAudience;

    /**
     * Ключевое сообщение или слоган кампании.
     */
    @Column(length = 1000)
    private String keyMessage;

    /**
     * Цель кампании (например, "Привлечение 100 новых лидов").
     */
    private String objective;

    /**
     * Имя менеджера по маркетингу, ответственного за выполнение.
     */
    private String managerName;

    /**
     * Количество кликов или переходов (для цифровых каналов).
     */
    private long metricsClicks;

    /**
     * Количество показов (Impressions).
     */
    private long metricsImpressions;

    /**
     * Конструктор по умолчанию.
     */
    public MarketingCampaign() {
        this.status = "PLANNED";
        this.startDate = LocalDateTime.now();
    }

    /**
     * Конструктор для быстрой инициализации.
     * @param name Название
     * @param budget Бюджет
     */
    public MarketingCampaign(String name, double budget) {
        this();
        this.name = name;
        this.budget = budget;
    }

    // --- MANUAL GETTERS AND SETTERS FOR VOLUME ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }

    public double getActualCost() { return actualCost; }
    public void setActualCost(double actualCost) { this.actualCost = actualCost; }

    public double getExpectedRevenue() { return expectedRevenue; }
    public void setExpectedRevenue(double expectedRevenue) { this.expectedRevenue = expectedRevenue; }

    public String getTargetAudience() { return targetAudience; }
    public void setTargetAudience(String targetAudience) { this.targetAudience = targetAudience; }

    public String getKeyMessage() { return keyMessage; }
    public void setKeyMessage(String keyMessage) { this.keyMessage = keyMessage; }

    public String getObjective() { return objective; }
    public void setObjective(String objective) { this.objective = objective; }

    public String getManagerName() { return managerName; }
    public void setManagerName(String managerName) { this.managerName = managerName; }

    public long getMetricsClicks() { return metricsClicks; }
    public void setMetricsClicks(long metricsClicks) { this.metricsClicks = metricsClicks; }

    public long getMetricsImpressions() { return metricsImpressions; }
    public void setMetricsImpressions(long metricsImpressions) { this.metricsImpressions = metricsImpressions; }
}
