/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.model.transactions;

import com.itgarden.ERP.module.inventory.model.settings.InventoryLocations;
import com.itgarden.ERP.module.settings.model.company_setup.TransactionsType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class InventoryLocationTransfers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(optional = false)
    public TransactionsType transType;

    @ManyToOne(optional = false)
    public InventoryLocations fromLocation;

    @ManyToOne(optional = false)
    public InventoryLocations toLocation;

    
    public String iltReference;

    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = " Inventory adjustment date cannot be blank.")
    private Date iltDate;

    private BigDecimal total;

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

    @OneToMany(mappedBy = "inventoryLocationTransfers", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventoryLocationTransfersItem> inventoryLocationTransfersItem;

    public InventoryLocationTransfers(Long id, TransactionsType transType, InventoryLocations fromLocation, InventoryLocations toLocation, String iltReference, Date iltDate, BigDecimal total, String memo, String createdBy, LocalDateTime created, String modifiedBy, LocalDateTime modified, List<InventoryLocationTransfersItem> inventoryLocationTransfersItem) {
        this.id = id;
        this.transType = transType;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.iltReference = iltReference;
        this.iltDate = iltDate;
        this.total = total;
        this.memo = memo;
        this.createdBy = createdBy;
        this.created = created;
        this.modifiedBy = modifiedBy;
        this.modified = modified;
        this.inventoryLocationTransfersItem = inventoryLocationTransfersItem;
    }

    

    public InventoryLocationTransfers() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionsType getTransType() {
        return transType;
    }

    public void setTransType(TransactionsType transType) {
        this.transType = transType;
    }

    public InventoryLocations getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(InventoryLocations fromLocation) {
        this.fromLocation = fromLocation;
    }

    public InventoryLocations getToLocation() {
        return toLocation;
    }

    public void setToLocation(InventoryLocations toLocation) {
        this.toLocation = toLocation;
    }

    public String getIltReference() {
        return iltReference;
    }

    public void setIltReference(String iltReference) {
        this.iltReference = iltReference;
    }

    public Date getIltDate() {
        return iltDate;
    }

    public void setIltDate(Date iltDate) {
        this.iltDate = iltDate;
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<InventoryLocationTransfersItem> getInventoryLocationTransfersItem() {
        return inventoryLocationTransfersItem;
    }

    public void setInventoryLocationTransfersItem(List<InventoryLocationTransfersItem> inventoryLocationTransfersItem) {
        this.inventoryLocationTransfersItem = inventoryLocationTransfersItem;
    }

}
