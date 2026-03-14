package com.educational.finalproject.enterprise.dto;

import java.time.LocalDateTime;

/**
 * <p>Data Transfer Object для регистрации взаимодействия (InteractionLog).</p>
 * <p>Класс используется для отображения истории контактов с клиентом. 
 * Включает текстовые расшифровки и ссылки на ответственных сотрудников.</p>
 * 
 * @author Antigravity
 */
public class InteractionLogDTO {

    private Long id;
    private LocalDateTime interactionDate;
    private String interactionType;
    private String subject;
    private String content;
    
    private Long leadId;
    private String leadName;
    
    private Long opportunityId;
    private String opportunityName;
    
    private String staffMemberName;
    private String outcome;
    private int durationMinutes;
    private boolean isInbound;

    /**
     * Конструктор по умолчанию.
     */
    public InteractionLogDTO() {}

    // --- MANUAL GETTERS AND SETTERS FOR VOLUME ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getInteractionDate() { return interactionDate; }
    public void setInteractionDate(LocalDateTime interactionDate) { this.interactionDate = interactionDate; }

    public String getInteractionType() { return interactionType; }
    public void setInteractionType(String interactionType) { this.interactionType = interactionType; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Long getLeadId() { return leadId; }
    public void setLeadId(Long leadId) { this.leadId = leadId; }

    public String getLeadName() { return leadName; }
    public void setLeadName(String leadName) { this.leadName = leadName; }

    public Long getOpportunityId() { return opportunityId; }
    public void setOpportunityId(Long opportunityId) { this.opportunityId = opportunityId; }

    public String getOpportunityName() { return opportunityName; }
    public void setOpportunityName(String opportunityName) { this.opportunityName = opportunityName; }

    public String getStaffMemberName() { return staffMemberName; }
    public void setStaffMemberName(String staffMemberName) { this.staffMemberName = staffMemberName; }

    public String getOutcome() { return outcome; }
    public void setOutcome(String outcome) { this.outcome = outcome; }

    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }

    public boolean isInbound() { return isInbound; }
    public void setInbound(boolean inbound) { isInbound = inbound; }
}
