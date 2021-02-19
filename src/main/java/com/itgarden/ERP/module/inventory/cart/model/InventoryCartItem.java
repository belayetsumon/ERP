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

    private Long itemId;

    private String itemCode;

    private String itemName;

    private BigDecimal quantity;

    private String unit;

    private BigDecimal price;

    private BigDecimal itemTotal;

    public InventoryCartItem() {
    }

    public InventoryCartItem(long id, Long itemId, String itemCode, String itemName, BigDecimal quantity, String unit, BigDecimal price, BigDecimal itemTotal) {
        this.id = id;
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.itemName = itemName;
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

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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
