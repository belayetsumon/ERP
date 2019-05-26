/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.fixed_assets.model.settings;

import com.itgarden.ERP.module.finance_banking.model.settings.GlAccounts;
import com.itgarden.ERP.module.fixed_assets.model.enulvalue.FixedAssetStutas;
import com.itgarden.ERP.module.inventory.model.settings.Units;
import com.itgarden.ERP.module.settings.model.company_setup.ItemTaxTypes;
import com.itgarden.ERP.module.settings.model.enumvalue.YesNo;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Entity
public class FixedAssetsCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = " Category Name cannot be blank")
    String categoryName;

    /// Default values for new items //
    @NotNull(message = "Item Tax Type cannot be blank.")
    @ManyToOne
    ItemTaxTypes itemTaxType;

    @NotNull(message = "Units of Measure cannot be blank.")
    @ManyToOne
    Units unitsofMeasure;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    YesNo excludeFromPurchases;

    @NotNull(message = "Sales account cannot be blank.")
    @ManyToOne
    GlAccounts salesAccount;

    @NotNull(message = "Asset account cannot be blank.")
    @ManyToOne
    GlAccounts assetAccount;

    @NotNull(message = "Depreciation Cost Account cannot be blank.")
    @ManyToOne
    GlAccounts depreciationCostAccount;

    @NotNull(message = "Depreciation Or Disposal Account cannot be blank.")
    @ManyToOne
    GlAccounts depreciationOrDisposalAccount;

    @NotNull(message = "Ststus cannot be blank.")
    @Enumerated(EnumType.STRING)
    FixedAssetStutas ststus;

    public FixedAssetsCategories() {
    }

    public FixedAssetsCategories(Long id, String categoryName, ItemTaxTypes itemTaxType, Units unitsofMeasure, YesNo excludeFromPurchases, GlAccounts salesAccount, GlAccounts assetAccount, GlAccounts depreciationCostAccount, GlAccounts depreciationOrDisposalAccount, FixedAssetStutas ststus) {
        this.id = id;
        this.categoryName = categoryName;
        this.itemTaxType = itemTaxType;
        this.unitsofMeasure = unitsofMeasure;
        this.excludeFromPurchases = excludeFromPurchases;
        this.salesAccount = salesAccount;
        this.assetAccount = assetAccount;
        this.depreciationCostAccount = depreciationCostAccount;
        this.depreciationOrDisposalAccount = depreciationOrDisposalAccount;
        this.ststus = ststus;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ItemTaxTypes getItemTaxType() {
        return itemTaxType;
    }

    public void setItemTaxType(ItemTaxTypes itemTaxType) {
        this.itemTaxType = itemTaxType;
    }

    public Units getUnitsofMeasure() {
        return unitsofMeasure;
    }

    public void setUnitsofMeasure(Units unitsofMeasure) {
        this.unitsofMeasure = unitsofMeasure;
    }

    public YesNo getExcludeFromPurchases() {
        return excludeFromPurchases;
    }

    public void setExcludeFromPurchases(YesNo excludeFromPurchases) {
        this.excludeFromPurchases = excludeFromPurchases;
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
        return depreciationOrDisposalAccount;
    }

    public void setDepreciationOrDisposalAccount(GlAccounts depreciationOrDisposalAccount) {
        this.depreciationOrDisposalAccount = depreciationOrDisposalAccount;
    }

    public FixedAssetStutas getStstus() {
        return ststus;
    }

    public void setStstus(FixedAssetStutas ststus) {
        this.ststus = ststus;
    }
}
