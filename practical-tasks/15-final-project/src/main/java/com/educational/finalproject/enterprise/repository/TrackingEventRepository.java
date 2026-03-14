package com.educational.finalproject.enterprise.repository;

import com.educational.finalproject.enterprise.model.TrackingEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для доступа к событиям трекинга.
 */
@Repository
public interface TrackingEventRepository extends JpaRepository<TrackingEvent, Long> {
    List<TrackingEvent> findByShipmentIdOrderByEventTimestampDesc(Long shipmentId);
    List<TrackingEvent> findByIsProblematicTrue();
}
