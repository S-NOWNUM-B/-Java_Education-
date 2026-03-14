package com.educational.finalproject.enterprise.dto;

import java.time.LocalDateTime;

/**
 * <p>Data Transfer Object для событий трекинга (TrackingEventDTO).</p>
 * <p>Служит для передачи истории перемещения груза. Форматирует данные 
 * для отображения на карте или в таймлайне доставки.</p>
 * 
 * @author Antigravity
 */
public class TrackingEventDTO {

    private Long id;
    private Long shipmentId;
    private String trackingNumber;
    private LocalDateTime eventTimestamp;
    private String eventType;
    private String location;
    private String description;
    private boolean isProblematic;
    private String publicDisplayMessage;

    /**
     * Конструктор по умолчанию.
     */
    public TrackingEventDTO() {}

    // --- MANUAL GETTERS AND SETTERS FOR VOLUME ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getShipmentId() { return shipmentId; }
    public void setShipmentId(Long shipmentId) { this.shipmentId = shipmentId; }

    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }

    public LocalDateTime getEventTimestamp() { return eventTimestamp; }
    public void setEventTimestamp(LocalDateTime eventTimestamp) { this.eventTimestamp = eventTimestamp; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isProblematic() { return isProblematic; }
    public void setProblematic(boolean problematic) { isProblematic = problematic; }

    public String getPublicDisplayMessage() { return publicDisplayMessage; }
    public void setPublicDisplayMessage(String publicDisplayMessage) { this.publicDisplayMessage = publicDisplayMessage; }
}
