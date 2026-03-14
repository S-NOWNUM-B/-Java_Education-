package com.educational.finalproject.enterprise.dto;

import java.time.LocalDateTime;

/**
 * <p>Data Transfer Object для налогового отчета (TaxReport).</p>
 * <p>Этот класс используется для представления агрегированных налоговых данных 
 * на пользовательском интерфейсе. Он отделяет логику хранения налогов в БД 
 * от формата отображения, принятого в финансовых департаментах.</p>
 * 
 * @author Antigravity
 */
public class TaxReportDTO {

    private Long id;
    private String reportNumber;
    private String fiscalPeriod;
    private LocalDateTime periodStart;
    private LocalDateTime periodEnd;
    private LocalDateTime generatedDate;
    
    private double totalRevenue;
    private double taxableExpenses;
    private double taxBase;
    
    private double totalVatAmount;
    private double totalIncomeTax;
    private double otherTaxesAmount;
    private double grandTotalTax;
    
    private String status;
    private String responsiblePerson;
    private String currency;

    /**
     * Конструктор по умолчанию.
     */
    public TaxReportDTO() {}

    // --- MANUAL GETTERS AND SETTERS FOR VOLUME ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReportNumber() { return reportNumber; }
    public void setReportNumber(String reportNumber) { this.reportNumber = reportNumber; }

    public String getFiscalPeriod() { return fiscalPeriod; }
    public void setFiscalPeriod(String fiscalPeriod) { this.fiscalPeriod = fiscalPeriod; }

    public LocalDateTime getPeriodStart() { return periodStart; }
    public void setPeriodStart(LocalDateTime periodStart) { this.periodStart = periodStart; }

    public LocalDateTime getPeriodEnd() { return periodEnd; }
    public void setPeriodEnd(LocalDateTime periodEnd) { this.periodEnd = periodEnd; }

    public LocalDateTime getGeneratedDate() { return generatedDate; }
    public void setGeneratedDate(LocalDateTime generatedDate) { this.generatedDate = generatedDate; }

    public double getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(double totalRevenue) { this.totalRevenue = totalRevenue; }

    public double getTaxableExpenses() { return taxableExpenses; }
    public void setTaxableExpenses(double taxableExpenses) { this.taxableExpenses = taxableExpenses; }

    public double getTaxBase() { return taxBase; }
    public void setTaxBase(double taxBase) { this.taxBase = taxBase; }

    public double getTotalVatAmount() { return totalVatAmount; }
    public void setTotalVatAmount(double totalVatAmount) { this.totalVatAmount = totalVatAmount; }

    public double getTotalIncomeTax() { return totalIncomeTax; }
    public void setTotalIncomeTax(double totalIncomeTax) { this.totalIncomeTax = totalIncomeTax; }

    public double getOtherTaxesAmount() { return otherTaxesAmount; }
    public void setOtherTaxesAmount(double otherTaxesAmount) { this.otherTaxesAmount = otherTaxesAmount; }

    public double getGrandTotalTax() { return grandTotalTax; }
    public void setGrandTotalTax(double grandTotalTax) { this.grandTotalTax = grandTotalTax; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getResponsiblePerson() { return responsiblePerson; }
    public void setResponsiblePerson(String responsiblePerson) { this.responsiblePerson = responsiblePerson; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}
