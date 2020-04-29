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
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Entity
public class ReorderLevels {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    Items itemId;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    InventoryLocations location;

    int quantityOnHand;

    int reOrderLevel;

    public ReorderLevels() {
    }

    public ReorderLevels(Long id, Items itemId, InventoryLocations location, int quantityOnHand, int reOrderLevel) {
        this.id = id;
        this.itemId = itemId;
        this.location = location;
        this.quantityOnHand = quantityOnHand;
        this.reOrderLevel = reOrderLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Items getItemId() {
        return itemId;
    }

    public void setItemId(Items itemId) {
        this.itemId = itemId;
    }

    public InventoryLocations getLocation() {
        return location;
    }

    public void setLocation(InventoryLocations location) {
        this.location = location;
    }

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    public int getReOrderLevel() {
        return reOrderLevel;
    }

    public void setReOrderLevel(int reOrderLevel) {
        this.reOrderLevel = reOrderLevel;
    }

}
