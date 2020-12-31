/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.finance_banking.model.settings;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Entity
public class GlAccountGroups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "This field cannot be blank.")
    int groupId;
    @NotEmpty(message = "This field cannot be blank.")
    String groupName;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccountClasses classes;

    public GlAccountGroups() {
    }

    public GlAccountGroups(Long id, int groupId, String groupName, GlAccountClasses classes) {
        this.id = id;
        this.groupId = groupId;
        this.groupName = groupName;
        this.classes = classes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public GlAccountClasses getClasses() {
        return classes;
    }

    public void setClasses(GlAccountClasses classes) {
        this.classes = classes;
    }
    
    

}
