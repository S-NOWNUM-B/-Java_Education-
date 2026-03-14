package com.educational.finalproject.enterprise.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * <p>Сущность CustomerFeedback представляет собой отзыв или тикет от клиента.</p>
 * <p>Лояльность клиентов напрямую зависит от скорости и качества обработки обратной связи. 
 * Данный класс служит для сбора оценок удовлетворенности (CSAT, NPS) и текстовых претензий 
 * или пожеланий. Это позволяет CRM-системе формировать общую картину репутации бренда 
 * и своевременно реагировать на негатив.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Entity
@Table(name = "crm_customer_feedback")
public class CustomerFeedback {

    /**
     * Системный идентификатор отзыва.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Дата и время получения отзыва.
     */
    @Column(nullable = false)
    private LocalDateTime feedbackDate;

    /**
     * Оценка удовлетворенности (Rating) по шкале от 1 до 5 звезд.
     */
    private int starRating;

    /**
     * Текстовое содержание отзыва или жалобы.
     */
    @Column(length = 2000)
    private String comment;

    /**
     * Тип отзыва: PRODUCT_COMPLAINT, SERVICE_QUALITY, COMPLIMENT, FEATURE_REQUEST, SUGGESTION.
     */
    private String feedbackType;

    /**
     * Канал получения отзыва: SURVEY, EMAIL, PHONE, SOCIAL_MEDIA.
     */
    private String channel;

    /**
     * Текущий статус обработки отзыва: NEW, IN_REVIEW, RESOLVED, DISMISSED.
     */
    private String status;

    /**
     * Ссылка на лид или уже существующего клиента.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_id") // Упрощение: используем Lead как базовый контакт
    private Lead contact;

    /**
     * Индекс лояльности (Net Promoter Score) — от 0 до 10.
     */
    private int npsScore;

    /**
     * Срочность обработки (Priority): LOW, MEDIUM, HIGH, URGENT.
     */
    private String priority;

    /**
     * Ответ сотрудника поддержки или менеджера.
     */
    @Column(length = 2000)
    private String officialResponse;

    /**
     * Дата и время закрытия тикета по отзыву.
     */
    private LocalDateTime resolutionDate;

    /**
     * Конструктор по умолчанию.
     */
    public CustomerFeedback() {
        this.feedbackDate = LocalDateTime.now();
        this.status = "NEW";
        this.priority = "MEDIUM";
    }

    /**
     * Конструктор для быстрой фиксации оценки.
     * @param rating Оценка от 1 до 5
     * @param comment Комментарий
     */
    public CustomerFeedback(int rating, String comment) {
        this();
        this.starRating = rating;
        this.comment = comment;
    }

    // --- MANUAL GETTERS AND SETTERS FOR VOLUME ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getFeedbackDate() { return feedbackDate; }
    public void setFeedbackDate(LocalDateTime feedbackDate) { this.feedbackDate = feedbackDate; }

    public int getStarRating() { return starRating; }
    public void setStarRating(int starRating) { this.starRating = starRating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getFeedbackType() { return feedbackType; }
    public void setFeedbackType(String feedbackType) { this.feedbackType = feedbackType; }

    public String getChannel() { return channel; }
    public void setChannel(String channel) { this.channel = channel; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Lead getContact() { return contact; }
    public void setContact(Lead contact) { this.contact = contact; }

    public int getNpsScore() { return npsScore; }
    public void setNpsScore(int npsScore) { this.npsScore = npsScore; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getOfficialResponse() { return officialResponse; }
    public void setOfficialResponse(String officialResponse) { this.officialResponse = officialResponse; }

    public LocalDateTime getResolutionDate() { return resolutionDate; }
    public void setResolutionDate(LocalDateTime resolutionDate) { this.resolutionDate = resolutionDate; }
}
