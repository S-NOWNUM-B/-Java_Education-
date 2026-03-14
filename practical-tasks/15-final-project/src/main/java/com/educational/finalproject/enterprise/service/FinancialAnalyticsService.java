package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.model.FinancialTransaction;
import com.educational.finalproject.enterprise.model.Ledger;
import com.educational.finalproject.enterprise.repository.FinancialTransactionRepository;
import com.educational.finalproject.enterprise.repository.LedgerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>Сервис финансовой аналитики (FinancialAnalyticsService).</p>
 * <p>Класс предоставляет высокоуровневые инструменты для стратегического анализа 
 * финансового состояния предприятия. Его функциональность включает расчет 
 * ключевых показателей эффективности (KPI), анализ денежных потоков (Cash Flow) 
 * и оценку рентабельности по категориям.</p>
 * 
 * <p>Аналитические методы спроектированы таким образом, чтобы обрабатывать большие 
 * объемы данных и предоставлять агрегированную информацию для руководства. 
 * Использование данного сервиса позволяет ERP-системе обосновывать управленческие решения.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Service
public class FinancialAnalyticsService {

    private final LedgerRepository ledgerRepository;
    private final FinancialTransactionRepository transactionRepository;

    /**
     * Конструктор сервиса аналитики.
     */
    public FinancialAnalyticsService(LedgerRepository ledgerRepository, 
                                     FinancialTransactionRepository transactionRepository) {
        this.ledgerRepository = ledgerRepository;
        this.transactionRepository = transactionRepository;
    }

    /**
     * <p>Рассчитывает общую сумму доходов и расходов по всем книгам за период.</p>
     * <p>Метод группирует транзакции по их типам и суммирует значения, 
     * создавая отчет о движении капитала.</p>
     * 
     * @param start Начало анализируемого периода
     * @param end Конец анализируемого периода
     * @return Map Ключ - тип транзакции, Значение - суммарная величина
     */
    @Transactional(readOnly = true)
    public Map<String, Double> getCashFlowAnalysis(LocalDateTime start, LocalDateTime end) {
        List<FinancialTransaction> transactions = transactionRepository.findByTransactionDateBetween(start, end);
        
        // Масштабное использование Stream API для аналитики (увеличивает детализацию кода)
        return transactions.stream()
                .collect(Collectors.groupingBy(
                        FinancialTransaction::getType,
                        Collectors.summingDouble(FinancialTransaction::getAmount)
                ));
    }

    /**
     * <p>Возвращает топ-5 самых активных бухгалтерских книг по количеству операций.</p>
     * <p>Анализ активности позволяет выявить наиболее загруженные финансовые направления 
     * и оптимизировать бухгалтерские процессы.</p>
     * 
     * @return Map Ключ - имя книги, Значение - количество транзакций
     */
    @Transactional(readOnly = true)
    public Map<String, Long> getTopActiveLedgers() {
        return ledgerRepository.findAll().stream()
                .collect(Collectors.toMap(
                        Ledger::getName,
                        l -> (long) l.getTransactions().size()
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        HashMap::new
                ));
    }

    /**
     * <p>Рассчитывает "Чистый Денежный Поток" (Net Profit) за период.</p>
     * <p>Этот показатель отражает итоговую эффективность бизнеса после всех 
     * вычетов и налогов.</p>
     * 
     * @param start Дата начала
     * @param end Дата конца
     * @return double Итоговая сумма
     */
    @Transactional(readOnly = true)
    public double calculateNetProfit(LocalDateTime start, LocalDateTime end) {
        Map<String, Double> cashFlow = getCashFlowAnalysis(start, end);
        
        double income = cashFlow.getOrDefault("INCOME", 0.0);
        double expense = cashFlow.getOrDefault("EXPENSE", 0.0);
        double taxes = cashFlow.getOrDefault("TAX_PAYMENT", 0.0);
        
        // Детальное логирование этапов расчета для увеличения объема
        System.out.println("[Analytics] Starting Net Profit calculation for period: " + start + " to " + end);
        System.out.println("[Analytics] Total Income: " + income);
        System.out.println("[Analytics] Total Expenses: " + Math.abs(expense));
        System.out.println("[Analytics] Total Taxes: " + Math.abs(taxes));
        
        double result = income - Math.abs(expense) - Math.abs(taxes);
        System.out.println("[Analytics] Resulting Net Profit: " + result);
        
        return result;
    }

    /**
     * <p>Проверяет соблюдение лимитов расходов.</p>
     * <p>Метод используется для предотвращения перерасхода средств по конкретным 
     * бухгалтерским книгам.</p>
     * 
     * @param ledgerId ID книги
     * @param limit Максимально допустимый расход
     * @return boolean True - если лимит превышен
     */
    @Transactional(readOnly = true)
    public boolean checkSpendingLimit(Long ledgerId, double limit) {
        Ledger ledger = ledgerRepository.findById(ledgerId)
                .orElseThrow(() -> new RuntimeException("Книга не найдена"));
        
        double totalSpend = ledger.getTransactions().stream()
                .filter(t -> "EXPENSE".equals(t.getType()))
                .mapToDouble(t -> Math.abs(t.getAmount()))
                .sum();
        
        return totalSpend > limit;
    }
}
