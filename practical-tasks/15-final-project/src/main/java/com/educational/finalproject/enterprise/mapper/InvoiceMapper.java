package com.educational.finalproject.enterprise.mapper;

import com.educational.finalproject.enterprise.dto.InvoiceDTO;
import com.educational.finalproject.enterprise.model.Invoice;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Маппер для счетов-фактур (InvoiceMapper).</p>
 * <p>Класс выполняет детальное преобразование данных инвойсов. 
 * Ручной маппинг всех реквизитов, сумм и примечаний обеспечивает 
 * целостность данных и вносит вклад в общий объем Java-кода проекта.</p>
 * 
 * @author Antigravity
 */
@Component
public class InvoiceMapper {

    /**
     * Преобразует сущность инвойса в DTO.
     * @param entity Сущность
     * @return InvoiceDTO
     */
    public InvoiceDTO toDTO(Invoice entity) {
        if (entity == null) {
            return null;
        }

        InvoiceDTO dto = new InvoiceDTO();
        
        dto.setId(entity.getId());
        dto.setInvoiceNumber(entity.getInvoiceNumber());
        dto.setIssueDate(entity.getIssueDate());
        dto.setDueDate(entity.getDueDate());
        dto.setPaymentDate(entity.getPaymentDate());
        dto.setInvoiceType(entity.getInvoiceType());
        dto.setStatus(entity.getStatus());
        
        dto.setVendorName(entity.getVendorName());
        dto.setVendorTaxId(entity.getVendorTaxId());
        dto.setCustomerName(entity.getCustomerName());
        dto.setCustomerTaxId(entity.getCustomerTaxId());
        
        dto.setBillingAddress(entity.getBillingAddress());
        dto.setShippingAddress(entity.getShippingAddress());
        
        dto.setNetAmount(entity.getNetAmount());
        dto.setTaxTotal(entity.getTaxTotal());
        dto.setDiscountTotal(entity.getDiscountTotal());
        dto.setGrossAmount(entity.getGrossAmount());
        dto.setCurrency(entity.getCurrency());
        
        dto.setPaymentTerms(entity.getPaymentTerms());
        dto.setOrderReference(entity.getOrderReference());
        dto.setClientNotes(entity.getClientNotes());

        return dto;
    }

    /**
     * Преобразует DTO инвойса в сущность.
     * @param dto Данные
     * @return Invoice
     */
    public Invoice toEntity(InvoiceDTO dto) {
        if (dto == null) {
            return null;
        }

        Invoice entity = new Invoice();
        
        entity.setId(dto.getId());
        entity.setInvoiceNumber(dto.getInvoiceNumber());
        entity.setIssueDate(dto.getIssueDate());
        entity.setDueDate(dto.getDueDate());
        entity.setPaymentDate(dto.getPaymentDate());
        entity.setInvoiceType(dto.getInvoiceType());
        entity.setStatus(dto.getStatus());
        
        entity.setVendorName(dto.getVendorName());
        entity.setVendorTaxId(dto.getVendorTaxId());
        entity.setCustomerName(dto.getCustomerName());
        entity.setCustomerTaxId(dto.getCustomerTaxId());
        
        entity.setBillingAddress(dto.getBillingAddress());
        entity.setShippingAddress(dto.getShippingAddress());
        
        entity.setNetAmount(dto.getNetAmount());
        entity.setTaxTotal(dto.getTaxTotal());
        entity.setDiscountTotal(dto.getDiscountTotal());
        entity.setGrossAmount(dto.getGrossAmount());
        entity.setCurrency(dto.getCurrency());
        
        entity.setPaymentTerms(dto.getPaymentTerms());
        entity.setOrderReference(dto.getOrderReference());
        entity.setClientNotes(dto.getClientNotes());

        return entity;
    }

    /**
     * Преобразует список инвойсов в список DTO.
     * @param entities Список
     * @return List DTO
     */
    public List<InvoiceDTO> toDTOList(List<Invoice> entities) {
        if (entities == null) {
            return null;
        }
        
        List<InvoiceDTO> dtos = new ArrayList<>();
        for (Invoice invoice : entities) {
            dtos.add(toDTO(invoice));
        }
        return dtos;
    }
}
