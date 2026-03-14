package com.educational.finalproject.enterprise.dto;

import java.time.LocalDateTime;

/**
 * <p>Data Transfer Object для отзывов клиентов (CustomerFeedback).</p>
 * <p>Данный объект служит для представления агрегированной информации об обратной связи. 
 * Он отделяет детали технического тикета от информации, необходимой 
 * для клиентского интерфейса мониторинга лояльности.</p>
 * 
 * @author Antigravity
 */
public class CustomerFeedbackDTO {

    private Long id;
    private LocalDateTime feedbackDate;
    private int starRating;
    private String comment;
    private String feedbackType;
    private String channel;
    private String status;
    
    private Long contactId;
    private String contactName;
    
    private int npsScore;
    private String priority;
    private String officialResponse;
    private LocalDateTime resolutionDate;

    /**
     * Конструктор по умолчанию.
     */
    public CustomerFeedbackDTO() {}

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

    public Long getContactId() { return contactId; }
    public void setContactId(Long contactId) { this.contactId = contactId; }

    public String getContactName() { return contactName; }
    public void setContactName(String contactName) { this.contactName = contactName; }

    public int getNpsScore() { return npsScore; }
    public void setNpsScore(int npsScore) { this.npsScore = npsScore; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getOfficialResponse() { return officialResponse; }
    public void setOfficialResponse(String officialResponse) { this.officialResponse = officialResponse; }

    public LocalDateTime getResolutionDate() { return resolutionDate; }
    public void setResolutionDate(LocalDateTime resolutionDate) { this.resolutionDate = resolutionDate; }
}
