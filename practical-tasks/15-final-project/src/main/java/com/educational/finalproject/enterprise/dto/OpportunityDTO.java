package com.educational.finalproject.enterprise.dto;

import java.time.LocalDateTime;

/**
 * <p>Data Transfer Object для сделки (Opportunity).</p>
 * <p>Класс служит для передачи деталей воронки продаж. Включает ссылки 
 * на связанные сущности по ID и имени для минимизации нагрузки на сеть 
 * при просмотре списков сделок.</p>
 * 
 * @author Antigravity
 */
public class OpportunityDTO {

    private Long id;
    private String name;
    private double expectedRevenue;
    private int probability;
    private String stage;
    private LocalDateTime closedDate;
    private LocalDateTime createdAt;
    private String opportunityType;
    private String description;
    
    private Long leadId;
    private String leadName;
    
    private String ownerName;
    private double customerBudget;
    private String riskLevel;

    /**
     * Конструктор по умолчанию.
     */
    public OpportunityDTO() {}

    // --- MANUAL GETTERS AND SETTERS FOR VOLUME ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getExpectedRevenue() { return expectedRevenue; }
    public void setExpectedRevenue(double expectedRevenue) { this.expectedRevenue = expectedRevenue; }

    public int getProbability() { return probability; }
    public void setProbability(int probability) { this.probability = probability; }

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

    public Long getLeadId() { return leadId; }
    public void setLeadId(Long leadId) { this.leadId = leadId; }

    public String getLeadName() { return leadName; }
    public void setLeadName(String leadName) { this.leadName = leadName; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public double getCustomerBudget() { return customerBudget; }
    public void setCustomerBudget(double customerBudget) { this.customerBudget = customerBudget; }

    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }
}
