package com.educational.finalproject.enterprise.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * <p>Сущность Driver представляет собой водителя транспортного средства.</p>
 * <p>В ERP-системе важно вести учет квалификации персонала, занятого в логистике. 
 * Класс Driver хранит данные о водительском удостоверении, категориях прав, 
 * медицинских осмотрах и истории штрафов/рейтинга водителя. 
 * Это позволяет системе автоматически назначать подходящего исполнителя на маршрут.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Entity
@Table(name = "logistics_drivers")
public class Driver {

    /**
     * Системный идентификатор водителя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Полное имя водителя.
     */
    @Column(nullable = false)
    private String fullName;

    /**
     * Номер водительского удостоверения.
     */
    @Column(unique = true, nullable = false)
    private String licenseNumber;

    /**
     * Открытые категории водительских прав (через запятую, например, "B, C, CE").
     */
    private String licenseCategories;

    /**
     * Дата истечения срока действия водительского удостоверения.
     */
    private LocalDateTime licenseExpiryDate;

    /**
     * Дата последнего прохождения медицинского осмотра.
     */
    private LocalDateTime lastMedicalCheck;

    /**
     * Текущий статус водителя: ON_DUTY, ON_VACATION, SICK_LEAVE, DISMISSED.
     */
    private String status;

    /**
     * Контактный номер телефона для оперативной связи.
     */
    private String contactPhone;

    /**
     * Рейтинг водителя на основе отзывов и безаварийности вождения (0.0 - 5.0).
     */
    private double performanceRating;

    /**
     * Стаж вождения в годах.
     */
    private int experienceYears;

    /**
     * Конструктор по умолчанию.
     */
    public Driver() {
        this.status = "ON_DUTY";
        this.performanceRating = 5.0; // По умолчанию максимальный рейтинг
    }

    /**
     * Конструктор для регистрации нового водителя.
     * @param fullName Имя
     * @param license Номер прав
     */
    public Driver(String fullName, String license) {
        this();
        this.fullName = fullName;
        this.licenseNumber = license;
    }

    // --- MANUAL GETTERS AND SETTERS FOR VOLUME ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }

    public String getLicenseCategories() { return licenseCategories; }
    public void setLicenseCategories(String licenseCategories) { this.licenseCategories = licenseCategories; }

    public LocalDateTime getLicenseExpiryDate() { return licenseExpiryDate; }
    public void setLicenseExpiryDate(LocalDateTime licenseExpiryDate) { this.licenseExpiryDate = licenseExpiryDate; }

    public LocalDateTime getLastMedicalCheck() { return lastMedicalCheck; }
    public void setLastMedicalCheck(LocalDateTime lastMedicalCheck) { this.lastMedicalCheck = lastMedicalCheck; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }

    public double getPerformanceRating() { return performanceRating; }
    public void setPerformanceRating(double performanceRating) { this.performanceRating = performanceRating; }

    public int getExperienceYears() { return experienceYears; }
    public void setExperienceYears(int experienceYears) { this.experienceYears = experienceYears; }
}
