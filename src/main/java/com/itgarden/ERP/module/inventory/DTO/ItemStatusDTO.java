/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.DTO;

import java.math.BigDecimal;

/**
 *
 * @author User
 */
public class ItemStatusDTO {

    public Long id;

    public String itemCode; // item code  bar code

    public String name;

    public BigDecimal quantity;

    public BigDecimal retailPrice;

    public BigDecimal itemTotal;

    public ItemStatusDTO() {
    }

    public ItemStatusDTO(Long id, String itemCode, String name, BigDecimal quantity, BigDecimal retailPrice, BigDecimal itemTotal) {
        this.id = id;
        this.itemCode = itemCode;
        this.name = name;
        this.quantity = quantity;
        this.retailPrice = retailPrice;
        this.itemTotal = itemTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public BigDecimal getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(BigDecimal itemTotal) {
        this.itemTotal = itemTotal;
    }
}
