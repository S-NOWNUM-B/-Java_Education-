package com.educational.finalproject.enterprise.dto;

import java.util.List;

/**
 * <p>Data Transfer Object для спецификации изделия (BillOfMaterialsDTO).</p>
 * <p>Передает структуру продукта и список необходимых компонентов. 
 * Используется для конфигурации производства и расчета себестоимости.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
public class BillOfMaterialsDTO {

    private Long id;
    private String productName;
    private String version;
    private boolean isApproved;
    private List<String> components;
    private String notes;
    private int totalComponentCount; // Вычисляемое поле

    /**
     * Конструктор по умолчанию.
     */
    public BillOfMaterialsDTO() {}

    // --- MANUAL GETTERS AND SETTERS ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public List<String> getComponents() {
        return components;
    }

    public void setComponents(List<String> components) {
        this.components = components;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getTotalComponentCount() {
        return totalComponentCount;
    }

    public void setTotalComponentCount(int totalComponentCount) {
        this.totalComponentCount = totalComponentCount;
    }
}
