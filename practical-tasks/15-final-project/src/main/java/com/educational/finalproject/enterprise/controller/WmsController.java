package com.educational.finalproject.enterprise.controller;

import com.educational.finalproject.enterprise.model.Product;
import com.educational.finalproject.enterprise.model.Warehouse;
import com.educational.finalproject.enterprise.service.WmsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST контроллер для управления складом.
 */
@RestController
@RequestMapping("/api/wms")
public class WmsController {

    private final WmsService wmsService;

    public WmsController(WmsService wmsService) {
        this.wmsService = wmsService;
    }

    @GetMapping("/warehouses")
    public List<Warehouse> getAllWarehouses() {
        return wmsService.getAllWarehouses();
    }

    @PostMapping("/warehouses")
    public Warehouse createWarehouse(@RequestBody Warehouse warehouse) {
        return wmsService.createWarehouse(warehouse);
    }

    @PostMapping("/warehouses/{id}/products")
    public Product addProduct(@PathVariable Long id, @RequestBody Product product) {
        return wmsService.addProductToWarehouse(id, product);
    }

    @PutMapping("/products/stock")
    public Product updateStock(@RequestParam String sku, @RequestParam int change) {
        return wmsService.updateStock(sku, change);
    }

    @GetMapping("/products/low-stock")
    public List<Product> getLowStock() {
        return wmsService.getLowStockProducts();
    }

    @PostMapping("/products/transfer")
    public void transfer(@RequestParam String sku, @RequestParam Long targetId) {
        wmsService.transferProduct(sku, targetId);
    }
}
