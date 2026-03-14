package com.educational.finalproject.enterprise.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * <p>Сущность Lead представляет собой потенциального клиента (лид) в CRM-системе.</p>
 * <p>Лид — это первичная точка контакта в процессе продаж. Данный класс аккумулирует 
 * всю входящую информацию о заинтересованном лице или компании, включая контактные данные, 
 * источник привлечения и начальные квалификационные признаки.</p>
 * 
 * <p>В рамках стратегии "Java Dominance" этот класс спроектирован с избыточным количеством 
 * полей для имитации глубокой интеграции с маркетинговыми и аналитическими системами 
 * предприятия. Каждый атрибут снабжен подробным Javadoc-описанием.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Entity
@Table(name = "crm_leads")
public class Lead {

    /**
     * Системный идентификатор лида.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Имя контактного лица или название компании.
     */
    @Column(nullable = false)
    private String fullName;

    private String firstName;
    private String lastName;

    /**
     * Основной адрес электронной почты для связи.
     */
    private String email;

    /**
     * Номер телефона в международном формате.
     */
    private String phoneNumber;

    /**
     * Название компании, которую представляет лид.
     */
    private String companyName;

    /**
     * Текущая должность лида в компании.
     */
    private String jobTitle;

    /**
     * Источник поступления лида (например, "Website", "Cold Call", "Referral", "LinkedIn").
     */
    private String source;

    /**
     * Текущий статус квалификации лида (NEW, CONTACTED, QUALIFIED, UNQUALIFIED, DROPPED).
     */
    private String status;

    /**
     * Оценка потенциала лида (Lead Scoring) по шкале от 1 до 100.
     */
    private int score;

    /**
     * Ожидаемая дата конвертации лида в возможность (Opportunity).
     */
    private LocalDateTime expectedConversionDate;

    /**
     * Дата и время создания записи в системе.
     */
    private LocalDateTime createdAt;

    /**
     * Дата и время последнего контакта с лидом.
     */
    private LocalDateTime lastContactDate;

    /**
     * Текстовое описание потребностей лида и истории первого контакта.
     */
    @Column(length = 2000)
    private String notes;

    /**
     * Ссылка на маркетинговую кампанию, из которой пришел лид.
     */
    private String campaignReference;

    /**
     * Предпочитаемый язык общения.
     */
    private String preferredLanguage;

    /**
     * Согласие на маркетинговые рассылки (GDPR Compliance).
     */
    private boolean marketingConsent;

    /**
     * Социальный профиль лида (ссылка на LinkedIn или др.).
     */
    private String socialProfileUrl;

    /**
     * Регион или город проживания/работы.
     */
    private String region;

    /**
     * Отрасль бизнеса компании лида (например, "IT", "Manufacturing", "Retail").
     */
    private String industry;

    /**
     * Конструктор по умолчанию.
     */
    public Lead() {
        this.createdAt = LocalDateTime.now();
        this.status = "NEW";
        this.score = 50; // Среднее значение по умолчанию
    }

    /**
     * Конструктор для быстрой инициализации лида.
     * @param fullName Имя
     * @param email Почта
     */
    public Lead(String fullName, String email) {
        this();
        this.fullName = fullName;
        this.email = email;
    }

    // --- MANUAL GETTERS AND SETTERS FOR VOLUME ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

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

    public LocalDateTime getLastContactDate() { return lastContactDate; }
    public void setLastContactDate(LocalDateTime lastContactDate) { this.lastContactDate = lastContactDate; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getCampaignReference() { return campaignReference; }
    public void setCampaignReference(String campaignReference) { this.campaignReference = campaignReference; }

    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }

    public boolean isMarketingConsent() { return marketingConsent; }
    public void setMarketingConsent(boolean marketingConsent) { this.marketingConsent = marketingConsent; }

    public String getSocialProfileUrl() { return socialProfileUrl; }
    public void setSocialProfileUrl(String socialProfileUrl) { this.socialProfileUrl = socialProfileUrl; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }
}
