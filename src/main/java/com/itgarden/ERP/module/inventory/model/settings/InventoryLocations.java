/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.model.settings;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author User
 */
@Entity
public class InventoryLocations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String locationCode;

    String locationName;

    String deliveriesContact;

    String address;

    String telephoneNo;

    public InventoryLocations() {
    }

    public InventoryLocations(Long id, String locationCode, String locationName, String deliveriesContact, String address, String telephoneNo) {
        this.id = id;
        this.locationCode = locationCode;
        this.locationName = locationName;
        this.deliveriesContact = deliveriesContact;
        this.address = address;
        this.telephoneNo = telephoneNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getDeliveriesContact() {
        return deliveriesContact;
    }

    public void setDeliveriesContact(String deliveriesContact) {
        this.deliveriesContact = deliveriesContact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    
}
