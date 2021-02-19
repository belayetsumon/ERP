/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.cart.model;

import com.itgarden.ERP.module.inventory.model.settings.Items;
import java.io.Serializable;
import java.math.BigDecimal;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author User
 */
@Scope("session")
public class SalesCartItem implements Serializable {

    private long id;

    private long itemId;

    private String itemCode;
    
     private String name;

    private String itemDescription;

    private BigDecimal quantity;

    private String unit;

    private BigDecimal price;

    private BigDecimal taxPercent;

    private BigDecimal taxTotal;

    private BigDecimal discountPercent;

    private BigDecimal discountTotal;

    private BigDecimal itemTotal;

    public SalesCartItem() {

    }

    public SalesCartItem(long id, long itemId, String itemCode, String name, String itemDescription, BigDecimal quantity, String unit, BigDecimal price, BigDecimal taxPercent, BigDecimal taxTotal, BigDecimal discountPercent, BigDecimal discountTotal, BigDecimal itemTotal) {
        this.id = id;
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.name = name;
        this.itemDescription = itemDescription;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.taxPercent = taxPercent;
        this.taxTotal = taxTotal;
        this.discountPercent = discountPercent;
        this.discountTotal = discountTotal;
        this.itemTotal = itemTotal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
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

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
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

    public BigDecimal getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(BigDecimal taxPercent) {
        this.taxPercent = taxPercent;
    }

    public BigDecimal getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(BigDecimal taxTotal) {
        this.taxTotal = taxTotal;
    }

    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    public BigDecimal getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(BigDecimal discountTotal) {
        this.discountTotal = discountTotal;
    }

    public BigDecimal getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(BigDecimal itemTotal) {
        this.itemTotal = itemTotal;
    }
    
    
    
    
    

}
