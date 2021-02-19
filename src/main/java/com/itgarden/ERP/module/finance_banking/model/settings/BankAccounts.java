/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.finance_banking.model.settings;

import com.itgarden.ERP.module.finance_banking.model.enumvalue.AccountType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author User
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class BankAccounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "This field cannot be blank.")
    String bankAccountName;
    @NotNull(message = "This field cannot be blank.")
//
//    @NotEmpty(message = "Slug cannot be blank.")
//    public String slug;

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

    private BigDecimal bankcharge;
    @Lob
    String remark;

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

    /// End Audit //// 
   
     public BankAccounts() {
    }

    public BankAccounts(Long id, String bankAccountName, AccountType accountType, GlAccounts bankAccountGLCode, GlAccounts bankChargesAccount, String bankName, String bankAccountNumber, String bankAddress, BigDecimal bankcharge, String remark, String createdBy, LocalDateTime created, String modifiedBy, LocalDateTime modified) {
        this.id = id;
        this.bankAccountName = bankAccountName;
        this.accountType = accountType;
        this.bankAccountGLCode = bankAccountGLCode;
        this.bankChargesAccount = bankChargesAccount;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.bankAddress = bankAddress;
        this.bankcharge = bankcharge;
        this.remark = remark;
        this.createdBy = createdBy;
        this.created = created;
        this.modifiedBy = modifiedBy;
        this.modified = modified;
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

    public BigDecimal getBankcharge() {
        return bankcharge;
    }

    public void setBankcharge(BigDecimal bankcharge) {
        this.bankcharge = bankcharge;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

   
}
