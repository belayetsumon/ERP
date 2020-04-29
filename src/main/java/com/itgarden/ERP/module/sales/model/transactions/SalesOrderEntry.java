/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.model.transactions;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author User
 */
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class SalesOrderEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false, updatable = false)
    private String orderno;

    private String transType;

    private String version;

    private String ordertype;

    private String debtorNo;

    private String branchCode;

    private String reference;

    private String customerRef;

    private String comments;

    private String ordDate;

    private String orderType;

    private String shipVia;

    private String deliveryAddress;

    private String contactPhone;

    private String contactEmail;

    private String deliverTo;

    private String freightCost;

    private String fromStkLoc;

    private String deliveryDate;

    private String paymentTerms;

    private String total;

    private String prepAmount;
    private String alloc;

    /// Audit /// 
    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime created;

    @LastModifiedBy
    @Column(insertable = false)
    private String modifiedBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime modified;

    /// End Audit //// 
}
