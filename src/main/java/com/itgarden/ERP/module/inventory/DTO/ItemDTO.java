/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.DTO;

import com.itgarden.ERP.module.finance_banking.model.settings.GlAccounts;
import com.itgarden.ERP.module.inventory.model.enumvalue.ItemType;
import com.itgarden.ERP.module.inventory.model.enumvalue.Itemstatus;
import com.itgarden.ERP.module.inventory.model.settings.ItemCategories;
import com.itgarden.ERP.module.inventory.model.settings.Units;
import com.itgarden.ERP.module.settings.model.company_setup.ItemTaxTypes;
import com.itgarden.ERP.module.settings.model.enumvalue.YesNo;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author User
 */
public class ItemDTO {

    public Long id;

    public int itemCode; // item code  bar code

    public String name;

    public String description;

    public Long itemCategoriesId;

    public String itemCategoriesName;

    public Long itemTaxTypeId;

    public String itemTaxTypeName;

    public ItemType itemType;

    public Long unitsofMeasureId;

    public String unitsofMeasureName;

    public YesNo editabledescription;

    public YesNo excludefromSales;

    public YesNo excludeFromPurchases;

    /// GL Accounts///
    public Long salesAccountId;

    public String salesAccountName;

    public Long cogsAccountId;

    public String cogsAccountName;

    public Long inventoryAccountId;

    public String inventoryAccountName;

    public Long InventoryadjustmentsAccountId;

    public String InventoryadjustmentsAccountName;

    //  Others //
    public Itemstatus itemstatus;

//    Price
    public double discount;

    public double retailPrice;
    public double wholesalePrice;

    public BigDecimal purchasePrice;

    public BigDecimal standardCost;

    /// Audit /// 
//    @Version
//    private int version;
//
    private String createdBy;

    private String created;

    private String modifiedBy;

    public ItemDTO() {
    }

