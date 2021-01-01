/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.model.settings;

import com.itgarden.ERP.module.Dimension.model.settings.Dimension;
import com.itgarden.ERP.module.finance_banking.model.settings.GlAccounts;
import com.itgarden.ERP.module.inventory.model.enumvalue.ItemType;
import com.itgarden.ERP.module.settings.model.company_setup.ItemTaxTypes;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author User
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull(message = "Item Code cannot be blank.")
    public String itemCode; // item code  bar code

    @NotEmpty(message = "Item/Product Name cannot be blank.")
    public String name;

    @Lob
    public String description;

    @NotNull(message = "Product category cannot be blank.")
    @ManyToOne
    public ItemCategories category;

    @ManyToOne(optional = true)
    public Manufacturer manufacturer;

    @ManyToOne(optional = true)
    public Brand brand;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    public ItemTaxTypes itemTaxType;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    public ItemType itemType;  // Purchase , manufuctor ,service

    @NotNull(message = "This field cannot be blank.")
    public String unitsofMeasure;

    public boolean editable;

    public boolean noSale;

    public boolean noPurchase;

    /// GL Accounts///
    @ManyToOne(optional = true)
    public GlAccounts salesAccount;

    @ManyToOne(optional = true)
    public GlAccounts cogsAccount;

    @ManyToOne(optional = true)
    public GlAccounts inventoryAccount;

    @ManyToOne(optional = true)
    public GlAccounts inventoryAdjustmentAccount;

    @ManyToOne(optional = true)
    public GlAccounts wipAccount;

//    Cost
    public double purchaseCost;

    public double materialCost;

    public double labourCost;

    public double overheadCost;

    public double discount;

    // fixed asset
    public char depreciationMethod;

    public double depreciationRate;

    public double depreciationFactor;

    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public Date depreciationStart;

    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public Date depreciationDate;

//  Others //
    @ManyToOne(optional = true)
    public Dimension dimension;

    public boolean itemstatus;

    /// Audit /// 
//    @Version
//    private int version;
//
    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime created;

    @LastModifiedBy
    @Column(insertable = false, updatable = true)
    private String modifiedBy;

    @LastModifiedDate
    @Column(insertable = false, updatable = true)
    private LocalDateTime modified;

