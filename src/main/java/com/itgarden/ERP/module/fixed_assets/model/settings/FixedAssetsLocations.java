/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.fixed_assets.model.settings;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Entity
public class FixedAssetsLocations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @NotNull(message = "This field cannot be blank.")
    int locationCode;
    @NotEmpty(message = "Location Name cannot be blank.")
    String locationName;

    @Lob
    String contactForDeliveries;

    @Lob
    String address;

    String telephoneNo;
    
    String mobile;
    @Email
    String email;

    public FixedAssetsLocations() {
    }

    public FixedAssetsLocations(Long id, int locationCode, String locationName, String contactForDeliveries, String address, String telephoneNo, String mobile, String email) {
        this.id = id;
        this.locationCode = locationCode;
        this.locationName = locationName;
        this.contactForDeliveries = contactForDeliveries;
        this.address = address;
        this.telephoneNo = telephoneNo;
        this.mobile = mobile;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(int locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getContactForDeliveries() {
        return contactForDeliveries;
    }

    public void setContactForDeliveries(String contactForDeliveries) {
        this.contactForDeliveries = contactForDeliveries;
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

   
    

}
