/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.settings.model.miscellaneous;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author User
 */
@Entity
public class PaymentTerms {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String terms;

    @Column(unique = true, updatable = false)
    private String slug;

    private int daysBeforeDue;

    private int dayInFollowingMonth;

    public PaymentTerms() {
    }

    public PaymentTerms(Long id, String terms, String slug, int daysBeforeDue, int dayInFollowingMonth) {
        this.id = id;
        this.terms = terms;
        this.slug = slug;
        this.daysBeforeDue = daysBeforeDue;
        this.dayInFollowingMonth = dayInFollowingMonth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getDaysBeforeDue() {
        return daysBeforeDue;
    }

    public void setDaysBeforeDue(int daysBeforeDue) {
        this.daysBeforeDue = daysBeforeDue;
    }

    public int getDayInFollowingMonth() {
        return dayInFollowingMonth;
    }

    public void setDayInFollowingMonth(int dayInFollowingMonth) {
        this.dayInFollowingMonth = dayInFollowingMonth;
    }

}
