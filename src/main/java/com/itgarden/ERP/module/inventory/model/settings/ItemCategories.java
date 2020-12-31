/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.model.settings;

import com.itgarden.ERP.module.finance_banking.model.settings.GlAccounts;
import com.itgarden.ERP.module.inventory.model.enumvalue.ItemType;
import com.itgarden.ERP.module.inventory.model.enumvalue.Itemstatus;
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
public class ItemCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "This field cannot be blank.")
    String categoryName;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    ItemTaxTypes itemTaxType;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    ItemType itemType;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    Units unitsofMeasure;

    @Enumerated(EnumType.STRING)
    YesNo excludefromSales;

    @Enumerated(EnumType.STRING)
    YesNo excludeFromPurchases;

    /// GL Accounts///
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts salesAccount;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts inventoryAccount;

    @ManyToOne
    GlAccounts cogsAccount;
    @NotNull(message = "This field cannot be blank.")

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts inventoryadjustmentsAccount;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts workInProgressAccount;

    //  Others //
    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    Itemstatus status;

    public ItemCategories() {
    }

    public ItemCategories(Long id, String categoryName, ItemTaxTypes itemTaxType, ItemType itemType, Units unitsofMeasure, YesNo excludefromSales, YesNo excludeFromPurchases, GlAccounts salesAccount, GlAccounts inventoryAccount, GlAccounts cogsAccount, GlAccounts inventoryadjustmentsAccount, GlAccounts workInProgressAccount, Itemstatus status) {
        this.id = id;
        this.categoryName = categoryName;
        this.itemTaxType = itemTaxType;
        this.itemType = itemType;
        this.unitsofMeasure = unitsofMeasure;
        this.excludefromSales = excludefromSales;
        this.excludeFromPurchases = excludeFromPurchases;
        this.salesAccount = salesAccount;
        this.inventoryAccount = inventoryAccount;
        this.cogsAccount = cogsAccount;
        this.inventoryadjustmentsAccount = inventoryadjustmentsAccount;
        this.workInProgressAccount = workInProgressAccount;
        this.status = status;
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

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Units getUnitsofMeasure() {
        return unitsofMeasure;
    }

    public void setUnitsofMeasure(Units unitsofMeasure) {
        this.unitsofMeasure = unitsofMeasure;
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

    public GlAccounts getSalesAccount() {
        return salesAccount;
    }

    public void setSalesAccount(GlAccounts salesAccount) {
        this.salesAccount = salesAccount;
    }

    public GlAccounts getInventoryAccount() {
        return inventoryAccount;
    }

    public void setInventoryAccount(GlAccounts inventoryAccount) {
        this.inventoryAccount = inventoryAccount;
    }

    public GlAccounts getCogsAccount() {
        return cogsAccount;
    }

    public void setCogsAccount(GlAccounts cogsAccount) {
        this.cogsAccount = cogsAccount;
    }

    public GlAccounts getInventoryadjustmentsAccount() {
        return inventoryadjustmentsAccount;
    }

    public void setInventoryadjustmentsAccount(GlAccounts inventoryadjustmentsAccount) {
        this.inventoryadjustmentsAccount = inventoryadjustmentsAccount;
    }

    public GlAccounts getWorkInProgressAccount() {
        return workInProgressAccount;
    }

    public void setWorkInProgressAccount(GlAccounts workInProgressAccount) {
        this.workInProgressAccount = workInProgressAccount;
    }

    public Itemstatus getStatus() {
        return status;
    }

    public void setStatus(Itemstatus status) {
        this.status = status;
    }
}
