/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.finance_banking.repository.settings;

import com.itgarden.ERP.module.finance_banking.model.settings.AccountTags;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface AccountTagsRepository extends JpaRepository<AccountTags, Long> {
    
}