/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.settings.model.miscellaneous;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author User
 */
@Entity
public class ShippingCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Name cannot be blank.")
    @Size(min = 2, message = "Minimum input 2 char. ")
    private String name;

    @NotEmpty(message = "Contact Persons cannot be blank.")
    @Size(min = 2, message = "Minimum input 2 char. ")
    private String contactPerson;

    @NotEmpty(message = "Mobile cannot be blank.")
    @Size(min = 2, message = "Minimum input 2 char. ")
    private String mobile;

    @NotEmpty(message = "Email cannot be blank.")
    @Email(message = "Email should be valid.")
    private String email;

    @NotEmpty(message = "Address cannot be blank.")
    @Size(min = 2, message = "Minimum input 2 char. ")
    private String address ;
    
    
    ///// Audit /// 
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

    public ShippingCompany() {
    }

    public ShippingCompany(Long id, String name, String contactPerson, String mobile, String email) {
        this.id = id;
        this.name = name;
        this.contactPerson = contactPerson;
        this.mobile = mobile;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
