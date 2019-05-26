/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.purchases.model.settings;

import com.itgarden.ERP.module.finance_banking.model.settings.BankAccounts;
import com.itgarden.ERP.module.finance_banking.model.settings.GlAccounts;
import com.itgarden.ERP.module.settings.model.company_setup.TaxGroups;
import com.itgarden.ERP.module.settings.model.enumvalue.YesNo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Entity
public class Suppliers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //  Basic Data //
    @NotEmpty(message = "This field cannot be blank.")
    String supplierName;

    String supplierShortName;
    String gstNo;
    String website;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    TaxGroups taxGroup;
    String ourCustomerNo;

//Purchasing//
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    BankAccounts bankNameOrAccount;
    String creditLimit;
    String paymentTerms;

    YesNo pricesContainTaxIncluded;

    // /// GL Accounts  ////
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts accountsPayableAccount;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts purchaseAccount;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts purchaseDiscountAccount;

    // Contact Data //
    String contactPerson;
    String phoneNumber;
    String mobileNumber;
    String faxNumber;
    @Email
    String email;
    @Lob
    String mailingAddress;
    @Lob
    String physicalAddress;
    @Lob
    String generalNotes;

    public Suppliers() {
    }

    public Suppliers(Long id, String supplierName, String supplierShortName, String gstNo, String website, TaxGroups taxGroup, String ourCustomerNo, BankAccounts bankNameOrAccount, String creditLimit, String paymentTerms, YesNo pricesContainTaxIncluded, GlAccounts accountsPayableAccount, GlAccounts purchaseAccount, GlAccounts purchaseDiscountAccount, String contactPerson, String phoneNumber, String mobileNumber, String faxNumber, String email, String mailingAddress, String physicalAddress, String generalNotes) {
        this.id = id;
        this.supplierName = supplierName;
        this.supplierShortName = supplierShortName;
        this.gstNo = gstNo;
        this.website = website;
        this.taxGroup = taxGroup;
        this.ourCustomerNo = ourCustomerNo;
        this.bankNameOrAccount = bankNameOrAccount;
        this.creditLimit = creditLimit;
        this.paymentTerms = paymentTerms;
        this.pricesContainTaxIncluded = pricesContainTaxIncluded;
        this.accountsPayableAccount = accountsPayableAccount;
        this.purchaseAccount = purchaseAccount;
        this.purchaseDiscountAccount = purchaseDiscountAccount;
        this.contactPerson = contactPerson;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
        this.faxNumber = faxNumber;
        this.email = email;
        this.mailingAddress = mailingAddress;
        this.physicalAddress = physicalAddress;
        this.generalNotes = generalNotes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierShortName() {
        return supplierShortName;
    }

    public void setSupplierShortName(String supplierShortName) {
        this.supplierShortName = supplierShortName;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public TaxGroups getTaxGroup() {
        return taxGroup;
    }

    public void setTaxGroup(TaxGroups taxGroup) {
        this.taxGroup = taxGroup;
    }

    public String getOurCustomerNo() {
        return ourCustomerNo;
    }

    public void setOurCustomerNo(String ourCustomerNo) {
        this.ourCustomerNo = ourCustomerNo;
    }

    public BankAccounts getBankNameOrAccount() {
        return bankNameOrAccount;
    }

    public void setBankNameOrAccount(BankAccounts bankNameOrAccount) {
        this.bankNameOrAccount = bankNameOrAccount;
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public YesNo getPricesContainTaxIncluded() {
        return pricesContainTaxIncluded;
    }

    public void setPricesContainTaxIncluded(YesNo pricesContainTaxIncluded) {
        this.pricesContainTaxIncluded = pricesContainTaxIncluded;
    }

    public GlAccounts getAccountsPayableAccount() {
        return accountsPayableAccount;
    }

    public void setAccountsPayableAccount(GlAccounts accountsPayableAccount) {
        this.accountsPayableAccount = accountsPayableAccount;
    }

    public GlAccounts getPurchaseAccount() {
        return purchaseAccount;
    }

    public void setPurchaseAccount(GlAccounts purchaseAccount) {
        this.purchaseAccount = purchaseAccount;
    }

    public GlAccounts getPurchaseDiscountAccount() {
        return purchaseDiscountAccount;
    }

    public void setPurchaseDiscountAccount(GlAccounts purchaseDiscountAccount) {
        this.purchaseDiscountAccount = purchaseDiscountAccount;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public String getGeneralNotes() {
        return generalNotes;
    }

    public void setGeneralNotes(String generalNotes) {
        this.generalNotes = generalNotes;
    }
}
