package com.educational.finalproject.enterprise.controller;

import com.educational.finalproject.enterprise.dto.CustomerFeedbackDTO;
import com.educational.finalproject.enterprise.dto.MarketingCampaignDTO;
import com.educational.finalproject.enterprise.service.FeedbackService;
import com.educational.finalproject.enterprise.service.MarketingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>REST контроллер маркетинга и поддержки (MarketingSupportController).</p>
 * <p>Класс объединяет точки доступа для управления маркетинговыми кампаниями 
 * и сбора обратной связи от клиентов. Такое объединение позволяет 
 * фронтенд-приложениям CRM получать данные об удовлетворенности клиентов 
 * в контексте рекламных активностей.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@RestController
@RequestMapping("/api/crm/marketing")
public class MarketingSupportController {

    private final MarketingService marketingService;
    private final FeedbackService feedbackService;

    /**
     * Конструктор контроллера.
     */
    public MarketingSupportController(MarketingService marketingService, 
                                     FeedbackService feedbackService) {
        this.marketingService = marketingService;
        this.feedbackService = feedbackService;
    }

    /**
     * Создает новую маркетинговую кампанию.
     * @param dto Данные кампании
     * @return Ответ с созданной кампанией
     */
    @PostMapping("/campaigns")
    public ResponseEntity<MarketingCampaignDTO> createCampaign(@RequestBody MarketingCampaignDTO dto) {
        return ResponseEntity.ok(marketingService.createCampaign(dto));
    }

    /**
     * Активирует запланированную кампанию.
     * @param id ID кампании
     * @return Ответ 200 OK
     */
    @PostMapping("/campaigns/{id}/activate")
    public ResponseEntity<Void> activateCampaign(@PathVariable Long id) {
        marketingService.activateCampaign(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Возвращает список всех активных маркетинговых кампаний.
     * @return Список DTO
     */
    @GetMapping("/campaigns/active")
    public ResponseEntity<List<MarketingCampaignDTO>> getActiveCampaigns() {
        return ResponseEntity.ok(marketingService.getActiveCampaigns());
    }

    /**
     * Регистрирует новый отзыв клиента.
     * @param contactId ID контакта (Lead)
     * @param dto Данные отзыва
     * @return Флаг успешного сохранения
     */
    @PostMapping("/feedback/{contactId}")
    public ResponseEntity<CustomerFeedbackDTO> submitFeedback(
            @PathVariable Long contactId, 
            @RequestBody CustomerFeedbackDTO dto) {
        return ResponseEntity.ok(feedbackService.submitFeedback(contactId, dto));
    }

    /**
     * Публикует ответ на претензию клиента.
     * @param id ID отзыва
     * @param response Текст ответа
     * @return Ответ 200 OK
     */
    @PostMapping("/feedback/{id}/respond")
    public ResponseEntity<Void> respondToFeedback(
            @PathVariable Long id, 
            @RequestParam String response) {
        feedbackService.respondToFeedback(id, response);
        return ResponseEntity.ok().build();
    }

    /**
     * Возвращает текущий средний показатель NPS.
     * @return Вещественное число
     */
    @GetMapping("/feedback/nps")
    public ResponseEntity<Double> getCurrentNps() {
        return ResponseEntity.ok(feedbackService.calculateAverageNps());
    }
}
