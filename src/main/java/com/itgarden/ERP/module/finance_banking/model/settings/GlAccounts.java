/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.finance_banking.model.settings;

import com.itgarden.ERP.module.finance_banking.model.enumvalue.AccountStatus;
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

public class GlAccounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "This field cannot be blank.")
    int accountCode;
    @NotEmpty(message = "This field cannot be blank.")
    String accountName;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccountGroups accountGroup;
    String accountTags;
    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    AccountStatus accountStatus;

    public GlAccounts() {
    }

    public GlAccounts(Long id, int accountCode, String accountName, GlAccountGroups accountGroup, String accountTags, AccountStatus accountStatus) {
        this.id = id;
        this.accountCode = accountCode;
        this.accountName = accountName;
        this.accountGroup = accountGroup;
        this.accountTags = accountTags;
        this.accountStatus = accountStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(int accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public GlAccountGroups getAccountGroup() {
        return accountGroup;
    }

    public void setAccountGroup(GlAccountGroups accountGroup) {
        this.accountGroup = accountGroup;
    }

    public String getAccountTags() {
        return accountTags;
    }

    public void setAccountTags(String accountTags) {
        this.accountTags = accountTags;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
    
}
