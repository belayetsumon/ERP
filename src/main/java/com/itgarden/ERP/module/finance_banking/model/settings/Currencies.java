/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.finance_banking.model.settings;

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
public class Currencies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Currency symbol cannot be blank.")
    private String currencySymbol;
    
     @NotEmpty(message = "Currency name cannot be blank.")
    private String name;

    @NotEmpty(message = "Currency Abbreviation cannot be blank.")
    private String currencyAbbreviation;

    @NotEmpty(message = "Hundredths Name cannot be blank.")
    private String hundredthsName;

    @NotEmpty(message = "Country Name cannot be blank.")
    private String country;
    
    
    
    //// Audit /// 
//    @Version
//    private int version;
//
//    @CreatedBy
//    @Column(nullable = false, updatable = false)
//    private String createdBy;
//
//    @CreatedDate
//    @Column(nullable = false, updatable = false)
//    private LocalDateTime created;
//
//    @LastModifiedBy
//    @Column(insertable = false, updatable = true)
//    private String modifiedBy;
//
//    @LastModifiedDate
//    @Column(insertable = false, updatable = true)
//    private LocalDateTime modified;
//
//    /// End Audit //// 

    

    public Currencies(Long id, String currencySymbol, String name, String currencyAbbreviation, String hundredthsName, String country) {
        this.id = id;
        this.currencySymbol = currencySymbol;
        this.name = name;
        this.currencyAbbreviation = currencyAbbreviation;
        this.hundredthsName = hundredthsName;
        this.country = country;
    }

    public Currencies() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getCurrencyAbbreviation() {
        return currencyAbbreviation;
    }

    public void setCurrencyAbbreviation(String currencyAbbreviation) {
        this.currencyAbbreviation = currencyAbbreviation;
    }

    public String getHundredthsName() {
        return hundredthsName;
    }

    public void setHundredthsName(String hundredthsName) {
        this.hundredthsName = hundredthsName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
