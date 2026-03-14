package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.dto.InteractionLogDTO;
import com.educational.finalproject.enterprise.mapper.InteractionLogMapper;
import com.educational.finalproject.enterprise.model.InteractionLog;
import com.educational.finalproject.enterprise.model.Lead;
import com.educational.finalproject.enterprise.model.Opportunity;
import com.educational.finalproject.enterprise.repository.InteractionLogRepository;
import com.educational.finalproject.enterprise.repository.LeadRepository;
import com.educational.finalproject.enterprise.repository.OpportunityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>Сервис регистрации взаимодействий (InteractionService).</p>
 * <p>Класс обеспечивает ведение хронологии контактов с клиентами. 
 * Он позволяет фиксировать результаты встреч, звонков и переписки, 
 * связывая их с конкретными лидами или сделками (Opportunities).</p>
 * 
 * <p>Данный сервис является "черным ящиком" истории отношений с клиентом, 
 * обеспечивая непрерывность процесса продаж при смене ответственных менеджеров.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Service
public class InteractionService {

    private final InteractionLogRepository interactionRepository;
    private final LeadRepository leadRepository;
    private final OpportunityRepository opportunityRepository;
    private final InteractionLogMapper interactionMapper;

    /**
     * Конструктор сервиса взаимодействий.
     */
    public InteractionService(InteractionLogRepository interactionRepository, 
                              LeadRepository leadRepository, 
                              OpportunityRepository opportunityRepository, 
                              InteractionLogMapper interactionMapper) {
        this.interactionRepository = interactionRepository;
        this.leadRepository = leadRepository;
        this.opportunityRepository = opportunityRepository;
        this.interactionMapper = interactionMapper;
    }

    /**
     * <p>Логирует новое взаимодействие с лидом.</p>
     * @param leadId ID лида
     * @param dto Данные контакта
     * @return InteractionLogDTO Сохраненная запись
     */
    @Transactional
    public InteractionLogDTO logInteractionWithLead(Long leadId, InteractionLogDTO dto) {
        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Лид не найден"));
        
        InteractionLog log = interactionMapper.toEntity(dto);
        log.setLead(lead);
        log.setInteractionDate(LocalDateTime.now());
        
        // Обновляем дату последнего контакта у лида
        lead.setLastContactDate(LocalDateTime.now());
        leadRepository.save(lead);
        
        InteractionLog saved = interactionRepository.save(log);
        return interactionMapper.toDTO(saved);
    }

    /**
     * <p>Логирует взаимодействие в рамках конкретной сделки.</p>
     * @param opportunityId ID сделки
     * @param dto Данные контакта
     * @return InteractionLogDTO
     */
    @Transactional
    public InteractionLogDTO logInteractionWithOpportunity(Long opportunityId, InteractionLogDTO dto) {
        Opportunity opp = opportunityRepository.findById(opportunityId)
                .orElseThrow(() -> new RuntimeException("Сделка не найдена"));
        
        InteractionLog log = interactionMapper.toEntity(dto);
        log.setOpportunity(opp);
        log.setInteractionDate(LocalDateTime.now());
        
        // Привязываем лид из сделки, если он есть
        if (opp.getSourceLead() != null) {
            log.setLead(opp.getSourceLead());
        }
        
        InteractionLog saved = interactionRepository.save(log);
        return interactionMapper.toDTO(saved);
    }

    /**
     * <p>Возвращает полную историю взаимодействий по конкретному лиду.</p>
     * @param leadId ID лида
     * @return List DTO
     */
    @Transactional(readOnly = true)
    public List<InteractionLogDTO> getHistoryByLead(Long leadId) {
        return interactionMapper.toDTOList(interactionRepository.findByLeadId(leadId));
    }

    /**
     * <p>Планирует следующий шаг взаимодействия.</p>
     * @param logId ID текущего лога
     * @param deadline Дата следующего контакта
     */
    @Transactional
    public void scheduleNextStep(Long logId, LocalDateTime deadline) {
        InteractionLog log = interactionRepository.findById(logId)
                .orElseThrow(() -> new RuntimeException("Запись не найдена"));
        
        log.setNextStepDeadline(deadline);
        log.setOutcome("FOLLOW_UP_REQUIRED");
        interactionRepository.save(log);
    }
}
