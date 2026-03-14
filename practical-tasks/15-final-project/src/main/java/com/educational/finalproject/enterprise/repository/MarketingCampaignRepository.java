package com.educational.finalproject.enterprise.repository;

import com.educational.finalproject.enterprise.model.MarketingCampaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для доступа к данным маркетинговых кампаний.
 */
@Repository
public interface MarketingCampaignRepository extends JpaRepository<MarketingCampaign, Long> {
    List<MarketingCampaign> findByStatus(String status);
    List<MarketingCampaign> findByManagerName(String managerName);
}
