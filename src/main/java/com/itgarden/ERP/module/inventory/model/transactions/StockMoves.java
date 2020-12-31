/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.model.transactions;

import com.itgarden.ERP.module.inventory.model.settings.InventoryLocations;
import com.itgarden.ERP.module.inventory.model.settings.Items;
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
public class StockMoves {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    
    public int trans_no;

    @ManyToOne(optional = false)
    public Items stock_id;

    @ManyToOne(optional = false)
    public TransactionsType transType;

    @ManyToOne(optional = false)
    public InventoryLocations inventoryLocations;

    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = " Quotation date cannot be blank.")
    private Date tranDate;

    private BigDecimal price;

    public String reference;

    @NotNull(message = "Quontity cannot be blank.")
    private BigDecimal qty;

    private BigDecimal standardCost;
    
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

    public StockMoves() {
    }

    public StockMoves(Long id, int trans_no, Items stock_id, TransactionsType transType, InventoryLocations inventoryLocations, Date tranDate, BigDecimal price, String reference, BigDecimal qty, BigDecimal standardCost, String createdBy, LocalDateTime created, String modifiedBy, LocalDateTime modified) {
        this.id = id;
        this.trans_no = trans_no;
        this.stock_id = stock_id;
        this.transType = transType;
        this.inventoryLocations = inventoryLocations;
        this.tranDate = tranDate;
        this.price = price;
        this.reference = reference;
        this.qty = qty;
        this.standardCost = standardCost;
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

    public int getTrans_no() {
        return trans_no;
    }

    public void setTrans_no(int trans_no) {
        this.trans_no = trans_no;
    }

    public Items getStock_id() {
        return stock_id;
    }

    public void setStock_id(Items stock_id) {
        this.stock_id = stock_id;
    }

    public TransactionsType getTransType() {
        return transType;
    }

    public void setTransType(TransactionsType transType) {
        this.transType = transType;
    }

    public InventoryLocations getInventoryLocations() {
        return inventoryLocations;
    }

    public void setInventoryLocations(InventoryLocations inventoryLocations) {
        this.inventoryLocations = inventoryLocations;
    }

    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getStandardCost() {
        return standardCost;
    }

    public void setStandardCost(BigDecimal standardCost) {
        this.standardCost = standardCost;
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
