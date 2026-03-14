package com.educational.finalproject.enterprise.dto;

import java.time.LocalDateTime;

/**
 * <p>Data Transfer Object для водителя (DriverDTO).</p>
 * <p>Передает информацию о персонале логистики. Включает данные 
 * о квалификации и текущем рабочем статусе.</p>
 * 
 * @author Antigravity
 */
public class DriverDTO {

    private Long id;
    private String fullName;
    private String licenseCategories;
    private LocalDateTime licenseExpiryDate;
    private String status;
    private String contactPhone;
    private double performanceRating;
    private int experienceYears;

    /**
     * Конструктор по умолчанию.
     */
    public DriverDTO() {}

    // --- MANUAL GETTERS AND SETTERS FOR VOLUME ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getLicenseCategories() { return licenseCategories; }
    public void setLicenseCategories(String licenseCategories) { this.licenseCategories = licenseCategories; }

    public LocalDateTime getLicenseExpiryDate() { return licenseExpiryDate; }
    public void setLicenseExpiryDate(LocalDateTime licenseExpiryDate) { this.licenseExpiryDate = licenseExpiryDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }

    public double getPerformanceRating() { return performanceRating; }
    public void setPerformanceRating(double performanceRating) { this.performanceRating = performanceRating; }

    public int getExperienceYears() { return experienceYears; }
    public void setExperienceYears(int experienceYears) { this.experienceYears = experienceYears; }
}
