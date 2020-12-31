/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.finance_banking.model.transactions;

import com.itgarden.ERP.module.finance_banking.model.settings.BankAccounts;
import com.itgarden.ERP.module.sales.model.settings.Customers;
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
public class Deposits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dipositId;

    @ManyToOne(optional = true)
    private TransactionsType transactionsType;

    @NotBlank
    private String payTo;

    @ManyToOne(optional = true)
    private Customers customers;

    @NotBlank
    private String dipositReference;

    @Column(nullable = false)
    @NotNull(message = "Payment date cannot be blank.")
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dipositDate;

    @ManyToOne(optional = true)
    private BankAccounts bankAccounts;

    private BigDecimal bankCharge;

    private BigDecimal alloc;

    private BigDecimal discount;

    @NotNull(message = "Amount cannot be blank.")
    private BigDecimal totalAmount;

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
    public Deposits(Long dipositId, TransactionsType transactionsType, String payTo, Customers customers, String dipositReference, Date dipositDate, BankAccounts bankAccounts, BigDecimal bankCharge, BigDecimal alloc, BigDecimal discount, BigDecimal totalAmount, String memo, String createdBy, LocalDateTime created, String modifiedBy, LocalDateTime modified) {
        this.dipositId = dipositId;
        this.transactionsType = transactionsType;
        this.payTo = payTo;
        this.customers = customers;
        this.dipositReference = dipositReference;
        this.dipositDate = dipositDate;
        this.bankAccounts = bankAccounts;
        this.bankCharge = bankCharge;
        this.alloc = alloc;
        this.discount = discount;
        this.totalAmount = totalAmount;
        this.memo = memo;
        this.createdBy = createdBy;
        this.created = created;
        this.modifiedBy = modifiedBy;
        this.modified = modified;
    }

    public Deposits() {
    }

    public Long getDipositId() {
        return dipositId;
    }

    public void setDipositId(Long dipositId) {
        this.dipositId = dipositId;
    }

    public TransactionsType getTransactionsType() {
        return transactionsType;
    }

    public void setTransactionsType(TransactionsType transactionsType) {
        this.transactionsType = transactionsType;
    }

    public String getPayTo() {
        return payTo;
    }

    public void setPayTo(String payTo) {
        this.payTo = payTo;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public String getDipositReference() {
        return dipositReference;
    }

    public void setDipositReference(String dipositReference) {
        this.dipositReference = dipositReference;
    }

    public Date getDipositDate() {
        return dipositDate;
    }

    public void setDipositDate(Date dipositDate) {
        this.dipositDate = dipositDate;
    }

    public BankAccounts getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(BankAccounts bankAccounts) {
        this.bankAccounts = bankAccounts;
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
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

}
