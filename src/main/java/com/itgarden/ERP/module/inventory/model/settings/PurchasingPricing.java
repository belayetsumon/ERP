/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.model.settings;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author User
 */
@Entity
public class PurchasingPricing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    int itemId;
    int SupplierId;
    int price;
    @ManyToOne
    Units suppliersUnitofMeasure;
    int ConversionFactorUom;
    String supplierCodeDescription;

    public PurchasingPricing() {
    }

    public PurchasingPricing(Long id, int itemId, int SupplierId, int price, Units suppliersUnitofMeasure, int ConversionFactorUom, String supplierCodeDescription) {
        this.id = id;
        this.itemId = itemId;
        this.SupplierId = SupplierId;
        this.price = price;
        this.suppliersUnitofMeasure = suppliersUnitofMeasure;
        this.ConversionFactorUom = ConversionFactorUom;
        this.supplierCodeDescription = supplierCodeDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(int SupplierId) {
        this.SupplierId = SupplierId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Units getSuppliersUnitofMeasure() {
        return suppliersUnitofMeasure;
    }

    public void setSuppliersUnitofMeasure(Units suppliersUnitofMeasure) {
        this.suppliersUnitofMeasure = suppliersUnitofMeasure;
    }

    public int getConversionFactorUom() {
        return ConversionFactorUom;
    }

    public void setConversionFactorUom(int ConversionFactorUom) {
        this.ConversionFactorUom = ConversionFactorUom;
    }

    public String getSupplierCodeDescription() {
        return supplierCodeDescription;
    }

    public void setSupplierCodeDescription(String supplierCodeDescription) {
        this.supplierCodeDescription = supplierCodeDescription;
    }
    
    
}
