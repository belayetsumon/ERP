/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.branch.transactions.services;

import com.itgarden.ERP.module.branch.model.transactions.BranchExpense;
import com.itgarden.ERP.module.branch.repositoy.transactions.BranchExpenseRepository;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class BranchExpenseService {
    
  @Autowired
    BranchExpenseRepository branchExpenseRepository;
    
    public String refarance() {

        BranchExpense refe = branchExpenseRepository.findTopByOrderByIdDesc();

        Long lastid;

        if (refe != null) {

            lastid = refe.getId() + 1;

        } else {

            lastid = 1l;
        }

        int year = Calendar.getInstance().get(Calendar.YEAR);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%05d", lastid));
        sb.append("/");
        sb.append(year);
        return sb.toString();
    }
    
}
