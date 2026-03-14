package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.dto.LeadDTO;
import com.educational.finalproject.enterprise.mapper.LeadMapper;
import com.educational.finalproject.enterprise.model.Lead;
import com.educational.finalproject.enterprise.repository.LeadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Сервис управления лидами (LeadService).</p>
 * <p>Класс реализует комплексную логику обработки входящего потока клиентов. 
 * Он обеспечивает квалификацию лидов, автоматическое назначение скоринга 
 * на основе предоставленных данных и управление статусами жизненного цикла лида.</p>
 * 
 * <p>Использование этого сервиса является критическим этапом в процессе 
 * автоматизации продаж. Система анализирует индустрию, должность и источник 
 * лида для формирования приоритетной очереди для менеджеров.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Service
public class LeadService {

    private final LeadRepository leadRepository;
    private final LeadMapper leadMapper;

    /**
     * Конструктор сервиса лидов.
     */
    public LeadService(LeadRepository leadRepository, LeadMapper leadMapper) {
        this.leadRepository = leadRepository;
        this.leadMapper = leadMapper;
    }

    /**
     * <p>Регистрирует новый лид в системе.</p>
     * <p>Метод выполняет первичную валидацию и расчет базового скоринга. 
     * Если лид пришел из корпоративного источника, его начальный балл повышается.</p>
     * 
     * @param dto Данные лида
     * @return LeadDTO Созданный лид
     */
    @Transactional
    public LeadDTO createLead(LeadDTO dto) {
        Lead lead = leadMapper.toEntity(dto);
        
        // Автоматическая квалификация (имитация)
        int initialScore = 50;
        if (lead.getEmail() != null && (lead.getEmail().endsWith(".edu") || lead.getEmail().endsWith(".gov"))) {
            initialScore += 20;
        }
        if ("Website".equals(lead.getSource())) {
            initialScore += 10;
        }
        
        lead.setScore(initialScore);
        lead.setCreatedAt(LocalDateTime.now());
        lead.setStatus("NEW");
        
        Lead saved = leadRepository.save(lead);
        return leadMapper.toDTO(saved);
    }

    /**
     * <p>Выполняет обновление статуса лида после первичного контакта.</p>
     * @param leadId ID лида
     * @param newStatus Новый статус (QUALIFIED, DISQUALIFIED и др.)
     */
    @Transactional
    public void updateLeadStatus(Long leadId, String newStatus) {
        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Лид не найден: " + leadId));
        
        lead.setStatus(newStatus);
        lead.setLastContactDate(LocalDateTime.now());
        
        // При переходе в QUALIFIED ожидается конвертация в Opportunity
        if ("QUALIFIED".equals(newStatus)) {
            lead.setScore(Math.min(100, lead.getScore() + 30));
        }
        
        leadRepository.save(lead);
        System.out.println("[CRM] Lead status updated to: " + newStatus + " for " + lead.getFullName());
    }

    /**
     * <p>Возвращает список наиболее перспективных лидов для обзвона.</p>
     * <p>Сортировка выполняется по убыванию скоринга.</p>
     * @return List DTO
     */
    @Transactional(readOnly = true)
    public List<LeadDTO> getHighPriorityLeads() {
        return leadRepository.findAll().stream()
                .filter(l -> l.getScore() >= 80)
                .sorted((l1, l2) -> Integer.compare(l2.getScore(), l1.getScore()))
                .map(leadMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * <p>Синхронизирует данные лида с внешними источниками (имитация).</p>
     * @param leadId ID лида
     */
    @Transactional
    public void enrichLeadData(Long leadId) {
        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Лид не найден"));
        
        // Имитация обогащения данных из LinkedIn API
        if (lead.getCompanyName() != null && lead.getIndustry() == null) {
            lead.setIndustry("TECHNOLOGY_SECTOR");
            lead.setNotes(lead.getNotes() + " [Enriched] Data synced from corporate directory.");
            leadRepository.save(lead);
        }
    }

    /**
     * <p>Возвращает всех лидов из конкретного региона.</p>
     * @param region Название региона
     * @return List DTO
     */
    @Transactional(readOnly = true)
    public List<LeadDTO> getLeadsByRegion(String region) {
        return leadRepository.findAll().stream()
                .filter(l -> region.equalsIgnoreCase(l.getRegion()))
                .map(leadMapper::toDTO)
                .collect(Collectors.toList());
    }
}
