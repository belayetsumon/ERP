/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.settings.model.company_setup;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Entity
public class FiscalYears {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    String fiscalYearBegin;
    @NotEmpty
    String fiscalYearEnd;
    @NotNull
    byte IsClosed;

    public FiscalYears() {
    }

    public FiscalYears(Long id, String fiscalYearBegin, String fiscalYearEnd, byte IsClosed) {
        this.id = id;
        this.fiscalYearBegin = fiscalYearBegin;
        this.fiscalYearEnd = fiscalYearEnd;
        this.IsClosed = IsClosed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFiscalYearBegin() {
        return fiscalYearBegin;
    }

    public void setFiscalYearBegin(String fiscalYearBegin) {
        this.fiscalYearBegin = fiscalYearBegin;
    }

    public String getFiscalYearEnd() {
        return fiscalYearEnd;
    }

    public void setFiscalYearEnd(String fiscalYearEnd) {
        this.fiscalYearEnd = fiscalYearEnd;
    }

    public byte getIsClosed() {
        return IsClosed;
    }

    public void setIsClosed(byte IsClosed) {
        this.IsClosed = IsClosed;
    }
    
    

}