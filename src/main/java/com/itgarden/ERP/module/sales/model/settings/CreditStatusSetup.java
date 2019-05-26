/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.model.settings;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 *
 * @author User
 */
@Entity
public class CreditStatusSetup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    String description;
    
    String dissallowinvoicing;

    public CreditStatusSetup() {
    }

    public CreditStatusSetup(Long id, String description, String dissallowinvoicing) {
        this.id = id;
        this.description = description;
        this.dissallowinvoicing = dissallowinvoicing;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDissallowinvoicing() {
        return dissallowinvoicing;
    }

    public void setDissallowinvoicing(String dissallowinvoicing) {
        this.dissallowinvoicing = dissallowinvoicing;
    }
    
    
    
    

}
