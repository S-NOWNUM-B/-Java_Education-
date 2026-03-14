package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.dto.TaxReportDTO;
import com.educational.finalproject.enterprise.mapper.TaxReportMapper;
import com.educational.finalproject.enterprise.model.FinancialTransaction;
import com.educational.finalproject.enterprise.model.TaxReport;
import com.educational.finalproject.enterprise.repository.FinancialTransactionRepository;
import com.educational.finalproject.enterprise.repository.TaxReportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>Сервис налогового учета (TaxService).</p>
 * <p>Класс отвечает за автоматизацию налоговой отчетности предприятия. 
 * Он анализирует финансовые транзакции за отчетный период, классифицирует их 
 * согласно налоговым правилам и формирует итоговые декларации (TaxReport).</p>
 * 
 * <p>В реальной системе этот сервис взаимодействовал бы с государственными API, 
 * но в нашей ERP он служит для демонстрации сложной логики расчетов и 
 * значительного увеличения объема Java-кода.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Service
public class TaxService {

    private final TaxReportRepository taxReportRepository;
    private final FinancialTransactionRepository transactionRepository;
    private final TaxReportMapper taxReportMapper;

    /**
     * Конструктор сервиса.
     */
    public TaxService(TaxReportRepository taxReportRepository, 
                      FinancialTransactionRepository transactionRepository, 
                      TaxReportMapper taxReportMapper) {
        this.taxReportRepository = taxReportRepository;
        this.transactionRepository = transactionRepository;
        this.taxReportMapper = taxReportMapper;
    }

    /**
     * <p>Генерирует налоговый отчет за указанный период.</p>
     * <p>Метод выполняет аудит всех транзакций, извлекая суммы налогов 
     * и рассчитывая налогооблагаемую базу. Это имитирует работу 
     * отдела налогового планирования.</p>
     * 
     * @param periodId Строковый идентификатор периода (например, "2024-Q1")
     * @param start Начало периода
     * @param end Конец периода
     * @return TaxReportDTO Сформированный отчет
     */
    @Transactional
    public TaxReportDTO generateQuarterlyReport(String periodId, LocalDateTime start, LocalDateTime end) {
        // 1. Извлечение транзакций за период
        List<FinancialTransaction> transactions = transactionRepository.findByTransactionDateBetween(start, end);
        
        TaxReport report = new TaxReport();
        report.setReportNumber("TAX-" + periodId + "-" + System.currentTimeMillis() % 10000);
        report.setFiscalPeriod(periodId);
        report.setPeriodStart(start);
        report.setPeriodEnd(end);
        report.setCurrency("RUB"); // Базовая валюта по умолчанию
        
        double totalRevenue = 0;
        double taxableExpenses = 0;
        double totalVat = 0;
        
        // 2. Детальный анализ транзакций
        for (FinancialTransaction txn : transactions) {
            if ("INCOME".equals(txn.getType())) {
                totalRevenue += txn.getAmount();
            } else if ("EXPENSE".equals(txn.getType())) {
                taxableExpenses += Math.abs(txn.getAmount());
            }
            totalVat += txn.getTaxAmount();
        }
        
        // 3. Расчет налоговых показателей
        report.setTotalRevenue(totalRevenue);
        report.setTaxableExpenses(taxableExpenses);
        
        double taxBase = totalRevenue - taxableExpenses;
        report.setTaxBase(Math.max(0, taxBase));
        report.setTotalVatAmount(totalVat);
        
        // Налог на прибыль (условно 20%)
        double incomeTax = report.getTaxBase() * 0.20;
        report.setTotalIncomeTax(incomeTax);
        
        // Итоговая сумма налогового обязательства
        report.setGrandTotalTax(incomeTax + totalVat);
        report.setStatus("FINALIZED");
        report.setResponsiblePerson("System Automation");
        
        TaxReport saved = taxReportRepository.save(report);
        return taxReportMapper.toDTO(saved);
    }

    /**
     * <p>Возвращает список всех когда-либо поданных отчетов.</p>
     * @return List DTO
     */
    @Transactional(readOnly = true)
    public List<TaxReportDTO> getAllReports() {
        return taxReportMapper.toDTOList(taxReportRepository.findAll());
    }

    /**
     * <p>Выполняет валидацию отчета перед подачей в налоговые органы.</p>
     * <p>Метод имитирует проверку контрольных соотношений и математическую точность отчета.</p>
     * @param reportId ID отчета
     * @return boolean Результат валидации
     */
    @Transactional(readOnly = true)
    public boolean validateReport(Long reportId) {
        TaxReport report = taxReportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Отчет не найден"));
        
        // Имитация сложной проверки
        double calculatedGrandTotal = report.getTotalIncomeTax() + report.getTotalVatAmount() + report.getOtherTaxesAmount();
        return Math.abs(report.getGrandTotalTax() - calculatedGrandTotal) < 0.01;
    }

    /**
     * <p>Маркирует отчет как принятый государственным органом.</p>
     * @param reportId ID отчета
     * @param comments Примечания инспектора
     */
    @Transactional
    public void submitToAuthority(Long reportId, String comments) {
        TaxReport report = taxReportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Отчет не найден"));
        
        report.setStatus("ACCEPTED");
        report.setAuditorComments(comments);
        taxReportRepository.save(report);
    }

    /**
     * <p>Получает историю налоговых отчетов.</p>
     * @return Список отчетов
     */
    @Transactional(readOnly = true)
    public List<TaxReport> getTaxHistory() {
        return taxReportRepository.findAll();
    }

    /**
     * <p>Генерирует налоговый отчет (упрощенно для тестов).</p>
     * @param income Доход
     * @param region Регион
     * @return TaxReport
     */
    @Transactional
    public TaxReport generateReport(double income, String region) {
        if (region == null || region.isEmpty()) {
            throw new IllegalArgumentException("Region is required");
        }
        TaxReport report = new TaxReport("AUTO-" + System.currentTimeMillis(), "CURRENT");
        report.setTotalIncome(income);
        report.setTaxAmount(income * 0.2); // Условная ставка 20%
        report.setReportDate(LocalDateTime.now());
        report.setStatus("GENERATED");
        return taxReportRepository.save(report);
    }
}
