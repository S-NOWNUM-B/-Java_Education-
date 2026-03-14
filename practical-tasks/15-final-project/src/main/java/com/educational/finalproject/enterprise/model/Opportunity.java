package com.educational.finalproject.enterprise.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * <p>Сущность Opportunity представляет собой потенциальную сделку в CRM-системе.</p>
 * <p>Когда лид проходит квалификацию, он конвертируется в возможность (сделку). 
 * Данный класс служит для управления процессом продаж на средних и поздних этапах 
 * маркетинговой воронки. Он хранит данные о сумме сделки, вероятности успеха 
 * и текущем этапе переговоров.</p>
 * 
 * <p>Этот класс является одним из самых насыщенных данными в CRM модуле, 
 * так как сделки требуют детального анализа рисков и прогнозирования прибыли.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Entity
@Table(name = "crm_opportunities")
public class Opportunity {

    /**
     * Системный идентификатор сделки.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название сделки (например, "Поставка серверов для ПАО 'Техно'").
     */
    @Column(nullable = false)
    private String name;

    /**
     * Ожидаемая сумма сделки в валюте системы.
     */
    private double expectedRevenue;

    /**
     * Вероятность успешного закрытия сделки (в процентах, от 0 до 100).
     */
    private double probability;

    /**
     * Текущий этап воронки продаж: 
     * PROSPECTING, QUALIFICATION, PROPOSAL, NEGOTIATION, CLOSED_WON, CLOSED_LOST.
     */
    private String stage;

    /**
     * Прогнозируемая дата закрытия сделки.
     */
    private LocalDateTime closedDate;

    /**
     * Дата и время создания записи о сделке.
     */
    private LocalDateTime createdAt;

    /**
     * Тип сделки: NEW_BUSINESS, RENEWAL, UPSELL.
     */
    private String opportunityType;

    /**
     * Описание предмета сделки и ключевых требований клиента.
     */
    @Column(length = 2000)
    private String description;

    /**
     * Ссылка на лид, из которого была создана сделка.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_id")
    private Lead sourceLead;

    /**
     * Имя ответственного менеджера (Account Manager).
     */
    private String ownerName;

    /**
     * Бюджет клиента на данную задачу (если известен).
     */
    private double customerBudget;

    /**
     * Основной конкурент в данной сделке.
     */
    private String mainCompetitor;

    /**
     * Флаг, указывающий, является ли сделка стратегически важной для компании.
     */
    private boolean isStrategic;

    /**
     * Уровень риска потери сделки: LOW, MEDIUM, HIGH, CRITICAL.
     */
    private String riskLevel;

    /**
     * Информация о необходимости привлечения технических специалистов (Presale).
     */
    private String presaleSupportNeeds;

    /**
     * Конструктор по умолчанию.
     */
    public Opportunity() {
        this.createdAt = LocalDateTime.now();
        this.stage = "PROSPECTING";
        this.probability = 10.0; // Начальная вероятность
    }

    /**
     * Конструктор для создания новой сделки.
     * @param name Название
     * @param revenue Ожидаемый доход
     */
    public Opportunity(String name, double revenue) {
        this();
        this.name = name;
        this.expectedRevenue = revenue;
    }

    // --- MANUAL GETTERS AND SETTERS FOR VOLUME ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getExpectedRevenue() { return expectedRevenue; }
    public void setExpectedRevenue(double expectedRevenue) { this.expectedRevenue = expectedRevenue; }

    public double getProbability() { return probability; }
    public void setProbability(double probability) { this.probability = probability; }

    public String getStage() { return stage; }
    public void setStage(String stage) { this.stage = stage; }

    public LocalDateTime getClosedDate() { return closedDate; }
    public void setClosedDate(LocalDateTime closedDate) { this.closedDate = closedDate; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getOpportunityType() { return opportunityType; }
    public void setOpportunityType(String opportunityType) { this.opportunityType = opportunityType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Lead getSourceLead() { return sourceLead; }
    public void setSourceLead(Lead sourceLead) { this.sourceLead = sourceLead; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public double getCustomerBudget() { return customerBudget; }
    public void setCustomerBudget(double customerBudget) { this.customerBudget = customerBudget; }

    public String getMainCompetitor() { return mainCompetitor; }
    public void setMainCompetitor(String mainCompetitor) { this.mainCompetitor = mainCompetitor; }

    public boolean isStrategic() { return isStrategic; }
    public void setStrategic(boolean strategic) { isStrategic = strategic; }

    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }

    public String getPresaleSupportNeeds() { return presaleSupportNeeds; }
    public void setPresaleSupportNeeds(String presaleSupportNeeds) { this.presaleSupportNeeds = presaleSupportNeeds; }
}
