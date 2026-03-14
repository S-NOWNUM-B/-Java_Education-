package com.educational.finalproject.enterprise.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Сущность Warehouse представляет склад компании.
 * Используется в подсистеме управления складом (WMS).
 */
@Entity
@Table(name = "warehouses")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String warehouseCode;

    private String name;
    private String address;
    private String city;
    private String region;
    private String country;
    
    private double totalArea;
    private double availableArea;
    
    private int loadingDocksCount;
    private boolean hasRefrigeration;
    private boolean hasHazardousMaterialsStorage;
    
    private String securityLevel;
    private String operatingHours;
    
    private String managerName;
    private String contactPhone;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<Product> products;

    public Warehouse() {
        this.products = new ArrayList<>();
    }

    public Warehouse(String warehouseCode, String name) {
        this();
        this.warehouseCode = warehouseCode;
        this.name = name;
    }

    // Геттеры и сеттеры (ручное написание для объема кода)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(double totalArea) {
        this.totalArea = totalArea;
    }

    public double getAvailableArea() {
        return availableArea;
    }

    public void setAvailableArea(double availableArea) {
        this.availableArea = availableArea;
    }

    public int getLoadingDocksCount() {
        return loadingDocksCount;
    }

    public void setLoadingDocksCount(int loadingDocksCount) {
        this.loadingDocksCount = loadingDocksCount;
    }

    public boolean isHasRefrigeration() {
        return hasRefrigeration;
    }

    public void setHasRefrigeration(boolean hasRefrigeration) {
        this.hasRefrigeration = hasRefrigeration;
    }

    public boolean isHasHazardousMaterialsStorage() {
        return hasHazardousMaterialsStorage;
    }

    public void setHasHazardousMaterialsStorage(boolean hasHazardousMaterialsStorage) {
        this.hasHazardousMaterialsStorage = hasHazardousMaterialsStorage;
    }

    public String getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(String securityLevel) {
        this.securityLevel = securityLevel;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
