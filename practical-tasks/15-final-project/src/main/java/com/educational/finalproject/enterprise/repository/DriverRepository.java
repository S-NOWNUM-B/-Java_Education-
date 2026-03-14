package com.educational.finalproject.enterprise.repository;

import com.educational.finalproject.enterprise.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для доступа к данным водителей.
 */
@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByLicenseNumber(String license);
    List<Driver> findByStatus(String status);
    List<Driver> findByExperienceYearsGreaterThan(int years);
}
