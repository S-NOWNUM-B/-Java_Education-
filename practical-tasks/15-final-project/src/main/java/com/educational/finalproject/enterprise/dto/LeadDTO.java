package com.educational.finalproject.enterprise.dto;

import java.time.LocalDateTime;

/**
 * <p>Data Transfer Object для потенциального клиента (Lead).</p>
 * <p>Класс обеспечивает передачу информации о лидах между клиентской частью 
 * приложения и сервером. Включает агрегированные поля для удобства отображения 
 * в таблицах CRM.</p>
 * 
 * @author Antigravity
 */
public class LeadDTO {

    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String companyName;
    private String jobTitle;
    private String source;
    private String status;
    private int score;
    private LocalDateTime expectedConversionDate;
    private LocalDateTime createdAt;
    private String region;
    private String industry;
    
    /**
     * Количество взаимодействий с лидом (вычисляемое поле).
     */
    private int interactionsCount;

    /**
     * Конструктор по умолчанию.
     */
    public LeadDTO() {}

    // --- MANUAL GETTERS AND SETTERS FOR VOLUME ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public LocalDateTime getExpectedConversionDate() { return expectedConversionDate; }
    public void setExpectedConversionDate(LocalDateTime expectedConversionDate) { this.expectedConversionDate = expectedConversionDate; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public int getInteractionsCount() { return interactionsCount; }
    public void setInteractionsCount(int interactionsCount) { this.interactionsCount = interactionsCount; }
}
