/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.cart.model;

import java.math.BigDecimal;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author User
 */
@Scope("session")
public class InventoryCartItem {

    private long id;

    private int itemCode;

    private BigDecimal quantity;

    private String unit;

    private BigDecimal price;

    private BigDecimal itemTotal;

    public InventoryCartItem() {
    }
    
    

    public InventoryCartItem(long id, int itemCode, BigDecimal quantity, String unit, BigDecimal price, BigDecimal itemTotal) {
        this.id = id;
        this.itemCode = itemCode;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.itemTotal = itemTotal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(BigDecimal itemTotal) {
        this.itemTotal = itemTotal;
    }

}
