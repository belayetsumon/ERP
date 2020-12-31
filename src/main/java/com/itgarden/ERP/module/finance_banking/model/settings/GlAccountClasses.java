/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.finance_banking.model.settings;

import com.itgarden.ERP.module.finance_banking.model.enumvalue.ClassType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class GlAccountClasses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Class ID cannot be blank.")
    int classId;
    @NotEmpty(message = "Class Name cannot be blank.")
    String className;

    @NotNull(message = "Class Type cannot be blank.")
    @Enumerated(EnumType.STRING)
    ClassType classType;

    public GlAccountClasses() {
    }

    public GlAccountClasses(Long id, int classId, String className, ClassType classType) {
        this.id = id;
        this.classId = classId;
        this.className = className;
        this.classType = classType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

}