//    Relation s
    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    public List<SalesPricing> salesPrice;

    @OneToOne(mappedBy = "itemId", fetch = FetchType.LAZY)
    public PurchasingPricing purchasingPricing;

    @OneToOne(mappedBy = "itemId", fetch = FetchType.LAZY)
    public StandardCosts standardCosts;

    @OneToMany(mappedBy = "itemId", fetch = FetchType.LAZY)
    public List<ReorderLevels> ReorderLevels;

    public Items() {
    }

    public Items(Long id, String itemCode, String name, String description, ItemCategories category, Manufacturer manufacturer, Brand brand, ItemTaxTypes itemTaxType, ItemType itemType, String unitsofMeasure, boolean editable, boolean noSale, boolean noPurchase, GlAccounts salesAccount, GlAccounts cogsAccount, GlAccounts inventoryAccount, GlAccounts inventoryAdjustmentAccount, GlAccounts wipAccount, double purchaseCost, double materialCost, double labourCost, double overheadCost, double discount, char depreciationMethod, double depreciationRate, double depreciationFactor, Date depreciationStart, Date depreciationDate, Dimension dimension, boolean itemstatus, String createdBy, LocalDateTime created, String modifiedBy, LocalDateTime modified, List<SalesPricing> salesPrice, PurchasingPricing purchasingPricing, StandardCosts standardCosts, List<ReorderLevels> ReorderLevels) {
        this.id = id;
        this.itemCode = itemCode;
        this.name = name;
        this.description = description;
        this.category = category;
        this.manufacturer = manufacturer;
        this.brand = brand;
        this.itemTaxType = itemTaxType;
        this.itemType = itemType;
        this.unitsofMeasure = unitsofMeasure;
        this.editable = editable;
        this.noSale = noSale;
        this.noPurchase = noPurchase;
        this.salesAccount = salesAccount;
        this.cogsAccount = cogsAccount;
        this.inventoryAccount = inventoryAccount;
        this.inventoryAdjustmentAccount = inventoryAdjustmentAccount;
        this.wipAccount = wipAccount;
        this.purchaseCost = purchaseCost;
        this.materialCost = materialCost;
        this.labourCost = labourCost;
        this.overheadCost = overheadCost;
        this.discount = discount;
        this.depreciationMethod = depreciationMethod;
        this.depreciationRate = depreciationRate;
        this.depreciationFactor = depreciationFactor;
        this.depreciationStart = depreciationStart;
        this.depreciationDate = depreciationDate;
        this.dimension = dimension;
        this.itemstatus = itemstatus;
        this.createdBy = createdBy;
        this.created = created;
        this.modifiedBy = modifiedBy;
        this.modified = modified;
        this.salesPrice = salesPrice;
        this.purchasingPricing = purchasingPricing;
        this.standardCosts = standardCosts;
        this.ReorderLevels = ReorderLevels;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemCategories getCategory() {
        return category;
    }

    public void setCategory(ItemCategories category) {
        this.category = category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public ItemTaxTypes getItemTaxType() {
        return itemTaxType;
    }

    public void setItemTaxType(ItemTaxTypes itemTaxType) {
        this.itemTaxType = itemTaxType;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public String getUnitsofMeasure() {
        return unitsofMeasure;
    }

    public void setUnitsofMeasure(String unitsofMeasure) {
        this.unitsofMeasure = unitsofMeasure;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isNoSale() {
        return noSale;
    }

    public void setNoSale(boolean noSale) {
        this.noSale = noSale;
    }

    public boolean isNoPurchase() {
        return noPurchase;
    }

    public void setNoPurchase(boolean noPurchase) {
        this.noPurchase = noPurchase;
    }

    public GlAccounts getSalesAccount() {
        return salesAccount;
    }

    public void setSalesAccount(GlAccounts salesAccount) {
        this.salesAccount = salesAccount;
    }

    public GlAccounts getCogsAccount() {
        return cogsAccount;
    }

    public void setCogsAccount(GlAccounts cogsAccount) {
        this.cogsAccount = cogsAccount;
    }

    public GlAccounts getInventoryAccount() {
        return inventoryAccount;
    }

    public void setInventoryAccount(GlAccounts inventoryAccount) {
        this.inventoryAccount = inventoryAccount;
    }

    public GlAccounts getInventoryAdjustmentAccount() {
        return inventoryAdjustmentAccount;
    }

    public void setInventoryAdjustmentAccount(GlAccounts inventoryAdjustmentAccount) {
        this.inventoryAdjustmentAccount = inventoryAdjustmentAccount;
    }

    public GlAccounts getWipAccount() {
        return wipAccount;
    }

    public void setWipAccount(GlAccounts wipAccount) {
        this.wipAccount = wipAccount;
    }

    public double getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(double purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    public double getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(double materialCost) {
        this.materialCost = materialCost;
    }

    public double getLabourCost() {
        return labourCost;
    }

    public void setLabourCost(double labourCost) {
        this.labourCost = labourCost;
    }

    public double getOverheadCost() {
        return overheadCost;
    }

    public void setOverheadCost(double overheadCost) {
        this.overheadCost = overheadCost;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public char getDepreciationMethod() {
        return depreciationMethod;
    }

    public void setDepreciationMethod(char depreciationMethod) {
        this.depreciationMethod = depreciationMethod;
    }

    public double getDepreciationRate() {
        return depreciationRate;
    }

    public void setDepreciationRate(double depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    public double getDepreciationFactor() {
        return depreciationFactor;
    }

    public void setDepreciationFactor(double depreciationFactor) {
        this.depreciationFactor = depreciationFactor;
    }

    public Date getDepreciationStart() {
        return depreciationStart;
    }

    public void setDepreciationStart(Date depreciationStart) {
        this.depreciationStart = depreciationStart;
    }

    public Date getDepreciationDate() {
        return depreciationDate;
    }

    public void setDepreciationDate(Date depreciationDate) {
        this.depreciationDate = depreciationDate;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public boolean isItemstatus() {
        return itemstatus;
    }

    public void setItemstatus(boolean itemstatus) {
        this.itemstatus = itemstatus;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public List<SalesPricing> getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(List<SalesPricing> salesPrice) {
        this.salesPrice = salesPrice;
    }

    public PurchasingPricing getPurchasingPricing() {
        return purchasingPricing;
    }

    public void setPurchasingPricing(PurchasingPricing purchasingPricing) {
        this.purchasingPricing = purchasingPricing;
    }

    public StandardCosts getStandardCosts() {
        return standardCosts;
    }

    public void setStandardCosts(StandardCosts standardCosts) {
        this.standardCosts = standardCosts;
    }

    public List<ReorderLevels> getReorderLevels() {
        return ReorderLevels;
    }

    public void setReorderLevels(List<ReorderLevels> ReorderLevels) {
        this.ReorderLevels = ReorderLevels;
    }

    
}
