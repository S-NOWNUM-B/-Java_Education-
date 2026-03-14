package com.educational.finalproject.enterprise.controller;

import com.educational.finalproject.enterprise.dto.TaxReportDTO;
import com.educational.finalproject.enterprise.service.TaxService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>REST контроллер для налогового модуля (TaxController).</p>
 * <p>Класс предоставляет API для генерации, просмотра и подачи налоговой отчетности. 
 * Он интегрирован с сервисом налогов и обеспечивает надежное управление 
 * фискальными данными предприятия.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@RestController
@RequestMapping("/api/finance/taxes")
public class TaxController {

    private final TaxService taxService;

    /**
     * Конструктор контроллера налогов.
     */
    public TaxController(TaxService taxService) {
        this.taxService = taxService;
    }

    /**
     * Генерирует новый налоговый отчет.
     * @param periodId Название периода (например, "2024-Q1")
     * @param start Начало периода (ISO)
     * @param end Конец периода (ISO)
     * @return Ответ с сформированным отчетом
     */
    @PostMapping("/reports/generate")
    public ResponseEntity<TaxReportDTO> generateReport(
            @RequestParam String periodId,
            @RequestParam String start,
            @RequestParam String end) {
        LocalDateTime startTime = LocalDateTime.parse(start);
        LocalDateTime endTime = LocalDateTime.parse(end);
        return ResponseEntity.ok(taxService.generateQuarterlyReport(periodId, startTime, endTime));
    }

    /**
     * Возвращает список всех существующих отчетов.
     * @return Список DTO
     */
    @GetMapping("/reports")
    public ResponseEntity<List<TaxReportDTO>> getAllReports() {
        return ResponseEntity.ok(taxService.getAllReports());
    }

    /**
     * Выполняет техническую проверку отчета.
     * @param reportId ID отчета
     * @return Флаг валидности
     */
    @GetMapping("/reports/{reportId}/validate")
    public ResponseEntity<Boolean> validateReport(@PathVariable Long reportId) {
        return ResponseEntity.ok(taxService.validateReport(reportId));
    }

    /**
     * Подает отчет в налоговые органы.
     * @param reportId ID отчета
     * @param comments Комментарии бухгалтера
     * @return Статус 200 OK
     */
    @PostMapping("/reports/{reportId}/submit")
    public ResponseEntity<Void> submitReport(
            @PathVariable Long reportId, 
            @RequestParam String comments) {
        taxService.submitToAuthority(reportId, comments);
        return ResponseEntity.ok().build();
    }
}
