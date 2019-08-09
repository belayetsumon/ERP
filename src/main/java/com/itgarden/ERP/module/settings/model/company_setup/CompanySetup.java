/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.settings.model.company_setup;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author User
 */
@Entity
public class CompanySetup {

    ////  General settings //
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    String companyName;
    @NotEmpty
    String address;

    String phoneNumber;

    String faxNumber;

    String emailAddress;

    String mobileNumber;

    // General Ledger Settings//
    String fiscalYear;

    int taxPeriods; //Months.

    int taxLastPeriod; //Months back.

    //User Interface Options//
    int loginTimeout;

    public CompanySetup() {
    }

    public CompanySetup(Long id, String companyName, String address, String phoneNumber, String faxNumber, String emailAddress, String mobileNumber, String fiscalYear, int taxPeriods, int taxLastPeriod, int loginTimeout) {
        this.id = id;
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.faxNumber = faxNumber;
        this.emailAddress = emailAddress;
        this.mobileNumber = mobileNumber;
        this.fiscalYear = fiscalYear;
        this.taxPeriods = taxPeriods;
        this.taxLastPeriod = taxLastPeriod;
        this.loginTimeout = loginTimeout;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public int getTaxPeriods() {
        return taxPeriods;
    }

    public void setTaxPeriods(int taxPeriods) {
        this.taxPeriods = taxPeriods;
    }

    public int getTaxLastPeriod() {
        return taxLastPeriod;
    }

    public void setTaxLastPeriod(int taxLastPeriod) {
        this.taxLastPeriod = taxLastPeriod;
    }

    public int getLoginTimeout() {
        return loginTimeout;
    }

    public void setLoginTimeout(int loginTimeout) {
        this.loginTimeout = loginTimeout;
    }

   

}
