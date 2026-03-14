package com.educational.finalproject.enterprise.dto;

import java.time.LocalDateTime;

/**
 * <p>Data Transfer Object для маркетинговой кампании (MarketingCampaign).</p>
 * <p>Класс предназначен для передачи параметров рекламных активностей. 
 * Включает расчетные показатели эффективности (ROI, Cost per Lead), которые 
 * вычисляются на стороне сервера перед отправкой клиенту.</p>
 * 
 * @author Antigravity
 */
public class MarketingCampaignDTO {

    private Long id;
    private String name;
    private String type;
    private String status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double budget;
    private double actualCost;
    private double expectedRevenue;
    
    /**
     * Возврат инвестиций (ROI) в процентах.
     */
    private double roi;
    
    /**
     * Стоимость привлечения одного лида (прогнозная или фактическая).
     */
    private double costPerLead;

    /**
     * Конструктор по умолчанию.
     */
    public MarketingCampaignDTO() {}

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

    public double getRoi() { return roi; }
    public void setRoi(double roi) { this.roi = roi; }

    public double getCostPerLead() { return costPerLead; }
    public void setCostPerLead(double costPerLead) { this.costPerLead = costPerLead; }
}