    public ItemDTO(Long id, int itemCode, String name, String description, Long itemCategoriesId, String itemCategoriesName, Long itemTaxTypeId, String itemTaxTypeName, ItemType itemType, Long unitsofMeasureId, String unitsofMeasureName, YesNo editabledescription, YesNo excludefromSales, YesNo excludeFromPurchases, Long salesAccountId, String salesAccountName, Long cogsAccountId, String cogsAccountName, Long inventoryAccountId, String inventoryAccountName, Long InventoryadjustmentsAccountId, String InventoryadjustmentsAccountName, Itemstatus itemstatus, double discount, double retailPrice, double wholesalePrice, BigDecimal purchasePrice, BigDecimal standardCost, String createdBy, String created, String modifiedBy) {
        this.id = id;
        this.itemCode = itemCode;
        this.name = name;
        this.description = description;
        this.itemCategoriesId = itemCategoriesId;
        this.itemCategoriesName = itemCategoriesName;
        this.itemTaxTypeId = itemTaxTypeId;
        this.itemTaxTypeName = itemTaxTypeName;
        this.itemType = itemType;
        this.unitsofMeasureId = unitsofMeasureId;
        this.unitsofMeasureName = unitsofMeasureName;
        this.editabledescription = editabledescription;
        this.excludefromSales = excludefromSales;
        this.excludeFromPurchases = excludeFromPurchases;
        this.salesAccountId = salesAccountId;
        this.salesAccountName = salesAccountName;
        this.cogsAccountId = cogsAccountId;
        this.cogsAccountName = cogsAccountName;
        this.inventoryAccountId = inventoryAccountId;
        this.inventoryAccountName = inventoryAccountName;
        this.InventoryadjustmentsAccountId = InventoryadjustmentsAccountId;
        this.InventoryadjustmentsAccountName = InventoryadjustmentsAccountName;
        this.itemstatus = itemstatus;
        this.discount = discount;
        this.retailPrice = retailPrice;
        this.wholesalePrice = wholesalePrice;
        this.purchasePrice = purchasePrice;
        this.standardCost = standardCost;
        this.createdBy = createdBy;
        this.created = created;
        this.modifiedBy = modifiedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
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

    public Long getItemCategoriesId() {
        return itemCategoriesId;
    }

    public void setItemCategoriesId(Long itemCategoriesId) {
        this.itemCategoriesId = itemCategoriesId;
    }

    public String getItemCategoriesName() {
        return itemCategoriesName;
    }

    public void setItemCategoriesName(String itemCategoriesName) {
        this.itemCategoriesName = itemCategoriesName;
    }

    public Long getItemTaxTypeId() {
        return itemTaxTypeId;
    }

    public void setItemTaxTypeId(Long itemTaxTypeId) {
        this.itemTaxTypeId = itemTaxTypeId;
    }

    public String getItemTaxTypeName() {
        return itemTaxTypeName;
    }

    public void setItemTaxTypeName(String itemTaxTypeName) {
        this.itemTaxTypeName = itemTaxTypeName;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Long getUnitsofMeasureId() {
        return unitsofMeasureId;
    }

    public void setUnitsofMeasureId(Long unitsofMeasureId) {
        this.unitsofMeasureId = unitsofMeasureId;
    }

    public String getUnitsofMeasureName() {
        return unitsofMeasureName;
    }

    public void setUnitsofMeasureName(String unitsofMeasureName) {
        this.unitsofMeasureName = unitsofMeasureName;
    }

    public YesNo getEditabledescription() {
        return editabledescription;
    }

    public void setEditabledescription(YesNo editabledescription) {
        this.editabledescription = editabledescription;
    }

    public YesNo getExcludefromSales() {
        return excludefromSales;
    }

    public void setExcludefromSales(YesNo excludefromSales) {
        this.excludefromSales = excludefromSales;
    }

    public YesNo getExcludeFromPurchases() {
        return excludeFromPurchases;
    }

    public void setExcludeFromPurchases(YesNo excludeFromPurchases) {
        this.excludeFromPurchases = excludeFromPurchases;
    }

    public Long getSalesAccountId() {
        return salesAccountId;
    }

    public void setSalesAccountId(Long salesAccountId) {
        this.salesAccountId = salesAccountId;
    }

    public String getSalesAccountName() {
        return salesAccountName;
    }

    public void setSalesAccountName(String salesAccountName) {
        this.salesAccountName = salesAccountName;
    }

    public Long getCogsAccountId() {
        return cogsAccountId;
    }

    public void setCogsAccountId(Long cogsAccountId) {
        this.cogsAccountId = cogsAccountId;
    }

    public String getCogsAccountName() {
        return cogsAccountName;
    }

    public void setCogsAccountName(String cogsAccountName) {
        this.cogsAccountName = cogsAccountName;
    }

    public Long getInventoryAccountId() {
        return inventoryAccountId;
    }

    public void setInventoryAccountId(Long inventoryAccountId) {
        this.inventoryAccountId = inventoryAccountId;
    }

    public String getInventoryAccountName() {
        return inventoryAccountName;
    }

    public void setInventoryAccountName(String inventoryAccountName) {
        this.inventoryAccountName = inventoryAccountName;
    }

    public Long getInventoryadjustmentsAccountId() {
        return InventoryadjustmentsAccountId;
    }

    public void setInventoryadjustmentsAccountId(Long InventoryadjustmentsAccountId) {
        this.InventoryadjustmentsAccountId = InventoryadjustmentsAccountId;
    }

    public String getInventoryadjustmentsAccountName() {
        return InventoryadjustmentsAccountName;
    }

    public void setInventoryadjustmentsAccountName(String InventoryadjustmentsAccountName) {
        this.InventoryadjustmentsAccountName = InventoryadjustmentsAccountName;
    }

    public Itemstatus getItemstatus() {
        return itemstatus;
    }

    public void setItemstatus(Itemstatus itemstatus) {
        this.itemstatus = itemstatus;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public double getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(double wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getStandardCost() {
        return standardCost;
    }

    public void setStandardCost(BigDecimal standardCost) {
        this.standardCost = standardCost;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    

}
