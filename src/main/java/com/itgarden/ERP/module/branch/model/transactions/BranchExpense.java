/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.branch.model.transactions;

import com.itgarden.ERP.module.branch.model.settings.ExpenseType;
import com.itgarden.ERP.module.finance_banking.model.settings.BankAccounts;
import com.itgarden.ERP.module.inventory.model.settings.InventoryLocations;
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
public class BranchExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true)
    private TransactionsType transactionsType;

    @ManyToOne(optional = false)
    private ExpenseType expenseType;

    @ManyToOne(optional = false)
    public InventoryLocations inventoryLocations;

    private String expenseReference;

    @Column(nullable = false)
    @NotNull(message = " Date cannot be blank.")
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date expenseDate;

    @ManyToOne(optional = true)
    private BankAccounts bankAccounts;

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

    public BranchExpense() {
    }

    /// End Audit //// 
    public BranchExpense(Long id, TransactionsType transactionsType, ExpenseType expenseType, InventoryLocations inventoryLocations, String expenseReference, Date expenseDate, BankAccounts bankAccounts, BigDecimal bankCharge, BigDecimal alloc, BigDecimal discount, BigDecimal amount, String memo, String createdBy, LocalDateTime created, String modifiedBy, LocalDateTime modified) {
        this.id = id;
        this.transactionsType = transactionsType;
        this.expenseType = expenseType;
        this.inventoryLocations = inventoryLocations;
        this.expenseReference = expenseReference;
        this.expenseDate = expenseDate;
        this.bankAccounts = bankAccounts;
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

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    public InventoryLocations getInventoryLocations() {
        return inventoryLocations;
    }

    public void setInventoryLocations(InventoryLocations inventoryLocations) {
        this.inventoryLocations = inventoryLocations;
    }

    public String getExpenseReference() {
        return expenseReference;
    }

    public void setExpenseReference(String expenseReference) {
        this.expenseReference = expenseReference;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
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

}
