/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.settings.model.company_setup;

import com.itgarden.ERP.module.settings.model.enumvalue.TransactionType;
import com.itgarden.ERP.module.settings.model.enumvalue.YesNo;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Entity
public class TransactionReferences {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    TransactionType transactionType;
    String referencePattern;
    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    YesNo defaultforThisType;
    @Lob
    String memo;

    public TransactionReferences() {
    }

    public TransactionReferences(Long id, TransactionType transactionType, String referencePattern, YesNo defaultforThisType, String memo) {
        this.id = id;
        this.transactionType = transactionType;
        this.referencePattern = referencePattern;
        this.defaultforThisType = defaultforThisType;
        this.memo = memo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getReferencePattern() {
        return referencePattern;
    }

    public void setReferencePattern(String referencePattern) {
        this.referencePattern = referencePattern;
    }

    public YesNo getDefaultforThisType() {
        return defaultforThisType;
    }

    public void setDefaultforThisType(YesNo defaultforThisType) {
        this.defaultforThisType = defaultforThisType;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
    
    

}
