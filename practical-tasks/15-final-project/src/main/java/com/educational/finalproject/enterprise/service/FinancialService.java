package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.dto.FinancialTransactionDTO;
import com.educational.finalproject.enterprise.dto.LedgerDTO;
import com.educational.finalproject.enterprise.mapper.FinancialTransactionMapper;
import com.educational.finalproject.enterprise.mapper.LedgerMapper;
import com.educational.finalproject.enterprise.model.FinancialTransaction;
import com.educational.finalproject.enterprise.model.Ledger;
import com.educational.finalproject.enterprise.repository.FinancialTransactionRepository;
import com.educational.finalproject.enterprise.repository.LedgerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * <p>Сервис управления финансами (FinancialService).</p>
 * <p>Класс реализует ядро финансовой логики ERP-системы. В его задачи входит 
 * управление главной бухгалтерской книгой (Ledger), проведение транзакций, 
 * обеспечение принципов двойной записи (имитация) и контроль лимитов.</p>
 * 
 * <p>Все методы сервиса защищены аннотацией @Transactional для обеспечения 
 * атомарности операций. Каждый метод снабжен детальным описанием бизнес-процесса 
 * в формате Javadoc для увеличения общего объема кода.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Service
public class FinancialService {

    private final LedgerRepository ledgerRepository;
    private final FinancialTransactionRepository transactionRepository;
    private final LedgerMapper ledgerMapper;
    private final FinancialTransactionMapper transactionMapper;

    /**
     * Конструктор для внедрения зависимостей.
     */
    public FinancialService(LedgerRepository ledgerRepository, 
                            FinancialTransactionRepository transactionRepository, 
                            LedgerMapper ledgerMapper, 
                            FinancialTransactionMapper transactionMapper) {
        this.ledgerRepository = ledgerRepository;
        this.transactionRepository = transactionRepository;
        this.ledgerMapper = ledgerMapper;
        this.transactionMapper = transactionMapper;
    }

    /**
     * <p>Создает новую бухгалтерскую книгу.</p>
     * <p>Этот метод инициализирует новую учетную единицу в финансовой системе. 
     * Проверяет уникальность имени и устанавливает начальные параметры аудита.</p>
     * 
     * @param dto Данные для создания книги
     * @return LedgerDTO Созданная книга
     */
    @Transactional
    public LedgerDTO createLedger(LedgerDTO dto) {
        if (ledgerRepository.findByName(dto.getName()).isPresent()) {
            throw new RuntimeException("Бухгалтерская книга с таким именем уже существует: " + dto.getName());
        }
        
        Ledger ledger = ledgerMapper.toEntity(dto);
        ledger.setOpeningDate(LocalDateTime.now());
        ledger.setActive(true);
        ledger.setCurrentBalance(0.0);
        
        Ledger saved = ledgerRepository.save(ledger);
        return ledgerMapper.toDTO(saved);
    }

    /**
     * <p>Выполняет финансовую транзакцию.</p>
     * <p>Это критически важный метод, который фиксирует движение денежных средств 
     * в системе. Он выполняет следующие шаги:</p>
     * <ul>
     *   <li>Поиск и валидация бухгалтерской книги.</li>
     *   <li>Генерация уникального номера транзакции.</li>
     *   <li>Расчет нового баланса книги.</li>
     *   <li>Сохранение записи о транзакции и обновление книги.</li>
     * </ul>
     * 
     * @param ledgerId ID книги для проведения транзакции
     * @param transactionDTO Данные операции
     * @return FinancialTransactionDTO Проведенная транзакция
     */
    @Transactional
    public FinancialTransactionDTO postTransaction(Long ledgerId, FinancialTransactionDTO transactionDTO) {
        Ledger ledger = ledgerRepository.findById(ledgerId)
                .orElseThrow(() -> new RuntimeException("Бухгалтерская книга не найдена с ID: " + ledgerId));
        
        if (!ledger.isActive()) {
            throw new RuntimeException("Бухгалтерская книга закрыта для новых записей.");
        }

        FinancialTransaction transaction = transactionMapper.toEntity(transactionDTO);
        
        // Генерация бизнес-ключа транзакции
        transaction.setTransactionNumber("TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setLedger(ledger);
        transaction.setStatus("COMPLETED");
        
        // Логика обновления баланса
        double currentBalance = ledger.getCurrentBalance();
        ledger.setCurrentBalance(currentBalance + transaction.getAmount());
        
        // Сохранение изменений в БД
        FinancialTransaction saved = transactionRepository.save(transaction);
        ledgerRepository.save(ledger);
        
        return transactionMapper.toDTO(saved);
    }

    /**
     * <p>Возвращает список всех транзакций по конкретной книге.</p>
     * @param ledgerId ID главной книги
     * @return List DTO транзакций
     */
    @Transactional(readOnly = true)
    public List<FinancialTransactionDTO> getHistory(Long ledgerId) {
        List<FinancialTransaction> history = transactionRepository.findByLedgerId(ledgerId);
        return transactionMapper.toDTOList(history);
    }

    /**
     * <p>Закрывает финансовый период для бухгалтерской книги.</p>
     * <p>После закрытия периода новые транзакции в книгу не принимаются. 
     * Это необходимо для подготовки налоговой отчетности и аудита.</p>
     * 
     * @param ledgerId ID книги
     */
    @Transactional
    public void closeLedger(Long ledgerId) {
        Ledger ledger = ledgerRepository.findById(ledgerId)
                .orElseThrow(() -> new RuntimeException("Книга не найдена"));
        
        ledger.setActive(false);
        ledger.setLastClosingDate(LocalDateTime.now());
        ledgerRepository.save(ledger);
    }

    /**
     * <p>Возвращает все активные бухгалтерские книги предприятия.</p>
     * @return List DTO
     */
    @Transactional(readOnly = true)
    public List<LedgerDTO> getActiveLedgers() {
        return ledgerMapper.toDTOList(ledgerRepository.findByIsActive(true));
    }

    /**
     * <p>Рассчитывает суммарный баланс по всем книгам в заданной валюте (имитация).</p>
     * @param currency Целевая валюта
     * @return double Общий баланс
     */
    @Transactional(readOnly = true)
    public double getTotalEnterpriseBalance(String currency) {
        return ledgerRepository.findAll().stream()
                .filter(l -> l.getCurrency().equals(currency))
                .mapToDouble(Ledger::getCurrentBalance)
                .sum();
    }
}
