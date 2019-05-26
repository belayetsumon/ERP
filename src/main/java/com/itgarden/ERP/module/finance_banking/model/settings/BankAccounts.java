/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.finance_banking.model.settings;

import com.itgarden.ERP.module.finance_banking.model.enumvalue.AccountType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Entity
public class BankAccounts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "This field cannot be blank.")
    String bankAccountName;
    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    AccountType accountType;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts bankAccountGLCode;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts bankChargesAccount;
    @NotEmpty(message = "This field cannot be blank.")
    String bankName;
    @NotEmpty(message = "This field cannot be blank.")
    String bankAccountNumber;
    String bankAddress;
    @Lob
    String remark;

    public BankAccounts() {
    }

    public BankAccounts(Long id, String bankAccountName, AccountType accountType, GlAccounts bankAccountGLCode, GlAccounts bankChargesAccount, String bankName, String bankAccountNumber, String bankAddress, String remark) {
        this.id = id;
        this.bankAccountName = bankAccountName;
        this.accountType = accountType;
        this.bankAccountGLCode = bankAccountGLCode;
        this.bankChargesAccount = bankChargesAccount;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.bankAddress = bankAddress;
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public GlAccounts getBankAccountGLCode() {
        return bankAccountGLCode;
    }

    public void setBankAccountGLCode(GlAccounts bankAccountGLCode) {
        this.bankAccountGLCode = bankAccountGLCode;
    }

    public GlAccounts getBankChargesAccount() {
        return bankChargesAccount;
    }

    public void setBankChargesAccount(GlAccounts bankChargesAccount) {
        this.bankChargesAccount = bankChargesAccount;
    }

   
   

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
