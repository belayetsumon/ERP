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
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
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
public class SalesQuotationEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qtno;

    @NotNull(message = "Customers cannot be blank.")
    @ManyToOne(optional = true)
    private Customers customers;

    @ManyToOne(optional = true)
    private Customers customerRef;

    @ManyToOne(optional = false)
    private TransactionsType transactionsType;
    @NotEmpty(message = "Quotation Reference  Code cannot be blank.")

    @Column(unique = true)
    private String qtReference;

    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = " Quotation date cannot be blank.")
    private Date qtDate;

    private String priceType;

    //@NotNull(message = "Branch Code cannot be blank.")
    @ManyToOne(optional = true)
    private CustomerBranches branchCode;

    @ManyToOne(optional = false)
    @NotNull(message = " Payment terms cannot be blank.")
    private PaymentTerms paymentTerms;

    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date qtDueDate;

    private String comments;

    @NotNull(message = "Total discount cannot be blank.")
    private BigDecimal totalDiscount;

    @NotNull(message = "Total vat cannot be blank.")
    private BigDecimal totalVat;

    @NotNull(message = "Total cannot be blank.")
    private BigDecimal total;

    private BigDecimal prepAmount;

    private BigDecimal alloc;

    // Order Delivery Details//
    //Delivery Address
    @Lob
    private String deliveryAddress;

    //Delivery contact phone
    private String contactPhone;

    private String contactPersonsName;

    //Delivery contact email
    private String contactEmail;

    //Delivery contact name
    @Lob
    private String deliverTo;

    @ManyToOne(optional = true)
    
    private ShippingCompany shipVia;
    
    private BigDecimal freightCost;

    @ManyToOne(optional = true)
    @NotNull(message = "Inventory location cannot be blank.")
    private InventoryLocations fromStkLoc;

    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = " Required delivery date cannot be blank.")
    private Date deliveryDate;

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

    @OneToMany(mappedBy = "salesQuotationEntry", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalesQuotationItem> salesQuotationItem;

    /// End Audit //// 
    public SalesQuotationEntry() {
    }

    public SalesQuotationEntry(Long qtno, Customers customers, Customers customerRef, TransactionsType transactionsType, String qtReference, Date qtDate, String priceType, CustomerBranches branchCode, PaymentTerms paymentTerms, Date qtDueDate, String comments, BigDecimal total, BigDecimal prepAmount, BigDecimal alloc, String deliveryAddress, String contactPhone, String contactPersonsName, String contactEmail, String deliverTo, ShippingCompany shipVia, BigDecimal freightCost, InventoryLocations fromStkLoc, Date deliveryDate, String createdBy, LocalDateTime created, String modifiedBy, LocalDateTime modified, List<SalesQuotationItem> salesQuotationItem) {
        this.qtno = qtno;
        this.customers = customers;
        this.customerRef = customerRef;
        this.transactionsType = transactionsType;
        this.qtReference = qtReference;
        this.qtDate = qtDate;
        this.priceType = priceType;
        this.branchCode = branchCode;
        this.paymentTerms = paymentTerms;
        this.qtDueDate = qtDueDate;
        this.comments = comments;
        this.total = total;
        this.prepAmount = prepAmount;
        this.alloc = alloc;
        this.deliveryAddress = deliveryAddress;
        this.contactPhone = contactPhone;
        this.contactPersonsName = contactPersonsName;
        this.contactEmail = contactEmail;
        this.deliverTo = deliverTo;
        this.shipVia = shipVia;
        this.freightCost = freightCost;
        this.fromStkLoc = fromStkLoc;
        this.deliveryDate = deliveryDate;
        this.createdBy = createdBy;
        this.created = created;
        this.modifiedBy = modifiedBy;
        this.modified = modified;
        this.salesQuotationItem = salesQuotationItem;
    }

    public Long getQtno() {
        return qtno;
    }

    public void setQtno(Long qtno) {
        this.qtno = qtno;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public Customers getCustomerRef() {
        return customerRef;
    }

    public void setCustomerRef(Customers customerRef) {
        this.customerRef = customerRef;
    }

    public TransactionsType getTransactionsType() {
        return transactionsType;
    }

    public void setTransactionsType(TransactionsType transactionsType) {
        this.transactionsType = transactionsType;
    }

    public String getQtReference() {
        return qtReference;
    }

    public void setQtReference(String qtReference) {
        this.qtReference = qtReference;
    }

    public Date getQtDate() {
        return qtDate;
    }

    public void setQtDate(Date qtDate) {
        this.qtDate = qtDate;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public CustomerBranches getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(CustomerBranches branchCode) {
        this.branchCode = branchCode;
    }

    public PaymentTerms getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(PaymentTerms paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public Date getQtDueDate() {
        return qtDueDate;
    }

    public void setQtDueDate(Date qtDueDate) {
        this.qtDueDate = qtDueDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getPrepAmount() {
        return prepAmount;
    }

    public void setPrepAmount(BigDecimal prepAmount) {
        this.prepAmount = prepAmount;
    }

    public BigDecimal getAlloc() {
        return alloc;
    }

    public void setAlloc(BigDecimal alloc) {
        this.alloc = alloc;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactPersonsName() {
        return contactPersonsName;
    }

    public void setContactPersonsName(String contactPersonsName) {
        this.contactPersonsName = contactPersonsName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getDeliverTo() {
        return deliverTo;
    }

    public void setDeliverTo(String deliverTo) {
        this.deliverTo = deliverTo;
    }

    public ShippingCompany getShipVia() {
        return shipVia;
    }

    public void setShipVia(ShippingCompany shipVia) {
        this.shipVia = shipVia;
    }

    public BigDecimal getFreightCost() {
        return freightCost;
    }

    public void setFreightCost(BigDecimal freightCost) {
        this.freightCost = freightCost;
    }

    public InventoryLocations getFromStkLoc() {
        return fromStkLoc;
    }

    public void setFromStkLoc(InventoryLocations fromStkLoc) {
        this.fromStkLoc = fromStkLoc;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
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

    public List<SalesQuotationItem> getSalesQuotationItem() {
        return salesQuotationItem;
    }

    public void setSalesQuotationItem(List<SalesQuotationItem> salesQuotationItem) {
        this.salesQuotationItem = salesQuotationItem;
    }

}
