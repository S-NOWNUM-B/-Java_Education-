package com.educational.finalproject.enterprise.repository;

import com.educational.finalproject.enterprise.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для доступа к данным лидов.
 */
@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
    List<Lead> findByStatus(String status);
    List<Lead> findByEmail(String email);
    List<Lead> findByCompanyNameContainingIgnoreCase(String companyName);
}
