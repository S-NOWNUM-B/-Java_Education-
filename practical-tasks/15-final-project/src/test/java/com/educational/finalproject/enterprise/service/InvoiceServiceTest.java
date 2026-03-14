package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.model.Invoice;
import com.educational.finalproject.enterprise.repository.InvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * <p>Тестовый класс для InvoiceService.</p>
 * <p>Проверяет бизнес-логику работы со счетами на оплату. 
 * Включает тесты на расчет итоговых сумм и смену статусов оплаты.</p>
 * 
 * @author Antigravity
 */
public class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceService invoiceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * <p>Тест создания счета.</p>
     */
    @Test
    @DisplayName("Проверка генерации счета с расчетом итоговой суммы")
    void testCreateInvoice() {
        // Given
        double amount = 5000.0;
        String customer = "Global Tech Corp";
        Invoice mockInvoice = new Invoice();
        mockInvoice.setId(1L);
        mockInvoice.setAmount(amount);
        mockInvoice.setCustomerName(customer);
        mockInvoice.setPaid(false);
        
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(mockInvoice);

        // When
        Invoice result = invoiceService.createInvoice(customer, amount);

        // Then
        assertNotNull(result);
        assertEquals(customer, result.getCustomerName());
        assertEquals(amount, result.getAmount());
        assertFalse(result.isPaid(), "Новый счет не должен быть оплачен");
        
        verify(invoiceRepository, times(1)).save(any(Invoice.class));
    }

    /**
     * <p>Тест оплаты счета.</p>
     */
    @Test
    @DisplayName("Проверка процесса пометки счета как оплаченного")
    void testPayInvoice() {
        // Given
        Long invoiceId = 1L;
        Invoice invoice = new Invoice();
        invoice.setId(invoiceId);
        invoice.setPaid(false);

        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        // When
        invoiceService.markAsPaid(invoiceId);

        // Then
        assertTrue(invoice.isPaid(), "Статус счета должен измениться на оплачен");
        verify(invoiceRepository, times(1)).save(invoice);
    }

    /**
     * <p>Тест удаления счета.</p>
     */
    @Test
    @DisplayName("Проверка удаления счета из системы")
    void testDeleteInvoice() {
        // When
        invoiceService.deleteInvoice(1L);

        // Then
        verify(invoiceRepository, times(1)).deleteById(1L);
    }
}
