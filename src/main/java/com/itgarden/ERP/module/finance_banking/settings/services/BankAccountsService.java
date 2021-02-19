/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.finance_banking.settings.services;

import com.itgarden.ERP.module.finance_banking.model.settings.BankAccounts;
import com.itgarden.ERP.module.finance_banking.repository.settings.BankAccountsRepository;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class BankAccountsService {

    @Autowired
    BankAccountsRepository bankAccountsRepository;

    public BigDecimal bankcharge(Long id) {

        BankAccounts charg = bankAccountsRepository.getOne(id);

        if (charg != null) {
            return charg.getBankcharge();
        } else {

            return BigDecimal.valueOf(0.00);

        } // condition end

    } // bank charge end

}
