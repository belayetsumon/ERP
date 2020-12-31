/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.finance_banking.model.settings;

import com.itgarden.ERP.module.finance_banking.model.enumvalue.EntryType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author User
 */
@Entity
public class QuickEntries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String description;
    String usages;
    EntryType entryType;
    String baseAmountDescription;
    String defaultBaseAmount;

    public QuickEntries() {
    }

    public QuickEntries(Long id, String description, String usages, EntryType entryType, String baseAmountDescription, String defaultBaseAmount) {
        this.id = id;
        this.description = description;
        this.usages = usages;
        this.entryType = entryType;
        this.baseAmountDescription = baseAmountDescription;
        this.defaultBaseAmount = defaultBaseAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsages() {
        return usages;
    }

    public void setUsages(String usages) {
        this.usages = usages;
    }

    public EntryType getEntryType() {
        return entryType;
    }

    public void setEntryType(EntryType entryType) {
        this.entryType = entryType;
    }

    public String getBaseAmountDescription() {
        return baseAmountDescription;
    }

    public void setBaseAmountDescription(String baseAmountDescription) {
        this.baseAmountDescription = baseAmountDescription;
    }

    public String getDefaultBaseAmount() {
        return defaultBaseAmount;
    }

    public void setDefaultBaseAmount(String defaultBaseAmount) {
        this.defaultBaseAmount = defaultBaseAmount;
    }
    

}
