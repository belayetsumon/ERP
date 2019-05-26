/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.model.settings;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author User
 */
@Entity
public class SalesPersons {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String salesPersonName;
    String telephoneNumber;
    String faxNumber;
    String email;
    int provision;
    int turnoverBreakPtLevel;
    int Provision2;

    public SalesPersons() {
    }

    public SalesPersons(Long id, String salesPersonName, String telephoneNumber, String faxNumber, String email, int provision, int turnoverBreakPtLevel, int Provision2) {
        this.id = id;
        this.salesPersonName = salesPersonName;
        this.telephoneNumber = telephoneNumber;
        this.faxNumber = faxNumber;
        this.email = email;
        this.provision = provision;
        this.turnoverBreakPtLevel = turnoverBreakPtLevel;
        this.Provision2 = Provision2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalesPersonName() {
        return salesPersonName;
    }

    public void setSalesPersonName(String salesPersonName) {
        this.salesPersonName = salesPersonName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
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

    public int getProvision() {
        return provision;
    }

    public void setProvision(int provision) {
        this.provision = provision;
    }

    public int getTurnoverBreakPtLevel() {
        return turnoverBreakPtLevel;
    }

    public void setTurnoverBreakPtLevel(int turnoverBreakPtLevel) {
        this.turnoverBreakPtLevel = turnoverBreakPtLevel;
    }

    public int getProvision2() {
        return Provision2;
    }

    public void setProvision2(int Provision2) {
        this.Provision2 = Provision2;
    }

}
