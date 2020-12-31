/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.model.settings;

import com.itgarden.ERP.module.inventory.model.settings.InventoryLocations;
import com.itgarden.ERP.module.sales.model.enumvalue.SalesTypeName;
import com.itgarden.ERP.module.settings.model.company_setup.TaxGroups;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name and Address //
    @NotEmpty(message = "Customer name cannot be blank.")
    public String customerName;

    public String customerShortName;
    public String address;
    public String gstNo;

    @NotNull(message = "Sales type cannot be blank.")
    @Enumerated(EnumType.STRING)
    public SalesTypeName salesTypeOrPriceList;

    ///contact///
    @NotEmpty(message = "Phone cannot be blank.")
    public String contactPersonsName;

    @NotEmpty(message = "Phone cannot be blank.")
    public String phone;
    @NotEmpty(message = "Mobile cannot be blank.")
    public String mobile;

    @Email
    public String email;
    public String salesPerson;
    public String bankAccountNumber;

    @Lob
    public String mailingAddress;

    @Lob
    public String billingAddress;

    @Lob
    public String deliveryAddress;

    //Sales//
    public double discountPercent;

    public double promptPaymentDiscountPercent;

    public double creditLimit;

    public String paymentTerms;

    @NotNull(message = "Credit Status cannot be blank.")
    @ManyToOne
    public CreditStatusSetup creditStatus;

    public String generalNotes;

    // Branch//
    @NotNull(message = "Inventory location cannot be blank.")
    @ManyToOne
    public InventoryLocations defaultInventoryLocation;

    @NotNull(message = "Sales area/ branch  cannot be blank.")
    @ManyToOne
    public SalesAreas salesArea;

    @NotNull(message = "Tax Group cannot be blank.")
    @ManyToOne
    public TaxGroups taxGroup;

    // Others //
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
    public Customers() {
    }

    public Customers(Long id, String customerName, String customerShortName, String address, String gstNo, SalesTypeName salesTypeOrPriceList, String contactPersonsName, String phone, String mobile, String email, String salesPerson, String bankAccountNumber, String mailingAddress, String billingAddress, String deliveryAddress, double discountPercent, double promptPaymentDiscountPercent, double creditLimit, String paymentTerms, CreditStatusSetup creditStatus, String generalNotes, InventoryLocations defaultInventoryLocation, SalesAreas salesArea, TaxGroups taxGroup, String createdBy, LocalDateTime created, String modifiedBy, LocalDateTime modified) {
        this.id = id;
        this.customerName = customerName;
        this.customerShortName = customerShortName;
        this.address = address;
        this.gstNo = gstNo;
        this.salesTypeOrPriceList = salesTypeOrPriceList;
        this.contactPersonsName = contactPersonsName;
        this.phone = phone;
        this.mobile = mobile;
        this.email = email;
        this.salesPerson = salesPerson;
        this.bankAccountNumber = bankAccountNumber;
        this.mailingAddress = mailingAddress;
        this.billingAddress = billingAddress;
        this.deliveryAddress = deliveryAddress;
        this.discountPercent = discountPercent;
        this.promptPaymentDiscountPercent = promptPaymentDiscountPercent;
        this.creditLimit = creditLimit;
        this.paymentTerms = paymentTerms;
        this.creditStatus = creditStatus;
        this.generalNotes = generalNotes;
        this.defaultInventoryLocation = defaultInventoryLocation;
        this.salesArea = salesArea;
        this.taxGroup = taxGroup;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerShortName() {
        return customerShortName;
    }

    public void setCustomerShortName(String customerShortName) {
        this.customerShortName = customerShortName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public SalesTypeName getSalesTypeOrPriceList() {
        return salesTypeOrPriceList;
    }

    public void setSalesTypeOrPriceList(SalesTypeName salesTypeOrPriceList) {
        this.salesTypeOrPriceList = salesTypeOrPriceList;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(String salesPerson) {
        this.salesPerson = salesPerson;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getPromptPaymentDiscountPercent() {
        return promptPaymentDiscountPercent;
    }

    public void setPromptPaymentDiscountPercent(double promptPaymentDiscountPercent) {
        this.promptPaymentDiscountPercent = promptPaymentDiscountPercent;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public CreditStatusSetup getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(CreditStatusSetup creditStatus) {
        this.creditStatus = creditStatus;
    }

    public String getGeneralNotes() {
        return generalNotes;
    }

    public void setGeneralNotes(String generalNotes) {
        this.generalNotes = generalNotes;
    }

    public InventoryLocations getDefaultInventoryLocation() {
        return defaultInventoryLocation;
    }

    public void setDefaultInventoryLocation(InventoryLocations defaultInventoryLocation) {
        this.defaultInventoryLocation = defaultInventoryLocation;
    }

    public SalesAreas getSalesArea() {
        return salesArea;
    }

    public void setSalesArea(SalesAreas salesArea) {
        this.salesArea = salesArea;
    }

    public TaxGroups getTaxGroup() {
        return taxGroup;
    }

    public void setTaxGroup(TaxGroups taxGroup) {
        this.taxGroup = taxGroup;
    }

    public String getContactPersonsName() {
        return contactPersonsName;
    }

    public void setContactPersonsName(String contactPersonsName) {
        this.contactPersonsName = contactPersonsName;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
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
