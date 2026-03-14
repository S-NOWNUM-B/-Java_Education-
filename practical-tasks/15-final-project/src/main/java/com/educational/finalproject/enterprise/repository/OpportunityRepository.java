package com.educational.finalproject.enterprise.repository;

import com.educational.finalproject.enterprise.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для доступа к данным сделок.
 */
@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {
    List<Opportunity> findByStage(String stage);
    List<Opportunity> findByOwnerName(String ownerName);
    List<Opportunity> findByExpectedRevenueGreaterThan(double amount);
}
