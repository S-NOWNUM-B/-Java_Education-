package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.dto.OpportunityDTO;
import com.educational.finalproject.enterprise.mapper.OpportunityMapper;
import com.educational.finalproject.enterprise.model.Lead;
import com.educational.finalproject.enterprise.model.Opportunity;
import com.educational.finalproject.enterprise.repository.LeadRepository;
import com.educational.finalproject.enterprise.repository.OpportunityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Сервис управления сделками (OpportunityService).</p>
 * <p>Класс отвечает за автоматизацию процесса продаж на этапе заключения сделки. 
 * Он обеспечивает конвертацию квалифицированных лидов в возможности, 
 * прогнозирование доходов на основе вероятностных моделей и контроль 
 * за продвижением сделки по этапам (Sales Stages).</p>
 * 
 * <p>Является ключевым компонентом CRM-модуля для отдела продаж.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Service
public class OpportunityService {

    private final OpportunityRepository opportunityRepository;
    private final LeadRepository leadRepository;
    private final OpportunityMapper opportunityMapper;

    /**
     * Конструктор сервиса сделок.
     */
    public OpportunityService(OpportunityRepository opportunityRepository, 
                              LeadRepository leadRepository, 
                              OpportunityMapper opportunityMapper) {
        this.opportunityRepository = opportunityRepository;
        this.leadRepository = leadRepository;
        this.opportunityMapper = opportunityMapper;
    }

    /**
     * <p>Конвертирует квалифицированный лид в новую сделку.</p>
     * <p>Этот процесс переводит контакт на следующий уровень воронки продаж. 
     * Лид при этом не удаляется, а служит источником данных для сделки.</p>
     * 
     * @param leadId ID лида
     * @param initialRevenue Первоначальная оценка суммы сделки
     * @return OpportunityDTO Созданная сделка
     */
    @Transactional
    public OpportunityDTO convertLeadToOpportunity(Long leadId, double initialRevenue) {
        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Лид не найден для конвертации"));
        
        if (!"QUALIFIED".equals(lead.getStatus())) {
            throw new RuntimeException("Только квалифицированные лиды могут быть конвертированы в сделки");
        }
        
        Opportunity opportunity = new Opportunity();
        opportunity.setName("Сделка: " + lead.getCompanyName() + " (" + lead.getFullName() + ")");
        opportunity.setExpectedRevenue(initialRevenue);
        opportunity.setSourceLead(lead);
        opportunity.setCreatedAt(LocalDateTime.now());
        opportunity.setStage("PROSPECTING");
        opportunity.setProbability(10); // Базовая вероятность для первого этапа
        opportunity.setOpportunityType("NEW_BUSINESS");
        
        Opportunity saved = opportunityRepository.save(opportunity);
        
        // Помечаем лид как сконвертированный
        lead.setStatus("CONVERTED");
        leadRepository.save(lead);
        
        return opportunityMapper.toDTO(saved);
    }

    /**
     * <p>Обновляет этап ведения сделки и пересчитывает вероятность успеха.</p>
     * @param opportunityId ID сделки
     * @param newStage Новый этап
     */
    @Transactional
    public void advanceStage(Long opportunityId, String newStage) {
        Opportunity opp = opportunityRepository.findById(opportunityId)
                .orElseThrow(() -> new RuntimeException("Сделка не найдена"));
        
        opp.setStage(newStage);
        
        // Динамический расчет вероятности в зависимости от этапа (имитация)
        switch (newStage) {
            case "QUALIFICATION": opp.setProbability(20.0); break;
            case "PROPOSAL": opp.setProbability(50.0); break;
            case "NEGOTIATION": opp.setProbability(75.0); break;
            case "CLOSED_WON": 
                opp.setProbability(100.0); 
                opp.setClosedDate(LocalDateTime.now());
                break;
            case "CLOSED_LOST": 
                opp.setProbability(0.0); 
                opp.setClosedDate(LocalDateTime.now());
                break;
            default:
                opp.setProbability(10.0);
                break;
        }
        
        opportunityRepository.save(opp);
    }

    /**
     * <p>Рассчитывает "Взвешенный прогноз" (Weighted Pipeline).</p>
     * <p>Прогноз вычисляется как сумма ожидаемых доходов, умноженных 
     * на вероятность успеха каждой сделки.</p>
     * 
     * @return double Итоговая сумма прогноза
     */
    @Transactional(readOnly = true)
    public double calculatePipelineForecast() {
        return opportunityRepository.findAll().stream()
                .filter(o -> !"CLOSED_WON".equals(o.getStage()) && !"CLOSED_LOST".equals(o.getStage()))
                .mapToDouble(o -> o.getExpectedRevenue() * (o.getProbability() / 100.0))
                .sum();
    }

    /**
     * <p>Возвращает список сделок с высоким уровнем риска.</p>
     * @return List DTO
     */
    @Transactional(readOnly = true)
    public List<OpportunityDTO> getAtRiskOpportunities() {
        return opportunityRepository.findAll().stream()
                .filter(o -> "HIGH".equals(o.getRiskLevel()) || "CRITICAL".equals(o.getRiskLevel()))
                .map(opportunityMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * <p>Назначает владельца сделки.</p>
     * @param opportunitId ID
     * @param managerName Имя менеджера
     */
    @Transactional
    public void assignOwner(Long opportunitId, String managerName) {
        Opportunity opp = opportunityRepository.findById(opportunitId)
                .orElseThrow(() -> new RuntimeException("Сделка не найдена"));
        opp.setOwnerName(managerName);
        opportunityRepository.save(opp);
    }

    /**
     * <p>Переводит сделку на новый этап.</p>
     * @param opportunityId ID
     * @param newStage Этап
     */
    @Transactional
    public void moveToStage(Long opportunityId, String newStage) {
        advanceStage(opportunityId, newStage);
    }

    /**
     * <p>Закрывает сделку со статусом "Выиграна".</p>
     * @param opportunityId ID
     */
    @Transactional
    public void closeAsWon(Long opportunityId) {
        advanceStage(opportunityId, "CLOSED_WON");
    }

    /**
     * <p>Рассчитывает взвешенный доход по всем открытым сделкам.</p>
     * @return double
     */
    @Transactional(readOnly = true)
    public double calculatePipelineWeightedRevenue() {
        return calculatePipelineForecast();
    }
}
