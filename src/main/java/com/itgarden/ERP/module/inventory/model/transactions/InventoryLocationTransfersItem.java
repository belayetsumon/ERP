/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.model.transactions;

import com.itgarden.ERP.module.inventory.model.settings.Items;
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
public class InventoryLocationTransfersItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    InventoryLocationTransfers inventoryLocationTransfers;

    @ManyToOne(optional = false)
    private Items itemCode;

    private BigDecimal quantity;

    private String unit;

    private BigDecimal price;

    private BigDecimal itemTotal;

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

     public InventoryLocationTransfersItem(long id, InventoryLocationTransfers inventoryLocationTransfers, Items itemCode, BigDecimal quantity, String unit, BigDecimal price, BigDecimal itemTotal, String createdBy, LocalDateTime created, String modifiedBy, LocalDateTime modified) {
        this.id = id;
        this.inventoryLocationTransfers = inventoryLocationTransfers;
        this.itemCode = itemCode;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.itemTotal = itemTotal;
        this.createdBy = createdBy;
        this.created = created;
        this.modifiedBy = modifiedBy;
        this.modified = modified;
    }

    public InventoryLocationTransfersItem() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public InventoryLocationTransfers getInventoryLocationTransfers() {
        return inventoryLocationTransfers;
    }

    public void setInventoryLocationTransfers(InventoryLocationTransfers inventoryLocationTransfers) {
        this.inventoryLocationTransfers = inventoryLocationTransfers;
    }

    public Items getItemCode() {
        return itemCode;
    }

    public void setItemCode(Items itemCode) {
        this.itemCode = itemCode;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(BigDecimal itemTotal) {
        this.itemTotal = itemTotal;
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
