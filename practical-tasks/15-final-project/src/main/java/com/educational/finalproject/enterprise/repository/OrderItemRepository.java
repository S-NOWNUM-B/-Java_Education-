package com.educational.finalproject.enterprise.repository;

import com.educational.finalproject.enterprise.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderOrderNumber(String orderNumber);
}
