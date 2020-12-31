/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.fixed_assets.model.settings;

import com.itgarden.ERP.module.finance_banking.model.settings.GlAccounts;
import com.itgarden.ERP.module.fixed_assets.model.enulvalue.DepreciationMethod;
import com.itgarden.ERP.module.fixed_assets.model.enulvalue.FixedAssetStutas;
import com.itgarden.ERP.module.inventory.model.settings.Units;
import com.itgarden.ERP.module.settings.model.company_setup.ItemTaxTypes;
import com.itgarden.ERP.module.settings.model.enumvalue.YesNo;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Entity
public class FixedAssets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /// General Settings ///
    @NotNull(message = "Item Code cannot be blank.")
    int itemCode;

    @NotEmpty(message = "Name cannot be blank.")
    String name;

    String description;

    @NotNull(message = "Category cannot be blank.")
    @ManyToOne
    FixedAssetsCategories category;
    @NotNull(message = "Item Tax Type cannot be blank.")

    @ManyToOne
    ItemTaxTypes itemTaxType;

    @NotNull(message = "Units of measure cannot be blank.")
    @ManyToOne
    Units unitsOfMeasure;

    @NotNull(message = "Dditable Description cannot be blank.")
    @Enumerated(EnumType.STRING)
    YesNo editableDescription;

    @NotNull(message = "Exclude from purchases cannot be blank.")
    @Enumerated(EnumType.STRING)
    YesNo excludeFromPurchases;

    /// Depreciation//
    @NotNull(message = "Fixed asset class cannot be blank.")
    @ManyToOne
    FixedAssetsClasses fixedAssetClass;

    @NotNull(message = "Depreciation method cannot be blank.")
    @Enumerated(EnumType.STRING)
    DepreciationMethod depreciationMethod;

    @NotNull(message = "Depreciation Rate cannot be blank.")
    int depreciationRate;
    @NotNull(message = "Base Rate cannot be blank.")
   
    int baseRate;
    @NotNull(message = "Rate multiplier cannot be blank.")
    int rateMultiplier;

    @NotNull(message = "Depreciation Years cannot be blank.")
    int depreciationYears;

    @NotNull(message = "Depreciation start date be blank.")
    Date depreciationStartDate;

    // GL Accounts //
    @NotNull(message = "Sales account cannot be blank.")
    @ManyToOne
    GlAccounts salesAccount;

    @NotNull(message = "Asset account cannot be blank.")
    @ManyToOne
    GlAccounts assetAccount;

    @NotNull(message = "Depreciation cost account cannot be blank.")
    @ManyToOne
    GlAccounts depreciationCostAccount;

    @NotNull(message = "Depreciation or disposal account cannot be blank.")
    @ManyToOne
    GlAccounts DepreciationOrDisposalAccount;

    @NotNull(message = "Status cannot be blank.")
    @Enumerated(EnumType.STRING)
    FixedAssetStutas ststus;

    public FixedAssets() {
    }

    public FixedAssets(Long id, int itemCode, String name, String description, FixedAssetsCategories category, ItemTaxTypes itemTaxType, Units unitsOfMeasure, YesNo editableDescription, YesNo excludeFromPurchases, FixedAssetsClasses fixedAssetClass, DepreciationMethod depreciationMethod, int depreciationRate, int baseRate, int rateMultiplier, int depreciationYears, Date depreciationStartDate, GlAccounts salesAccount, GlAccounts assetAccount, GlAccounts depreciationCostAccount, GlAccounts DepreciationOrDisposalAccount, FixedAssetStutas ststus) {
        this.id = id;
        this.itemCode = itemCode;
        this.name = name;
        this.description = description;
        this.category = category;
        this.itemTaxType = itemTaxType;
        this.unitsOfMeasure = unitsOfMeasure;
        this.editableDescription = editableDescription;
        this.excludeFromPurchases = excludeFromPurchases;
        this.fixedAssetClass = fixedAssetClass;
        this.depreciationMethod = depreciationMethod;
        this.depreciationRate = depreciationRate;
        this.baseRate = baseRate;
        this.rateMultiplier = rateMultiplier;
        this.depreciationYears = depreciationYears;
        this.depreciationStartDate = depreciationStartDate;
        this.salesAccount = salesAccount;
        this.assetAccount = assetAccount;
        this.depreciationCostAccount = depreciationCostAccount;
        this.DepreciationOrDisposalAccount = DepreciationOrDisposalAccount;
        this.ststus = ststus;
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

    public FixedAssetsCategories getCategory() {
        return category;
    }

    public void setCategory(FixedAssetsCategories category) {
        this.category = category;
    }

    public ItemTaxTypes getItemTaxType() {
        return itemTaxType;
    }

    public void setItemTaxType(ItemTaxTypes itemTaxType) {
        this.itemTaxType = itemTaxType;
    }

    public Units getUnitsOfMeasure() {
        return unitsOfMeasure;
    }

    public void setUnitsOfMeasure(Units unitsOfMeasure) {
        this.unitsOfMeasure = unitsOfMeasure;
    }

    public YesNo getEditableDescription() {
        return editableDescription;
    }

    public void setEditableDescription(YesNo editableDescription) {
        this.editableDescription = editableDescription;
    }

    public YesNo getExcludeFromPurchases() {
        return excludeFromPurchases;
    }

    public void setExcludeFromPurchases(YesNo excludeFromPurchases) {
        this.excludeFromPurchases = excludeFromPurchases;
    }

    public FixedAssetsClasses getFixedAssetClass() {
        return fixedAssetClass;
    }

    public void setFixedAssetClass(FixedAssetsClasses fixedAssetClass) {
        this.fixedAssetClass = fixedAssetClass;
    }

    public DepreciationMethod getDepreciationMethod() {
        return depreciationMethod;
    }

    public void setDepreciationMethod(DepreciationMethod depreciationMethod) {
        this.depreciationMethod = depreciationMethod;
    }

    public int getDepreciationRate() {
        return depreciationRate;
    }

    public void setDepreciationRate(int depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    public int getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(int baseRate) {
        this.baseRate = baseRate;
    }

    public int getRateMultiplier() {
        return rateMultiplier;
    }

    public void setRateMultiplier(int rateMultiplier) {
        this.rateMultiplier = rateMultiplier;
    }

    public int getDepreciationYears() {
        return depreciationYears;
    }

    public void setDepreciationYears(int depreciationYears) {
        this.depreciationYears = depreciationYears;
    }

    public Date getDepreciationStartDate() {
        return depreciationStartDate;
    }

    public void setDepreciationStartDate(Date depreciationStartDate) {
        this.depreciationStartDate = depreciationStartDate;
    }

    public GlAccounts getSalesAccount() {
        return salesAccount;
    }

    public void setSalesAccount(GlAccounts salesAccount) {
        this.salesAccount = salesAccount;
    }

    public GlAccounts getAssetAccount() {
        return assetAccount;
    }

    public void setAssetAccount(GlAccounts assetAccount) {
        this.assetAccount = assetAccount;
    }

    public GlAccounts getDepreciationCostAccount() {
        return depreciationCostAccount;
    }

    public void setDepreciationCostAccount(GlAccounts depreciationCostAccount) {
        this.depreciationCostAccount = depreciationCostAccount;
    }

    public GlAccounts getDepreciationOrDisposalAccount() {
        return DepreciationOrDisposalAccount;
    }

    public void setDepreciationOrDisposalAccount(GlAccounts DepreciationOrDisposalAccount) {
        this.DepreciationOrDisposalAccount = DepreciationOrDisposalAccount;
    }

    public FixedAssetStutas getStstus() {
        return ststus;
    }

    public void setStstus(FixedAssetStutas ststus) {
        this.ststus = ststus;
    }

    
}
