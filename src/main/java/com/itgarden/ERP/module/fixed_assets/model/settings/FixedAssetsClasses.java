/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.fixed_assets.model.settings;

import com.itgarden.ERP.module.fixed_assets.model.enulvalue.FixedAssetStutas;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Entity
public class FixedAssetsClasses {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = " Parent Class cannot be blank.")
    String parentClass;
    @NotEmpty(message = " Fixed asset class cannot be blank.")
    String fixedAssetClass;

    String description;

    @Lob
    String longDescription;

    @NotNull(message = " Parent Class cannot be blank.")
    int basicDepreciationRate;

    @NotNull(message = "Status cannot be blank.")
    @Enumerated(EnumType.STRING)
    FixedAssetStutas ststus;

    public FixedAssetsClasses() {
    }

    public FixedAssetsClasses(Long id, String parentClass, String fixedAssetClass, String description, String longDescription, int basicDepreciationRate, FixedAssetStutas ststus) {
        this.id = id;
        this.parentClass = parentClass;
        this.fixedAssetClass = fixedAssetClass;
        this.description = description;
        this.longDescription = longDescription;
        this.basicDepreciationRate = basicDepreciationRate;
        this.ststus = ststus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParentClass() {
        return parentClass;
    }

    public void setParentClass(String parentClass) {
        this.parentClass = parentClass;
    }

    public String getFixedAssetClass() {
        return fixedAssetClass;
    }

    public void setFixedAssetClass(String fixedAssetClass) {
        this.fixedAssetClass = fixedAssetClass;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public int getBasicDepreciationRate() {
        return basicDepreciationRate;
    }

    public void setBasicDepreciationRate(int basicDepreciationRate) {
        this.basicDepreciationRate = basicDepreciationRate;
    }

    public FixedAssetStutas getStstus() {
        return ststus;
    }

    public void setStstus(FixedAssetStutas ststus) {
        this.ststus = ststus;
    }

}
