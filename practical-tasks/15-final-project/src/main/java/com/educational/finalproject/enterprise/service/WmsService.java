package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.model.Product;
import com.educational.finalproject.enterprise.model.Warehouse;
import com.educational.finalproject.enterprise.repository.ProductRepository;
import com.educational.finalproject.enterprise.repository.WarehouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Сервис управления складом (Warehouse Management System Service).
 */
@Service
@SuppressWarnings("null")
public class WmsService {

    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository;

    public WmsService(WarehouseRepository warehouseRepository, ProductRepository productRepository) {
        this.warehouseRepository = warehouseRepository;
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    @Transactional
    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    @Transactional
    public Product addProductToWarehouse(Long warehouseId, Product product) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Склад не найден"));
        
        product.setWarehouse(warehouse);
        return productRepository.save(product);
    }

    /**
     * Бизнес-логика инвентаризации: обновление остатков.
     */
    @Transactional
    public Product updateStock(String sku, int quantityChange) {
        Product product = productRepository.findBySku(sku)
                .orElseThrow(() -> new RuntimeException("Товар не найден"));
        
        int newQuantity = product.getQuantityInStock() + quantityChange;
        if (newQuantity < 0) {
            throw new RuntimeException("Недостаточно товара на складе");
        }
        
        product.setQuantityInStock(newQuantity);
        return productRepository.save(product);
    }

    /**
     * Проверка товаров, уровень запасов которых ниже минимального.
     */
    @Transactional(readOnly = true)
    public List<Product> getLowStockProducts() {
        return productRepository.findAll().stream()
                .filter(p -> p.getQuantityInStock() <= p.getMinimumStockLevel())
                .toList();
    }

    @Transactional
    public void transferProduct(String sku, Long targetWarehouseId) {
        Product product = productRepository.findBySku(sku)
                .orElseThrow(() -> new RuntimeException("Товар не найден"));
        
        Warehouse target = warehouseRepository.findById(targetWarehouseId)
                .orElseThrow(() -> new RuntimeException("Целевой склад не найден"));
        
        product.setWarehouse(target);
        productRepository.save(product);
    }
}
