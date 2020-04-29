/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.finance_banking.model.settings;

import com.itgarden.ERP.module.finance_banking.model.enumvalue.EntryType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author User
 */
@Entity
public class QuickEntries {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String description;
    String usage;
    EntryType entryType;
    String baseAmountDescription;
    String defaultBaseAmount;
    

}
