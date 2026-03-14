package com.educational.finalproject.enterprise.repository;

import com.educational.finalproject.enterprise.model.TaxReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * <p>Репозиторий для налоговых отчетов (TaxReport).</p>
 * <p>Обеспечивает доступ к архиву налоговых деклараций и отчетности. 
 * Позволяет фильтровать отчеты по статусам соответствия и фискальным периодам.</p>
 * 
 * @author Antigravity
 */
@Repository
public interface TaxReportRepository extends JpaRepository<TaxReport, Long> {

    /**
     * Поиск по уникальному номеру отчета.
     * @param reportNumber Номер декларации
     * @return Optional
     */
    Optional<TaxReport> findByReportNumber(String reportNumber);

    /**
     * Поиск отчетов за конкретный фискальный период (квартал/год).
     * @param fiscalPeriod Строка периода
     * @return Список отчетов
     */
    List<TaxReport> findByFiscalPeriod(String fiscalPeriod);

    /**
     * Поиск отчетов по статусу (ACCEPTED, SUBMITTED и т.д.).
     * @param status Статус отчета
     * @return Список
     */
    List<TaxReport> findByStatus(String status);

    /**
     * Поиск отчетов конкретного ответственного лица.
     * @param responsiblePerson Имя бухгалтера
     * @return Список
     */
    List<TaxReport> findByResponsiblePerson(String responsiblePerson);
}
