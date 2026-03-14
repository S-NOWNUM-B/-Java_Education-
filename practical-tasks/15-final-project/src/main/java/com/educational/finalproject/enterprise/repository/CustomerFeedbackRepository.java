package com.educational.finalproject.enterprise.repository;

import com.educational.finalproject.enterprise.model.CustomerFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для доступа к данным отзывов клиентов.
 */
@Repository
public interface CustomerFeedbackRepository extends JpaRepository<CustomerFeedback, Long> {
    List<CustomerFeedback> findByStatus(String status);
    List<CustomerFeedback> findByStarRating(int rating);
    List<CustomerFeedback> findByContactId(Long contactId);
}
