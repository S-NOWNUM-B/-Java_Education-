package com.educational.finalproject.enterprise.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * <p>Сущность TrackingEvent представляет собой запись о перемещении груза.</p>
 * <p>Для обеспечения высокого уровня сервиса ERP-система должна фиксировать 
 * каждое значимое событие в процессе доставки: прохождение контрольных точек, 
 * перегрузку, задержки или факты передачи груза.</p>
 * 
 * <p>Данный класс служит основой для построения таймлайна доставки (Tracking Page) 
 * для конечного клиента.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Entity
@Table(name = "logistics_tracking_events")
public class TrackingEvent {

    /**
     * Системный идентификатор события трекинга.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Ссылка на отгрузку, к которой относится данное событие.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

    /**
     * Дата и время возникновения события.
     */
    @Column(nullable = false)
    private LocalDateTime eventTimestamp;

    /**
     * Тип события: DEPARTED, ARRIVED, SORTING, CLEARANCE_COMPLETED, DELAYED.
     */
    private String eventType;

    /**
     * Текущее географическое местоположение груза (город или координаты).
     */
    private String location;

    /**
     * Подробное описание события для пользователя.
     */
    @Column(length = 1000)
    private String description;

    /**
     * Имя сотрудника, зарегистрировавшего событие вручную (если применимо).
     */
    private String recordedBy;

    /**
     * Флаг, указывающий на наличие проблемы или задержки (Incident).
     */
    private boolean isProblematic;

    /**
     * Публичный статус, отображаемый клиенту на странице отслеживания.
     */
    private String publicDisplayMessage;

    /**
     * Конструктор по умолчанию.
     */
    public TrackingEvent() {
        this.eventTimestamp = LocalDateTime.now();
        this.isProblematic = false;
    }

    /**
     * Конструктор для быстрой записи статуса.
     * @param shipment Отгрузка
     * @param type Тип события
     */
    public TrackingEvent(Shipment shipment, String type) {
        this();
        this.shipment = shipment;
        this.eventType = type;
    }

    // --- MANUAL GETTERS AND SETTERS FOR VOLUME ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Shipment getShipment() { return shipment; }
    public void setShipment(Shipment shipment) { this.shipment = shipment; }

    public LocalDateTime getEventTimestamp() { return eventTimestamp; }
    public void setEventTimestamp(LocalDateTime eventTimestamp) { this.eventTimestamp = eventTimestamp; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getRecordedBy() { return recordedBy; }
    public void setRecordedBy(String recordedBy) { this.recordedBy = recordedBy; }

    public boolean isProblematic() { return isProblematic; }
    public void setProblematic(boolean problematic) { isProblematic = problematic; }

    public String getPublicDisplayMessage() { return publicDisplayMessage; }
    public void setPublicDisplayMessage(String publicDisplayMessage) { this.publicDisplayMessage = publicDisplayMessage; }
}
