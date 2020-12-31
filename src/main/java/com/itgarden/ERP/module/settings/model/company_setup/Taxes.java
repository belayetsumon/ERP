/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.settings.model.company_setup;

import com.itgarden.ERP.module.finance_banking.model.settings.GlAccounts;
import javax.persistence.Entity;
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
public class Taxes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "This field cannot be blank.")
    String description;

    String defaultRate;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts salesGlAccount;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts purchasingGlAccount;

    public Taxes() {
    }

    public Taxes(Long id, String description, String defaultRate, GlAccounts salesGlAccount, GlAccounts purchasingGlAccount) {
        this.id = id;
        this.description = description;
        this.defaultRate = defaultRate;
        this.salesGlAccount = salesGlAccount;
        this.purchasingGlAccount = purchasingGlAccount;
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

    public String getDefaultRate() {
        return defaultRate;
    }

    public void setDefaultRate(String defaultRate) {
        this.defaultRate = defaultRate;
    }

    public GlAccounts getSalesGlAccount() {
        return salesGlAccount;
    }

    public void setSalesGlAccount(GlAccounts salesGlAccount) {
        this.salesGlAccount = salesGlAccount;
    }

    public GlAccounts getPurchasingGlAccount() {
        return purchasingGlAccount;
    }

    public void setPurchasingGlAccount(GlAccounts purchasingGlAccount) {
        this.purchasingGlAccount = purchasingGlAccount;
    }

    
}
