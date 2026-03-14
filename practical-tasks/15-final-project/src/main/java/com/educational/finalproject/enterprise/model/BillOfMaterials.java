package com.educational.finalproject.enterprise.model;

import jakarta.persistence.*;
import java.util.List;

/**
 * <p>Сущность спецификации изделия (BillOfMaterials - BOM).</p>
 * <p>Определяет перечень и количество компонентов, необходимых 
 * для сборки финального продукта. Является основой для планирования 
 * закупок и производственных затрат.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
@Entity
@Table(name = "bill_of_materials")
public class BillOfMaterials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "version")
    private String version;

    @Column(name = "is_approved")
    private boolean isApproved;

    @ElementCollection
    @CollectionTable(name = "bom_components", joinColumns = @JoinColumn(name = "bom_id"))
    @Column(name = "component_name")
    private List<String> components;

    @Column(name = "notes")
    private String notes;

    /**
     * Конструктор по умолчанию.
     */
    public BillOfMaterials() {}

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
}
