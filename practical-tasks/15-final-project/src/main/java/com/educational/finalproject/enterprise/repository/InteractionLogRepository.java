package com.educational.finalproject.enterprise.repository;

import com.educational.finalproject.enterprise.model.InteractionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для доступа к данным журналов взаимодействия.
 */
@Repository
public interface InteractionLogRepository extends JpaRepository<InteractionLog, Long> {
    List<InteractionLog> findByLeadId(Long leadId);
    List<InteractionLog> findByOpportunityId(Long opportunityId);
    List<InteractionLog> findByStaffMemberName(String staffName);
}
