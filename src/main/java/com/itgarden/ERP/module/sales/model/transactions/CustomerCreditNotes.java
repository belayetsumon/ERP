/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.model.transactions;

import com.itgarden.ERP.module.inventory.model.settings.InventoryLocations;
import com.itgarden.ERP.module.sales.model.settings.CustomerBranches;
import com.itgarden.ERP.module.sales.model.settings.Customers;
import com.itgarden.ERP.module.settings.model.company_setup.TransactionsType;
import com.itgarden.ERP.module.settings.model.miscellaneous.PaymentTerms;
import com.itgarden.ERP.module.settings.model.miscellaneous.ShippingCompany;
import java.io.Serializable;
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
import javax.persistence.OneToOne;
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
public class CustomerCreditNotes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cnno;

    @ManyToOne(optional = true)
    private TransactionsType transactionsType;

    @ManyToOne(optional = true)
    private Customers customers;

    @ManyToOne(optional = true)
    private CustomerBranches branchCode;

    @Column(nullable = false)
    @NotNull(message = "Invoice date cannot be blank.")
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date cnDate;

    @NotNull(message = "Price type cannot be blank.")
    private String priceType;

    private String cnReference;

    private String memo;




    /// Not Understandable 
    private BigDecimal ov_amount;

    private BigDecimal ov_gst;

    private BigDecimal ov_freight;

    private BigDecimal ov_freight_tax;

    private BigDecimal ov_discount;

    ///// >>>>>>>>>>>>>>>>>>>>>>>/////
    private BigDecimal alloc;

    private BigDecimal prepAmount;

    @NotNull(message = "Total discount cannot be blank.")
    private BigDecimal totalDiscount;

    @NotNull(message = "Total vat cannot be blank.")
    private BigDecimal totalVat;

    @NotNull(message = " Grand total cannot be blank.")
    private BigDecimal grandTotal;

    // Order Delivery Details//
    
    //Delivery contact name

    @ManyToOne(optional = true)
    private ShippingCompany shipVia;
    @ManyToOne(optional = true)
    @NotNull(message = "Inventory location cannot be blank.")
    private InventoryLocations fromStkLoc;

    private String dimension_id;

 

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
    @OneToMany(mappedBy = "customerCreditNotes", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerCreditNotesItem> customerCreditNotesItem;

    public CustomerCreditNotes() {
    }

    public CustomerCreditNotes(Long cnno, TransactionsType transactionsType, Customers customers, CustomerBranches branchCode, Date cnDate, String priceType, String cnReference, String memo, BigDecimal ov_amount, BigDecimal ov_gst, BigDecimal ov_freight, BigDecimal ov_freight_tax, BigDecimal ov_discount, BigDecimal alloc, BigDecimal prepAmount, BigDecimal totalDiscount, BigDecimal totalVat, BigDecimal grandTotal, ShippingCompany shipVia, InventoryLocations fromStkLoc, String dimension_id, String createdBy, LocalDateTime created, String modifiedBy, LocalDateTime modified, List<CustomerCreditNotesItem> customerCreditNotesItem) {
        this.cnno = cnno;
        this.transactionsType = transactionsType;
        this.customers = customers;
        this.branchCode = branchCode;
        this.cnDate = cnDate;
        this.priceType = priceType;
        this.cnReference = cnReference;
        this.memo = memo;
        this.ov_amount = ov_amount;
        this.ov_gst = ov_gst;
        this.ov_freight = ov_freight;
        this.ov_freight_tax = ov_freight_tax;
        this.ov_discount = ov_discount;
        this.alloc = alloc;
        this.prepAmount = prepAmount;
        this.totalDiscount = totalDiscount;
        this.totalVat = totalVat;
        this.grandTotal = grandTotal;
        this.shipVia = shipVia;
        this.fromStkLoc = fromStkLoc;
        this.dimension_id = dimension_id;
        this.createdBy = createdBy;
        this.created = created;
        this.modifiedBy = modifiedBy;
        this.modified = modified;
        this.customerCreditNotesItem = customerCreditNotesItem;
    }

    public Long getCnno() {
        return cnno;
    }

    public void setCnno(Long cnno) {
        this.cnno = cnno;
    }

    public TransactionsType getTransactionsType() {
        return transactionsType;
    }

    public void setTransactionsType(TransactionsType transactionsType) {
        this.transactionsType = transactionsType;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public CustomerBranches getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(CustomerBranches branchCode) {
        this.branchCode = branchCode;
    }

    public Date getCnDate() {
        return cnDate;
    }

    public void setCnDate(Date cnDate) {
        this.cnDate = cnDate;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getCnReference() {
        return cnReference;
    }

    public void setCnReference(String cnReference) {
        this.cnReference = cnReference;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public BigDecimal getOv_amount() {
        return ov_amount;
    }

    public void setOv_amount(BigDecimal ov_amount) {
        this.ov_amount = ov_amount;
    }

    public BigDecimal getOv_gst() {
        return ov_gst;
    }

    public void setOv_gst(BigDecimal ov_gst) {
        this.ov_gst = ov_gst;
    }

    public BigDecimal getOv_freight() {
        return ov_freight;
    }

    public void setOv_freight(BigDecimal ov_freight) {
        this.ov_freight = ov_freight;
    }

    public BigDecimal getOv_freight_tax() {
        return ov_freight_tax;
    }

    public void setOv_freight_tax(BigDecimal ov_freight_tax) {
        this.ov_freight_tax = ov_freight_tax;
    }

    public BigDecimal getOv_discount() {
        return ov_discount;
    }

    public void setOv_discount(BigDecimal ov_discount) {
        this.ov_discount = ov_discount;
    }

    public BigDecimal getAlloc() {
        return alloc;
    }

    public void setAlloc(BigDecimal alloc) {
        this.alloc = alloc;
    }

    public BigDecimal getPrepAmount() {
        return prepAmount;
    }

    public void setPrepAmount(BigDecimal prepAmount) {
        this.prepAmount = prepAmount;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public BigDecimal getTotalVat() {
        return totalVat;
    }

    public void setTotalVat(BigDecimal totalVat) {
        this.totalVat = totalVat;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public ShippingCompany getShipVia() {
        return shipVia;
    }

    public void setShipVia(ShippingCompany shipVia) {
        this.shipVia = shipVia;
    }

    public InventoryLocations getFromStkLoc() {
        return fromStkLoc;
    }

    public void setFromStkLoc(InventoryLocations fromStkLoc) {
        this.fromStkLoc = fromStkLoc;
    }

    public String getDimension_id() {
        return dimension_id;
    }

    public void setDimension_id(String dimension_id) {
        this.dimension_id = dimension_id;
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

    public List<CustomerCreditNotesItem> getCustomerCreditNotesItem() {
        return customerCreditNotesItem;
    }

    public void setCustomerCreditNotesItem(List<CustomerCreditNotesItem> customerCreditNotesItem) {
        this.customerCreditNotesItem = customerCreditNotesItem;
    }

    
    
}
