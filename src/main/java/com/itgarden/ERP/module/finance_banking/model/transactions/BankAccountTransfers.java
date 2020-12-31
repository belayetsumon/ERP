/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.finance_banking.model.transactions;

import com.itgarden.ERP.module.finance_banking.model.settings.BankAccounts;
import com.itgarden.ERP.module.settings.model.company_setup.TransactionsType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author User
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class BankAccountTransfers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true)
    private TransactionsType transactionsType;

    @NotBlank
    private String batReference;

    @Column(nullable = false)
    @NotNull(message = "Payment date cannot be blank.")
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date transDate;

    @ManyToOne(optional = true)
    private BankAccounts fromBankAccounts;

    @ManyToOne(optional = true)
    private BankAccounts toBankAccounts;

    private BigDecimal bankCharge;

    private BigDecimal alloc;

    private BigDecimal discount;

    @NotNull(message = "Amount cannot be blank.")
    private BigDecimal amount;

    @Lob
    private String memo;

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

    public BankAccountTransfers(Long id, TransactionsType transactionsType, String batReference, Date transDate, BankAccounts fromBankAccounts, BankAccounts toBankAccounts, BigDecimal bankCharge, BigDecimal alloc, BigDecimal discount, BigDecimal amount, String memo, String createdBy, LocalDateTime created, String modifiedBy, LocalDateTime modified) {
        this.id = id;
        this.transactionsType = transactionsType;
        this.batReference = batReference;
        this.transDate = transDate;
        this.fromBankAccounts = fromBankAccounts;
        this.toBankAccounts = toBankAccounts;
        this.bankCharge = bankCharge;
        this.alloc = alloc;
        this.discount = discount;
        this.amount = amount;
        this.memo = memo;
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

    public TransactionsType getTransactionsType() {
        return transactionsType;
    }

    public void setTransactionsType(TransactionsType transactionsType) {
        this.transactionsType = transactionsType;
    }

    public String getBatReference() {
        return batReference;
    }

    public void setBatReference(String batReference) {
        this.batReference = batReference;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public BankAccounts getFromBankAccounts() {
        return fromBankAccounts;
    }

    public void setFromBankAccounts(BankAccounts fromBankAccounts) {
        this.fromBankAccounts = fromBankAccounts;
    }

    public BankAccounts getToBankAccounts() {
        return toBankAccounts;
    }

    public void setToBankAccounts(BankAccounts toBankAccounts) {
        this.toBankAccounts = toBankAccounts;
    }

    public BigDecimal getBankCharge() {
        return bankCharge;
    }

    public void setBankCharge(BigDecimal bankCharge) {
        this.bankCharge = bankCharge;
    }

    public BigDecimal getAlloc() {
        return alloc;
    }

    public void setAlloc(BigDecimal alloc) {
        this.alloc = alloc;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

    public BankAccountTransfers() {
    }

    
}
