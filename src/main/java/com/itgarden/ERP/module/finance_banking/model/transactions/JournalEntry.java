/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.finance_banking.model.transactions;

import com.itgarden.ERP.module.settings.model.company_setup.TransactionsType;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author User
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long journalId;

    @ManyToOne(optional = true)
    private TransactionsType transactionsType;

    @Column(nullable = false)
    @NotNull(message = "Payment date cannot be blank.")
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date journalDate;

    @Column(nullable = false)
    @NotNull(message = "Payment date cannot be blank.")
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date documentDate;

    @Column(nullable = false)
    @NotNull(message = "Payment date cannot be blank.")
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date eventDate;

    private String sourceRef;

    @NotBlank
    private String journalReference;

    public boolean taxRegister;

    @Lob
    private String memo;

    /// Audit /// 
//    @Version
//    private int version;
//
    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime created;

    @LastModifiedBy
    @Column(insertable = false, updatable = true)
    private String modifiedBy;

    @LastModifiedDate
    @Column(insertable = false, updatable = true)
    private LocalDateTime modified;

    /// End Audit ////  

    public JournalEntry(Long journalId, TransactionsType transactionsType, Date journalDate, Date documentDate, Date eventDate, String sourceRef, String journalReference, boolean taxRegister, String memo, String createdBy, LocalDateTime created, String modifiedBy, LocalDateTime modified) {
        this.journalId = journalId;
        this.transactionsType = transactionsType;
        this.journalDate = journalDate;
        this.documentDate = documentDate;
        this.eventDate = eventDate;
        this.sourceRef = sourceRef;
        this.journalReference = journalReference;
        this.taxRegister = taxRegister;
        this.memo = memo;
        this.createdBy = createdBy;
        this.created = created;
        this.modifiedBy = modifiedBy;
        this.modified = modified;
    }

    public JournalEntry() {
    }

    public Long getJournalId() {
        return journalId;
    }

    public void setJournalId(Long journalId) {
        this.journalId = journalId;
    }

    public TransactionsType getTransactionsType() {
        return transactionsType;
    }

    public void setTransactionsType(TransactionsType transactionsType) {
        this.transactionsType = transactionsType;
    }

    public Date getJournalDate() {
        return journalDate;
    }

    public void setJournalDate(Date journalDate) {
        this.journalDate = journalDate;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getSourceRef() {
        return sourceRef;
    }

    public void setSourceRef(String sourceRef) {
        this.sourceRef = sourceRef;
    }

    public String getJournalReference() {
        return journalReference;
    }

    public void setJournalReference(String journalReference) {
        this.journalReference = journalReference;
    }

    public boolean isTaxRegister() {
        return taxRegister;
    }

    public void setTaxRegister(boolean taxRegister) {
        this.taxRegister = taxRegister;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }



}
