package com.educational.finalproject.enterprise.repository;

import com.educational.finalproject.enterprise.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderNumber(String orderNumber);
    List<Order> findByStatus(String status);
    List<Order> findByOrderDateBetween(LocalDateTime start, LocalDateTime end);
}
