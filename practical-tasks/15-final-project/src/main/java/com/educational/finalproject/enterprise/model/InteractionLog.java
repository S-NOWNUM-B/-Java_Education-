package com.educational.finalproject.enterprise.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * <p>Сущность InteractionLog представляет собой запись о взаимодействии с клиентом.</p>
 * <p>В CRM-системе важно хранить полную историю коммуникаций. Этот класс фиксирует 
 * детали звонков, встреч, переписки по электронной почте и других активностей. 
 * Это позволяет менеджерам анализировать контекст отношений с клиентом и 
 * принимать взвешенные решения о следующих шагах (Next Steps).</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Entity
@Table(name = "crm_interaction_logs")
public class InteractionLog {

    /**
     * Системный идентификатор записи о взаимодействии.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Дата и время проведения взаимодействия.
     */
    @Column(nullable = false)
    private LocalDateTime interactionDate;

    /**
     * Тип взаимодействия: CALL, EMAIL, MEETING, CHAT, PRESENTATION.
     */
    private String interactionType;

    /**
     * Краткая тема разговора или встречи (Subject).
     */
    private String subject;

    /**
     * Детальное содержание взаимодействия, ключевые достигнутые договоренности.
     */
    @Column(length = 3000)
    private String content;

    /**
     * Ссылка на лид (если контакт еще не стал клиентом).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_id")
    private Lead lead;

    /**
     * Ссылка на сделку (если взаимодействие касается конкретной возможности).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opportunity_id")
    private Opportunity opportunity;

    /**
     * Имя сотрудника, который проводил взаимодействие.
     */
    private String staffMemberName;

    /**
     * Результат взаимодействия (Outcome): POSITIVE, NEGATIVE, NEUTRAL, FOLLOW_UP_REQUIRED.
     */
    private String outcome;

    /**
     * Длительность взаимодействия в минутах (если применимо).
     */
    private int durationMinutes;

    /**
     * Флаг, указывающий, было ли взаимодействие инициировано клиентом.
     */
    private boolean isInbound;

    /**
     * Ссылка на запись разговора или прикрепленный файл лога.
     */
    private String attachmentLink;

    /**
     * Дедлайн для выполнения следующих шагов, согласованных в ходе контакта.
     */
    private LocalDateTime nextStepDeadline;

    /**
     * Конструктор по умолчанию.
     */
    public InteractionLog() {
        this.interactionDate = LocalDateTime.now();
        this.isInbound = false;
    }

    /**
     * Конструктор для быстрой фиксации события.
     * @param subject Тема
     * @param type Тип
     */
    public InteractionLog(String subject, String type) {
        this();
        this.subject = subject;
        this.interactionType = type;
    }

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

    public Lead getLead() { return lead; }
    public void setLead(Lead lead) { this.lead = lead; }

    public Opportunity getOpportunity() { return opportunity; }
    public void setOpportunity(Opportunity opportunity) { this.opportunity = opportunity; }

    public String getStaffMemberName() { return staffMemberName; }
    public void setStaffMemberName(String staffMemberName) { this.staffMemberName = staffMemberName; }

    public String getOutcome() { return outcome; }
    public void setOutcome(String outcome) { this.outcome = outcome; }

    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }

    public boolean isInbound() { return isInbound; }
    public void setInbound(boolean inbound) { isInbound = inbound; }

    public String getAttachmentLink() { return attachmentLink; }
    public void setAttachmentLink(String attachmentLink) { this.attachmentLink = attachmentLink; }

    public LocalDateTime getNextStepDeadline() { return nextStepDeadline; }
    public void setNextStepDeadline(LocalDateTime nextStepDeadline) { this.nextStepDeadline = nextStepDeadline; }
}
