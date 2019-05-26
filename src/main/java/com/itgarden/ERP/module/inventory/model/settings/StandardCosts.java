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
public class StandardCosts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    int itemId;
    int itemCost;
    String memo;

}
