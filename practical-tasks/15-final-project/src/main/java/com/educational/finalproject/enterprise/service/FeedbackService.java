package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.dto.CustomerFeedbackDTO;
import com.educational.finalproject.enterprise.mapper.CustomerFeedbackMapper;
import com.educational.finalproject.enterprise.model.CustomerFeedback;
import com.educational.finalproject.enterprise.model.Lead;
import com.educational.finalproject.enterprise.repository.CustomerFeedbackRepository;
import com.educational.finalproject.enterprise.repository.LeadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>Сервис обработки обратной связи (FeedbackService).</p>
 * <p>Данный класс реализует механизмы сбора и управления качеством обслуживания. 
 * Он позволяет регистрировать отзывы, жалобы и предложения клиентов, 
 * назначать приоритеты для их обработки и отслеживать показатели CSAT/NPS.</p>
 * 
 * <p>Сервис помогает предприятию оперативно реагировать на критические инциденты 
 * и улучшать продукт на основе пожеланий пользователей.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Service
public class FeedbackService {

    private final CustomerFeedbackRepository feedbackRepository;
    private final LeadRepository leadRepository;
    private final CustomerFeedbackMapper feedbackMapper;

    /**
     * Конструктор сервиса отзывов.
     */
    public FeedbackService(CustomerFeedbackRepository feedbackRepository, 
                           LeadRepository leadRepository, 
                           CustomerFeedbackMapper feedbackMapper) {
        this.feedbackRepository = feedbackRepository;
        this.leadRepository = leadRepository;
        this.feedbackMapper = feedbackMapper;
    }

    /**
     * <p>Регистрирует новый отзыв в системе.</p>
     * @param contactId ID контактного лица (Lead)
     * @param dto Данные отзыва
     * @return CustomerFeedbackDTO
     */
    @Transactional
    public CustomerFeedbackDTO submitFeedback(Long contactId, CustomerFeedbackDTO dto) {
        Lead contact = leadRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Контакт не найден"));
        
        CustomerFeedback feedback = feedbackMapper.toEntity(dto);
        feedback.setContact(contact);
        feedback.setFeedbackDate(LocalDateTime.now());
        feedback.setStatus("NEW");
        
        // Автоматическое назначение приоритета при низкой оценке
        if (feedback.getStarRating() <= 2) {
            feedback.setPriority("HIGH");
        }
        
        CustomerFeedback saved = feedbackRepository.save(feedback);
        return feedbackMapper.toDTO(saved);
    }

    /**
     * <p>Публикует официальный ответ на отзыв.</p>
     * @param feedbackId ID отзыва
     * @param response Текст ответа
     */
    @Transactional
    public void respondToFeedback(Long feedbackId, String response) {
        CustomerFeedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new RuntimeException("Отзыв не найден"));
        
        feedback.setOfficialResponse(response);
        feedback.setStatus("RESOLVED");
        feedback.setResolutionDate(LocalDateTime.now());
        
        feedbackRepository.save(feedback);
        System.out.println("[Support] Official response sent for feedback ID: " + feedbackId);
    }

    /**
     * <p>Рассчитывает средний индекс NPS (Net Promoter Score) по всем отзывам.</p>
     * @return double Средний балл
     */
    @Transactional(readOnly = true)
    public double calculateAverageNps() {
        return feedbackRepository.findAll().stream()
                .mapToDouble(CustomerFeedback::getNpsScore)
                .average()
                .orElse(0.0);
    }

    /**
     * <p>Возвращает список критических отзывов, требующих немедленного внимания.</p>
     * @return List DTO
     */
    @Transactional(readOnly = true)
    public List<CustomerFeedbackDTO> getUrgentFeedback() {
        return feedbackMapper.toDTOList(
                feedbackRepository.findByStatus("NEW").stream()
                        .filter(f -> "HIGH".equals(f.getPriority()) || "URGENT".equals(f.getPriority()))
                        .toList()
        );
    }

    /**
     * <p>Изменяет приоритет ручным способом для специфических случаев.</p>
     * @param id ID
     * @param priority Новая категория срочности
     */
    @Transactional
    public void changePriority(Long id, String priority) {
        CustomerFeedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Отзыв не найден"));
        feedback.setPriority(priority);
        feedbackRepository.save(feedback);
    }
}
