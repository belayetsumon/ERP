/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.branch.repository.settings;

import com.itgarden.ERP.module.branch.model.settings.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Long> {
    
}
