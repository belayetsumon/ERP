/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.settings.model.company_setup;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author User
 */
@Entity
public class TaxGroups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "This field cannot be blank.")
    private String name;

    @ManyToMany
    @JoinTable(name = "taxgroups_tax",
            joinColumns = @JoinColumn(name = "taxgroup_id"),
            inverseJoinColumns = @JoinColumn(name = "tax_id"))
   private  Set<Taxes> tax;

    public TaxGroups() {
    }

    public TaxGroups(Long id, String name, Set<Taxes> tax) {
        this.id = id;
        this.name = name;
        this.tax = tax;
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

    public Set<Taxes> getTax() {
        return tax;
    }

    public void setTax(Set<Taxes> tax) {
        this.tax = tax;
    }

    
}
