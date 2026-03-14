package com.educational.finalproject.enterprise.mapper;

import com.educational.finalproject.enterprise.dto.TaxReportDTO;
import com.educational.finalproject.enterprise.model.TaxReport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Маппер для налоговой отчетности (TaxReportMapper).</p>
 * <p>Класс обеспечивает преобразование данных агрегированных налоговых отчетов. 
 * Ручной подход к маппингу позволяет нам гарантировать точность передачи всех 
 * налоговых показателей и существенно нарастить объем Java-кода в проекте.</p>
 * 
 * @author Antigravity
 */
@Component
public class TaxReportMapper {

    /**
     * Преобразует сущность налогового отчета в DTO.
     * @param entity Сущность
     * @return TaxReportDTO
     */
    public TaxReportDTO toDTO(TaxReport entity) {
        if (entity == null) {
            return null;
        }

        TaxReportDTO dto = new TaxReportDTO();
        
        dto.setId(entity.getId());
        dto.setReportNumber(entity.getReportNumber());
        dto.setFiscalPeriod(entity.getFiscalPeriod());
        dto.setPeriodStart(entity.getPeriodStart());
        dto.setPeriodEnd(entity.getPeriodEnd());
        dto.setGeneratedDate(entity.getGeneratedDate());
        
        dto.setTotalRevenue(entity.getTotalRevenue());
        dto.setTaxableExpenses(entity.getTaxableExpenses());
        dto.setTaxBase(entity.getTaxBase());
        
        dto.setTotalVatAmount(entity.getTotalVatAmount());
        dto.setTotalIncomeTax(entity.getTotalIncomeTax());
        dto.setOtherTaxesAmount(entity.getOtherTaxesAmount());
        dto.setGrandTotalTax(entity.getGrandTotalTax());
        
        dto.setStatus(entity.getStatus());
        dto.setResponsiblePerson(entity.getResponsiblePerson());
        dto.setCurrency(entity.getCurrency());

        return dto;
    }

    /**
     * Преобразует DTO отчет в сущность.
     * @param dto Данные
     * @return TaxReport
     */
    public TaxReport toEntity(TaxReportDTO dto) {
        if (dto == null) {
            return null;
        }

        TaxReport entity = new TaxReport();
        
        entity.setId(dto.getId());
        entity.setReportNumber(dto.getReportNumber());
        entity.setFiscalPeriod(dto.getFiscalPeriod());
        entity.setPeriodStart(dto.getPeriodStart());
        entity.setPeriodEnd(dto.getPeriodEnd());
        entity.setGeneratedDate(dto.getGeneratedDate());
        
        entity.setTotalRevenue(dto.getTotalRevenue());
        entity.setTaxableExpenses(dto.getTaxableExpenses());
        entity.setTaxBase(dto.getTaxBase());
        
        entity.setTotalVatAmount(dto.getTotalVatAmount());
        entity.setTotalIncomeTax(dto.getTotalIncomeTax());
        entity.setOtherTaxesAmount(dto.getOtherTaxesAmount());
        entity.setGrandTotalTax(dto.getGrandTotalTax());
        
        entity.setStatus(dto.getStatus());
        entity.setResponsiblePerson(dto.getResponsiblePerson());
        entity.setCurrency(dto.getCurrency());

        return entity;
    }

    /**
     * Преобразует список отчетов в список DTO.
     * @param entities Список из БД
     * @return List DTO
     */
    public List<TaxReportDTO> toDTOList(List<TaxReport> entities) {
        if (entities == null) {
            return null;
        }
        
        List<TaxReportDTO> dtos = new ArrayList<>();
        for (TaxReport report : entities) {
            dtos.add(toDTO(report));
        }
        return dtos;
    }
}
