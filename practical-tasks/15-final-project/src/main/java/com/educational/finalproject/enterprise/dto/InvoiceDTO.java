package com.educational.finalproject.enterprise.dto;

import java.time.LocalDateTime;

/**
 * <p>Data Transfer Object для инвойса (Invoice).</p>
 * <p>Класс предназначен для передачи детальной информации о счетах через REST интерфейс. 
 * Он содержит расширенный набор полей, включая реквизиты сторон и расчетные суммы, 
 * что позволяет фронтенд-приложениям отображать полноценные финансовые документы.</p>
 * 
 * @author Antigravity
 */
public class InvoiceDTO {

    private Long id;
    private String invoiceNumber;
    private LocalDateTime issueDate;
    private LocalDateTime dueDate;
    private LocalDateTime paymentDate;
    private String invoiceType;
    private String status;
    
    private String vendorName;
    private String vendorTaxId;
    private String customerName;
    private String customerTaxId;
    
    private String billingAddress;
    private String shippingAddress;
    
    private double netAmount;
    private double taxTotal;
    private double discountTotal;
    private double grossAmount;
    private String currency;
    
    private String paymentTerms;
    private String orderReference;
    private String clientNotes;

    /**
     * Конструктор по умолчанию.
     */
    public InvoiceDTO() {}

    // --- MANUAL GETTERS AND SETTERS FOR VOLUME ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public LocalDateTime getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDateTime issueDate) { this.issueDate = issueDate; }

    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }

    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }

    public String getInvoiceType() { return invoiceType; }
    public void setInvoiceType(String invoiceType) { this.invoiceType = invoiceType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getVendorName() { return vendorName; }
    public void setVendorName(String vendorName) { this.vendorName = vendorName; }

    public String getVendorTaxId() { return vendorTaxId; }
    public void setVendorTaxId(String vendorTaxId) { this.vendorTaxId = vendorTaxId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerTaxId() { return customerTaxId; }
    public void setCustomerTaxId(String customerTaxId) { this.customerTaxId = customerTaxId; }

    public String getBillingAddress() { return billingAddress; }
    public void setBillingAddress(String billingAddress) { this.billingAddress = billingAddress; }

    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }

    public double getNetAmount() { return netAmount; }
    public void setNetAmount(double netAmount) { this.netAmount = netAmount; }

    public double getTaxTotal() { return taxTotal; }
    public void setTaxTotal(double taxTotal) { this.taxTotal = taxTotal; }

    public double getDiscountTotal() { return discountTotal; }
    public void setDiscountTotal(double discountTotal) { this.discountTotal = discountTotal; }

    public double getGrossAmount() { return grossAmount; }
    public void setGrossAmount(double grossAmount) { this.grossAmount = grossAmount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getPaymentTerms() { return paymentTerms; }
    public void setPaymentTerms(String paymentTerms) { this.paymentTerms = paymentTerms; }

    public String getOrderReference() { return orderReference; }
    public void setOrderReference(String orderReference) { this.orderReference = orderReference; }

    public String getClientNotes() { return clientNotes; }
    public void setClientNotes(String clientNotes) { this.clientNotes = clientNotes; }
}
