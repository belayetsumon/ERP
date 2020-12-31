package com.itgarden.ERP.module.sales.dto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class CustomerSearchDto {

 
    
    public Long customerId;
    
    public String customerName;

    public CustomerSearchDto(Long customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public CustomerSearchDto() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    
    
}
