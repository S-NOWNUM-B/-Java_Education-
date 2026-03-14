package com.educational.finalproject.enterprise.repository;

import com.educational.finalproject.enterprise.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для доступа к данным маршрутов.
 */
@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findByStatus(String status);
    List<Route> findByPrimaryDriverId(Long driverId);
}
