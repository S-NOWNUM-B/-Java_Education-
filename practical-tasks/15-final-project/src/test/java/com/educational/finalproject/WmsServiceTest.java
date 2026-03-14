package com.educational.finalproject;

import com.educational.finalproject.enterprise.model.Product;
import com.educational.finalproject.enterprise.model.Warehouse;
import com.educational.finalproject.enterprise.repository.ProductRepository;
import com.educational.finalproject.enterprise.repository.WarehouseRepository;
import com.educational.finalproject.enterprise.service.WmsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Тесты для WmsService.
 */
class WmsServiceTest {

    @Mock
    private WarehouseRepository warehouseRepository;
    @Mock
    private ProductRepository productRepository;
    
    private WmsService wmsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        wmsService = new WmsService(warehouseRepository, productRepository);
    }

    @Test
    @DisplayName("Должен возвращать все склады")
    void shouldReturnAllWarehouses() {
        Warehouse w1 = new Warehouse("WH01", "Main Warehouse");
        Warehouse w2 = new Warehouse("WH02", "Spare Warehouse");
        when(warehouseRepository.findAll()).thenReturn(Arrays.asList(w1, w2));

        List<Warehouse> result = wmsService.getAllWarehouses();

        assertEquals(2, result.size());
        verify(warehouseRepository).findAll();
    }

    @Test
    @DisplayName("Должен добавлять товар на склад")
    void shouldAddProductToWarehouse() {
        Warehouse warehouse = new Warehouse("WH01", "Main");
        warehouse.setId(1L);
        Product product = new Product("SKU123", "Product 1", 100.0);

        when(warehouseRepository.findById(1L)).thenReturn(Optional.of(warehouse));
        when(productRepository.save(any(Product.class))).thenAnswer(i -> i.getArguments()[0]);

        Product result = wmsService.addProductToWarehouse(1L, product);

        assertNotNull(result.getWarehouse());
        assertEquals("WH01", result.getWarehouse().getWarehouseCode());
        verify(productRepository).save(product);
    }

    @Test
    @DisplayName("Должен обновлять остатки на складе")
    void shouldUpdateStock() {
        Product product = new Product("SKU123", "Product 1", 100.0);
        product.setQuantityInStock(10);
        
        when(productRepository.findBySku("SKU123")).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenAnswer(i -> i.getArguments()[0]);

        Product result = wmsService.updateStock("SKU123", 5);

        assertEquals(15, result.getQuantityInStock());
        verify(productRepository).save(product);
    }

    @Test
    @DisplayName("Должен выбрасывать ошибку если остаток уходит в минус")
    void shouldThrowErrorWhenStockNegative() {
        Product product = new Product("SKU123", "Product 1", 100.0);
        product.setQuantityInStock(5);
        when(productRepository.findBySku("SKU123")).thenReturn(Optional.of(product));

        assertThrows(RuntimeException.class, () -> wmsService.updateStock("SKU123", -10));
    }

    @Test
    @DisplayName("Должен находить товары с низким остатком")
    void shouldFindLowStockProducts() {
        Product p1 = new Product("P1", "Low", 10.0);
        p1.setQuantityInStock(1);
        p1.setMinimumStockLevel(5);
        
        Product p2 = new Product("P2", "High", 10.0);
        p2.setQuantityInStock(10);
        p2.setMinimumStockLevel(5);

        when(productRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Product> result = wmsService.getLowStockProducts();

        assertEquals(1, result.size());
        assertEquals("P1", result.get(0).getSku());
    }
}
