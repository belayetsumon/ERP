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
public class SalesDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryNo;

    @ManyToOne(optional = true)
    private TransactionsType transactionsType;

    @ManyToOne(optional = true)
    private Customers customers;

    @ManyToOne(optional = true)
    private CustomerBranches branchCode;

    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date deliveryDate;

    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date invoiceBefore;

    private String priceType;

    private String diReference;

    private String comments;

    @ManyToOne(optional = false)
    @NotNull(message = " Payment terms cannot be blank.")
    private PaymentTerms paymentTerms;

    private boolean template;

    @OneToOne(optional = true)
    private SalesOrder orderNo;
    
    @OneToOne(optional = true)
    private SalesInvoice salesInvoice;

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

    private String dimension_id;

    private boolean tax_included;

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
    @OneToMany(mappedBy = "salesDelivery", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalesDeliveryItem> salesDeliveryItem;

    public SalesDelivery() {
    }

    public SalesDelivery(Long deliveryNo, TransactionsType transactionsType, Customers customers, CustomerBranches branchCode, Date deliveryDate, Date invoiceBefore, String priceType, String diReference, String comments, PaymentTerms paymentTerms, boolean template, SalesOrder orderNo, BigDecimal ov_amount, BigDecimal ov_gst, BigDecimal ov_freight, BigDecimal ov_freight_tax, BigDecimal ov_discount, BigDecimal alloc, BigDecimal prepAmount, BigDecimal totalDiscount, BigDecimal totalVat, BigDecimal grandTotal, String deliveryAddress, String contactPhone, String contactPersonsName, String contactEmail, String deliverTo, ShippingCompany shipVia, BigDecimal freightCost, InventoryLocations fromStkLoc, String dimension_id, boolean tax_included, String createdBy, LocalDateTime created, String modifiedBy, LocalDateTime modified, List<SalesDeliveryItem> salesDeliveryItem) {
        this.deliveryNo = deliveryNo;
        this.transactionsType = transactionsType;
        this.customers = customers;
        this.branchCode = branchCode;
        this.deliveryDate = deliveryDate;
        this.invoiceBefore = invoiceBefore;
        this.priceType = priceType;
        this.diReference = diReference;
        this.comments = comments;
        this.paymentTerms = paymentTerms;
        this.template = template;
        this.orderNo = orderNo;
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
        this.deliveryAddress = deliveryAddress;
        this.contactPhone = contactPhone;
        this.contactPersonsName = contactPersonsName;
        this.contactEmail = contactEmail;
        this.deliverTo = deliverTo;
        this.shipVia = shipVia;
        this.freightCost = freightCost;
        this.fromStkLoc = fromStkLoc;
        this.dimension_id = dimension_id;
        this.tax_included = tax_included;
        this.createdBy = createdBy;
        this.created = created;
        this.modifiedBy = modifiedBy;
        this.modified = modified;
        this.salesDeliveryItem = salesDeliveryItem;
    }

    public Long getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(Long deliveryNo) {
        this.deliveryNo = deliveryNo;
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

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getInvoiceBefore() {
        return invoiceBefore;
    }

    public void setInvoiceBefore(Date invoiceBefore) {
        this.invoiceBefore = invoiceBefore;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getDiReference() {
        return diReference;
    }

    public void setDiReference(String diReference) {
        this.diReference = diReference;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public PaymentTerms getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(PaymentTerms paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public boolean isTemplate() {
        return template;
    }

    public void setTemplate(boolean template) {
        this.template = template;
    }

    public SalesOrder getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(SalesOrder orderNo) {
        this.orderNo = orderNo;
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

    public String getDimension_id() {
        return dimension_id;
    }

    public void setDimension_id(String dimension_id) {
        this.dimension_id = dimension_id;
    }

    public boolean isTax_included() {
        return tax_included;
    }

    public void setTax_included(boolean tax_included) {
        this.tax_included = tax_included;
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

    public List<SalesDeliveryItem> getSalesDeliveryItem() {
        return salesDeliveryItem;
    }

    public void setSalesDeliveryItem(List<SalesDeliveryItem> salesDeliveryItem) {
        this.salesDeliveryItem = salesDeliveryItem;
    }

}