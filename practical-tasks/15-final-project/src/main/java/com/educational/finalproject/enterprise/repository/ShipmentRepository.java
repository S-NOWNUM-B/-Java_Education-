package com.educational.finalproject.enterprise.repository;

import com.educational.finalproject.enterprise.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для доступа к данным отгрузок.
 */
@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    Optional<Shipment> findByTrackingNumber(String trackingNumber);
    List<Shipment> findByStatus(String status);
    List<Shipment> findByDestinationAddressContainingIgnoreCase(String city);
}
