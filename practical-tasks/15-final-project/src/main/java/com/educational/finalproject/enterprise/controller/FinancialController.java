package com.educational.finalproject.enterprise.controller;

import com.educational.finalproject.enterprise.dto.FinancialTransactionDTO;
import com.educational.finalproject.enterprise.dto.LedgerDTO;
import com.educational.finalproject.enterprise.service.FinancialAnalyticsService;
import com.educational.finalproject.enterprise.service.FinancialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>REST контроллер для управления финансовыми операциями (FinancialController).</p>
 * <p>Класс предоставляет API конечные точки для взаимодействия с подсистемой главной книги. 
 * Он обеспечивает создание новых книг, проведение транзакций и получение аналитической отчетности 
 * через HTTP протокол.</p>
 * 
 * <p>Методы контроллера используют ResponseEntity для управления статус-кодами ответов 
 * и обеспечивают безопасность данных путем работы исключительно через объекты DTO.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@RestController
@RequestMapping("/api/finance")
public class FinancialController {

    private final FinancialService financialService;
    private final FinancialAnalyticsService analyticsService;

    /**
     * Конструктор контроллера с внедрением зависимостей.
     */
    public FinancialController(FinancialService financialService, 
                               FinancialAnalyticsService analyticsService) {
        this.financialService = financialService;
        this.analyticsService = analyticsService;
    }

    /**
     * Создает новую главную книгу.
     * @param dto Данные книги
     * @return Ответ с созданной книгой
     */
    @PostMapping("/ledgers")
    public ResponseEntity<LedgerDTO> createLedger(@RequestBody LedgerDTO dto) {
        return ResponseEntity.ok(financialService.createLedger(dto));
    }

    /**
     * Возвращает список всех активных бухгалтерских книг.
     * @return Список DTO
     */
    @GetMapping("/ledgers")
    public ResponseEntity<List<LedgerDTO>> getActiveLedgers() {
        return ResponseEntity.ok(financialService.getActiveLedgers());
    }

    /**
     * Проводит новую финансовую транзакцию в указанной книге.
     * @param ledgerId ID книги
     * @param transactionDTO Данные операции
     * @return Проведенная транзакция
     */
    @PostMapping("/ledgers/{ledgerId}/transactions")
    public ResponseEntity<FinancialTransactionDTO> postTransaction(
            @PathVariable Long ledgerId, 
            @RequestBody FinancialTransactionDTO transactionDTO) {
        return ResponseEntity.ok(financialService.postTransaction(ledgerId, transactionDTO));
    }

    /**
     * Возвращает историю проводок по книге.
     * @param ledgerId ID книги
     * @return Список транзакций
     */
    @GetMapping("/ledgers/{ledgerId}/history")
    public ResponseEntity<List<FinancialTransactionDTO>> getHistory(@PathVariable Long ledgerId) {
        return ResponseEntity.ok(financialService.getHistory(ledgerId));
    }

    /**
     * Закрывает финансовый период для книги.
     * @param ledgerId ID книги
     * @return Пустой ответ 200 OK
     */
    @PostMapping("/ledgers/{ledgerId}/close")
    public ResponseEntity<Void> closeLedger(@PathVariable Long ledgerId) {
        financialService.closeLedger(ledgerId);
        return ResponseEntity.ok().build();
    }

    /**
     * Возвращает аналитический отчет о движении денежных средств.
     * @param start Дата начала (ISO формат)
     * @param end Дата конца (ISO формат)
     * @return Map с данными потока
     */
    @GetMapping("/analytics/cash-flow")
    public ResponseEntity<Map<String, Double>> getCashFlow(
            @RequestParam String start, 
            @RequestParam String end) {
        LocalDateTime startTime = LocalDateTime.parse(start);
        LocalDateTime endTime = LocalDateTime.parse(end);
        return ResponseEntity.ok(analyticsService.getCashFlowAnalysis(startTime, endTime));
    }

    /**
     * Возвращает итоговую чистую прибыль за период.
     * @param start Дата начала
     * @param end Дата конца
     * @return Сумма прибыли
     */
    @GetMapping("/analytics/net-profit")
    public ResponseEntity<Double> getNetProfit(
            @RequestParam String start, 
            @RequestParam String end) {
        LocalDateTime startTime = LocalDateTime.parse(start);
        LocalDateTime endTime = LocalDateTime.parse(end);
        return ResponseEntity.ok(analyticsService.calculateNetProfit(startTime, endTime));
    }
}
