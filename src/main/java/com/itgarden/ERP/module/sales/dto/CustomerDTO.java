/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.dto;

import com.itgarden.ERP.module.sales.model.enumvalue.SalesTypeName;

/**
 *
 * @author User
 */
public class CustomerDTO {

    private Long id;

    public String customerName;

    public String customerShortName;

    public String address;

    public String gstNo;

    public SalesTypeName salesTypeOrPriceList;

    public String contactPersonsName;

    public String phone;

    public String mobile;

    public String email;

    public String salesPerson;

    public String bankAccountNumber;

    public String mailingAddress;

    public String billingAddress;

    public String deliveryAddress;

    public double discountPercent;

    public double promptPaymentDiscountPercent;

    public double creditLimit;

    public String paymentTerms;

    public Long creditStatus;

    public String creditStatusName;

    public String generalNotes;

    public Long defaultInventoryLocation;

    public String defaultInventoryLocationName;

    public Long salesArea;

    public String salesAreaName;

    public Long taxGroup;

    public String taxGroupName;

    private String createdBy;

    private String created;

    private String modifiedBy;

    private String modified;

    public CustomerDTO() {
    }

    public CustomerDTO(Long id, String customerName, String customerShortName, String address, String gstNo, SalesTypeName salesTypeOrPriceList, String contactPersonsName, String phone, String mobile, String email, String salesPerson, String bankAccountNumber, String mailingAddress, String billingAddress, String deliveryAddress, double discountPercent, double promptPaymentDiscountPercent, double creditLimit, String paymentTerms, Long creditStatus, String creditStatusName, String generalNotes, Long defaultInventoryLocation, String defaultInventoryLocationName, Long salesArea, String salesAreaName, Long taxGroup, String taxGroupName, String createdBy, String created, String modifiedBy, String modified) {
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
        this.creditStatusName = creditStatusName;
        this.generalNotes = generalNotes;
        this.defaultInventoryLocation = defaultInventoryLocation;
        this.defaultInventoryLocationName = defaultInventoryLocationName;
        this.salesArea = salesArea;
        this.salesAreaName = salesAreaName;
        this.taxGroup = taxGroup;
        this.taxGroupName = taxGroupName;
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

    public String getContactPersonsName() {
        return contactPersonsName;
    }

    public void setContactPersonsName(String contactPersonsName) {
        this.contactPersonsName = contactPersonsName;
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

    public Long getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(Long creditStatus) {
        this.creditStatus = creditStatus;
    }

    public String getCreditStatusName() {
        return creditStatusName;
    }

    public void setCreditStatusName(String creditStatusName) {
        this.creditStatusName = creditStatusName;
    }

    public String getGeneralNotes() {
        return generalNotes;
    }

    public void setGeneralNotes(String generalNotes) {
        this.generalNotes = generalNotes;
    }

    public Long getDefaultInventoryLocation() {
        return defaultInventoryLocation;
    }

    public void setDefaultInventoryLocation(Long defaultInventoryLocation) {
        this.defaultInventoryLocation = defaultInventoryLocation;
    }

    public String getDefaultInventoryLocationName() {
        return defaultInventoryLocationName;
    }

    public void setDefaultInventoryLocationName(String defaultInventoryLocationName) {
        this.defaultInventoryLocationName = defaultInventoryLocationName;
    }

    public Long getSalesArea() {
        return salesArea;
    }

    public void setSalesArea(Long salesArea) {
        this.salesArea = salesArea;
    }

    public String getSalesAreaName() {
        return salesAreaName;
    }

    public void setSalesAreaName(String salesAreaName) {
        this.salesAreaName = salesAreaName;
    }

    public Long getTaxGroup() {
        return taxGroup;
    }

    public void setTaxGroup(Long taxGroup) {
        this.taxGroup = taxGroup;
    }

    public String getTaxGroupName() {
        return taxGroupName;
    }

    public void setTaxGroupName(String taxGroupName) {
        this.taxGroupName = taxGroupName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }
 
}
