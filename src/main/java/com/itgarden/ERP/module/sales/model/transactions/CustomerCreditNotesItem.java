/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.model.transactions;

import com.itgarden.ERP.module.inventory.model.settings.Items;
import com.itgarden.ERP.module.settings.model.company_setup.TransactionsType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class CustomerCreditNotesItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    CustomerCreditNotes customerCreditNotes;

    @ManyToOne(optional = true)
    private TransactionsType transactionsType;

    @ManyToOne(optional = false)
    Items itemId;

    private String description;

    private BigDecimal unitPrice;

    private BigDecimal quantity;

    private BigDecimal discount_percent;

    private BigDecimal discountTotal;

    private BigDecimal unitTax;

    private BigDecimal totalTax;

    private BigDecimal itemTotal;

    private BigDecimal standard_cost;

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

    public CustomerCreditNotesItem() {
    }

    public CustomerCreditNotesItem(Long id, CustomerCreditNotes customerCreditNotes, TransactionsType transactionsType, Items itemId, String description, BigDecimal unitPrice, BigDecimal quantity, BigDecimal discount_percent, BigDecimal discountTotal, BigDecimal unitTax, BigDecimal totalTax, BigDecimal itemTotal, BigDecimal standard_cost, String createdBy, LocalDateTime created, String modifiedBy, LocalDateTime modified) {
        this.id = id;
        this.customerCreditNotes = customerCreditNotes;
        this.transactionsType = transactionsType;
        this.itemId = itemId;
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount_percent = discount_percent;
        this.discountTotal = discountTotal;
        this.unitTax = unitTax;
        this.totalTax = totalTax;
        this.itemTotal = itemTotal;
        this.standard_cost = standard_cost;
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

    public CustomerCreditNotes getCustomerCreditNotes() {
        return customerCreditNotes;
    }

    public void setCustomerCreditNotes(CustomerCreditNotes customerCreditNotes) {
        this.customerCreditNotes = customerCreditNotes;
    }

    public TransactionsType getTransactionsType() {
        return transactionsType;
    }

    public void setTransactionsType(TransactionsType transactionsType) {
        this.transactionsType = transactionsType;
    }

    public Items getItemId() {
        return itemId;
    }

    public void setItemId(Items itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(BigDecimal discount_percent) {
        this.discount_percent = discount_percent;
    }

    public BigDecimal getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(BigDecimal discountTotal) {
        this.discountTotal = discountTotal;
    }

    public BigDecimal getUnitTax() {
        return unitTax;
    }

    public void setUnitTax(BigDecimal unitTax) {
        this.unitTax = unitTax;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(BigDecimal itemTotal) {
        this.itemTotal = itemTotal;
    }

    public BigDecimal getStandard_cost() {
        return standard_cost;
    }

    public void setStandard_cost(BigDecimal standard_cost) {
        this.standard_cost = standard_cost;
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
