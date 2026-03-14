package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.dto.MarketingCampaignDTO;
import com.educational.finalproject.enterprise.mapper.MarketingCampaignMapper;
import com.educational.finalproject.enterprise.model.MarketingCampaign;
import com.educational.finalproject.enterprise.repository.MarketingCampaignRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>Сервис управления маркетинговыми кампаниями (MarketingService).</p>
 * <p>Класс реализует механизмы планирования и оценки эффективности рекламных 
 * активностей компании. Он обеспечивает запуск новых кампаний, отслеживание 
 * фактических затрат и расчет окупаемости инвестиций (ROI).</p>
 * 
 * <p>Сервис является фундаментом для аналитики входящих лидов и позволяет 
 * маркетинговому отделу оптимизировать распределение бюджета.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Service
public class MarketingService {

    private final MarketingCampaignRepository campaignRepository;
    private final MarketingCampaignMapper campaignMapper;

    /**
     * Конструктор сервиса маркетинга.
     */
    public MarketingService(MarketingCampaignRepository campaignRepository, 
                            MarketingCampaignMapper campaignMapper) {
        this.campaignRepository = campaignRepository;
        this.campaignMapper = campaignMapper;
    }

    /**
     * <p>Создает и планирует новую маркетинговую кампанию.</p>
     * @param dto Данные кампании
     * @return MarketingCampaignDTO Созданная кампания
     */
    @Transactional
    public MarketingCampaignDTO createCampaign(MarketingCampaignDTO dto) {
        MarketingCampaign campaign = campaignMapper.toEntity(dto);
        
        campaign.setStatus("PLANNED");
        if (campaign.getStartDate() == null) {
            campaign.setStartDate(LocalDateTime.now().plusDays(7));
        }
        
        MarketingCampaign saved = campaignRepository.save(campaign);
        return campaignMapper.toDTO(saved);
    }

    /**
     * <p>Запускает кампанию в работу.</p>
     * @param id ID кампании
     */
    @Transactional
    public void activateCampaign(Long id) {
        MarketingCampaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Кампания не найдена"));
        
        campaign.setStatus("ACTIVE");
        campaign.setStartDate(LocalDateTime.now());
        campaignRepository.save(campaign);
    }

    /**
     * <p>Регистрирует фактические затраты на проведение кампании.</p>
     * @param id ID кампании
     * @param cost Сумма затрат
     */
    @Transactional
    public void recordActualCost(Long id, double cost) {
        MarketingCampaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Кампания не найдена"));
        
        campaign.setActualCost(campaign.getActualCost() + cost);
        campaignRepository.save(campaign);
    }

    /**
     * <p>Возвращает список всех активных на данный момент кампаний.</p>
     * @return List DTO
     */
    @Transactional(readOnly = true)
    public List<MarketingCampaignDTO> getActiveCampaigns() {
        return campaignMapper.toDTOList(campaignRepository.findByStatus("ACTIVE"));
    }

    /**
     * <p>Выполняет финальный расчет эффективности кампании перед закрытием.</p>
     * @param id ID кампании
     * @return double Итоговый ROI
     */
    @Transactional
    public double finalizeReporting(Long id) {
        MarketingCampaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Кампания не найдена"));
        
        campaign.setStatus("COMPLETED");
        campaign.setEndDate(LocalDateTime.now());
        
        MarketingCampaignDTO dto = campaignMapper.toDTO(campaign);
        campaignRepository.save(campaign);
        
        return dto.getRoi();
    }
}
