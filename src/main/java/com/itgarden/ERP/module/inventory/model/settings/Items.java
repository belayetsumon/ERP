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
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "This field cannot be blank.")
    int itemCode;
    @NotEmpty(message = "This field cannot be blank.")
    String name;
    String description;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    ItemCategories category;
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
    YesNo editabledescription;
    
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
    GlAccounts cogsAccount;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts inventoryAccount;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts InventoryadjustmentsAccount;
    @NotNull(message = "This field cannot be blank.")
    

    //  Others //
    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    Itemstatus itemstatus;

    public Items() {
    }

    public Items(Long id, int itemCode, String name, String description, ItemCategories category, ItemTaxTypes itemTaxType, ItemType itemType, Units unitsofMeasure, YesNo editabledescription, YesNo excludefromSales, YesNo excludeFromPurchases, GlAccounts salesAccount, GlAccounts cogsAccount, GlAccounts inventoryAccount, GlAccounts InventoryadjustmentsAccount, Itemstatus itemstatus) {
        this.id = id;
        this.itemCode = itemCode;
        this.name = name;
        this.description = description;
        this.category = category;
        this.itemTaxType = itemTaxType;
        this.itemType = itemType;
        this.unitsofMeasure = unitsofMeasure;
        this.editabledescription = editabledescription;
        this.excludefromSales = excludefromSales;
        this.excludeFromPurchases = excludeFromPurchases;
        this.salesAccount = salesAccount;
        this.cogsAccount = cogsAccount;
        this.inventoryAccount = inventoryAccount;
        this.InventoryadjustmentsAccount = InventoryadjustmentsAccount;
        this.itemstatus = itemstatus;
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

    public ItemCategories getCategory() {
        return category;
    }

    public void setCategory(ItemCategories category) {
        this.category = category;
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

    public GlAccounts getInventoryadjustmentsAccount() {
        return InventoryadjustmentsAccount;
    }

    public void setInventoryadjustmentsAccount(GlAccounts InventoryadjustmentsAccount) {
        this.InventoryadjustmentsAccount = InventoryadjustmentsAccount;
    }

    public Itemstatus getItemstatus() {
        return itemstatus;
    }

    public void setItemstatus(Itemstatus itemstatus) {
        this.itemstatus = itemstatus;
    }

   
}
