/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.model.settings;

import com.itgarden.ERP.module.sales.model.enumvalue.SalesTypeName;
import com.itgarden.ERP.module.sales.model.enumvalue.YesNoEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Entity
public class SalesTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    SalesTypeName salesTypeName;
    
    int calculationfactor;
    
    YesNoEnum taxincluded;

	public SalesTypes() {
		super();
	}

	public SalesTypes(Long id, @NotNull(message = "This field cannot be blank.") SalesTypeName salesTypeName,
			int calculationfactor, YesNoEnum taxincluded) {
		super();
		this.id = id;
		this.salesTypeName = salesTypeName;
		this.calculationfactor = calculationfactor;
		this.taxincluded = taxincluded;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SalesTypeName getSalesTypeName() {
		return salesTypeName;
	}

	public void setSalesTypeName(SalesTypeName salesTypeName) {
		this.salesTypeName = salesTypeName;
	}

	public int getCalculationfactor() {
		return calculationfactor;
	}

	public void setCalculationfactor(int calculationfactor) {
		this.calculationfactor = calculationfactor;
	}

	public YesNoEnum getTaxincluded() {
		return taxincluded;
	}

	public void setTaxincluded(YesNoEnum taxincluded) {
		this.taxincluded = taxincluded;
	}
    
    

}
