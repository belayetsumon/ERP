/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.settings.model.company_setup;

import com.itgarden.ERP.module.settings.model.enumvalue.TransactionType;
import com.itgarden.ERP.module.settings.model.enumvalue.YesNo;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author User
 */


@EntityListeners(AuditingEntityListener.class)
@Entity
public class TransactionReferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    
    
    
//    /// Audit /// 
//    @Version
//    private int version;
//
//    @CreatedBy
//    @Column(nullable = false, updatable = false)
//    private String createdBy;
//
//    @CreatedDate
//    @Column(nullable = false, updatable = false)
//    private LocalDateTime created;
//
//    @LastModifiedBy
//    @Column(insertable = false, updatable = true)
//    private String modifiedBy;
//
//    @LastModifiedDate
//    @Column(insertable = false, updatable = true)
//    private LocalDateTime modified;
//
//    /// End Audit //// 

    
    
}