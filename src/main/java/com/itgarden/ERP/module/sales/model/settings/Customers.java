/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.model.settings;

import com.itgarden.ERP.module.inventory.model.settings.InventoryLocations;
import com.itgarden.ERP.module.sales.model.enumvalue.SalesTypeName;
import com.itgarden.ERP.module.settings.model.company_setup.TaxGroups;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Entity
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Name and Address //
    @NotEmpty(message = "This field cannot be blank.")
    String customerName;

    String customerShortName;
    String address;
    String gstNo;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    SalesTypeName salesTypeOrPriceList;

    ///contact///
    String phone;
    String mobile;
    String email;

    //Sales//
    int discountPercent;
    int promptPaymentDiscountPercent;
    int creditLimit;
    String paymentTerms;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    CreditStatusSetup creditStatus;
    String generalNotes;

    // Branch//
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    InventoryLocations defaultInventoryLocation;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    SalesAreas salesArea;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    TaxGroups taxGroup;

    // Others //
    String bankAccountNumber;
    
    String salesPerson;

    public Customers() {
    }

    public Customers(Long id, String customerName, String customerShortName, String address, String gstNo, SalesTypeName salesTypeOrPriceList, String phone, String mobile, String email, int discountPercent, int promptPaymentDiscountPercent, int creditLimit, String paymentTerms, CreditStatusSetup creditStatus, String generalNotes, InventoryLocations defaultInventoryLocation, SalesAreas salesArea, TaxGroups taxGroup, String bankAccountNumber, String salesPerson) {
        this.id = id;
        this.customerName = customerName;
        this.customerShortName = customerShortName;
        this.address = address;
        this.gstNo = gstNo;
        this.salesTypeOrPriceList = salesTypeOrPriceList;
        this.phone = phone;
        this.mobile = mobile;
        this.email = email;
        this.discountPercent = discountPercent;
        this.promptPaymentDiscountPercent = promptPaymentDiscountPercent;
        this.creditLimit = creditLimit;
        this.paymentTerms = paymentTerms;
        this.creditStatus = creditStatus;
        this.generalNotes = generalNotes;
        this.defaultInventoryLocation = defaultInventoryLocation;
        this.salesArea = salesArea;
        this.taxGroup = taxGroup;
        this.bankAccountNumber = bankAccountNumber;
        this.salesPerson = salesPerson;
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

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getPromptPaymentDiscountPercent() {
        return promptPaymentDiscountPercent;
    }

    public void setPromptPaymentDiscountPercent(int promptPaymentDiscountPercent) {
        this.promptPaymentDiscountPercent = promptPaymentDiscountPercent;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(int creditLimit) {
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

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(String salesPerson) {
        this.salesPerson = salesPerson;
    }
    
    

}
