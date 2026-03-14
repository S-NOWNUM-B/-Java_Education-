package com.educational.finalproject.enterprise.controller;

import com.educational.finalproject.enterprise.dto.LeadDTO;
import com.educational.finalproject.enterprise.dto.OpportunityDTO;
import com.educational.finalproject.enterprise.service.LeadService;
import com.educational.finalproject.enterprise.service.OpportunityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>REST контроллер для управления продажами и лидами (CrmController).</p>
 * <p>Класс предоставляет API конечные точки для работы с воронкой продаж. 
 * Он объединяет функциональность управления первичными контактами (лидами) 
 * и активными сделками (возможностями), обеспечивая полный цикл конвертации клиента.</p>
 * 
 * <p>Все методы контроллера возвращают ResponseEntity для управления HTTP-заголовками 
 * и кодами состояний, что является стандартом для промышленных ERP-систем.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@RestController
@RequestMapping("/api/crm")
public class CrmController {

    private final LeadService leadService;
    private final OpportunityService opportunityService;

    /**
     * Конструктор контроллера.
     */
    public CrmController(LeadService leadService, OpportunityService opportunityService) {
        this.leadService = leadService;
        this.opportunityService = opportunityService;
    }

    /**
     * Создает новый лид в системе.
     * @param dto Данные лида
     * @return Ответ с созданным лидом
     */
    @PostMapping("/leads")
    public ResponseEntity<LeadDTO> createLead(@RequestBody LeadDTO dto) {
        return ResponseEntity.ok(leadService.createLead(dto));
    }

    /**
     * Обновляет статус лида (например, перевод в QUALIFIED).
     * @param id ID лида
     * @param status Новый статус
     * @return Ответ 200 OK
     */
    @PutMapping("/leads/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestParam String status) {
        leadService.updateLeadStatus(id, status);
        return ResponseEntity.ok().build();
    }

    /**
     * Возвращает список приоритетных лидов для работы менеджеров.
     * @return Список DTO
     */
    @GetMapping("/leads/priority")
    public ResponseEntity<List<LeadDTO>> getPriorityLeads() {
        return ResponseEntity.ok(leadService.getHighPriorityLeads());
    }

    /**
     * Конвертирует лид в сделку.
     * @param leadId ID лида
     * @param revenue Оценочная выручка
     * @return Созданная сделка
     */
    @PostMapping("/leads/{leadId}/convert")
    public ResponseEntity<OpportunityDTO> convertToOpportunity(
            @PathVariable Long leadId, 
            @RequestParam double revenue) {
        return ResponseEntity.ok(opportunityService.convertLeadToOpportunity(leadId, revenue));
    }

    /**
     * Продвигает сделку на следующий этап воронки.
     * @param id ID сделки
     * @param stage Новый этап
     * @return Ответ 200 OK
     */
    @PutMapping("/opportunities/{id}/stage")
    public ResponseEntity<Void> advanceOpportunityStage(@PathVariable Long id, @RequestParam String stage) {
        opportunityService.advanceStage(id, stage);
        return ResponseEntity.ok().build();
    }

    /**
     * Возвращает общий прогноз выручки по всей воронке.
     * @return Сумма взвешенного прогноза
     */
    @GetMapping("/opportunities/forecast")
    public ResponseEntity<Double> getPipelineForecast() {
        return ResponseEntity.ok(opportunityService.calculatePipelineForecast());
    }

    /**
     * Назначает ответственного менеджера за сделку.
     * @param id ID сделки
     * @param manager Имя сотрудника
     * @return Ответ 200 OK
     */
    @PutMapping("/opportunities/{id}/assign")
    public ResponseEntity<Void> assignOpportunity(@PathVariable Long id, @RequestParam String manager) {
        opportunityService.assignOwner(id, manager);
        return ResponseEntity.ok().build();
    }
}
