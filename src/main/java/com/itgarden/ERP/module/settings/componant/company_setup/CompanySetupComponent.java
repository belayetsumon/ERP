/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.settings.componant.company_setup;

import com.itgarden.ERP.module.settings.model.company_setup.CompanySetup;
import com.itgarden.ERP.module.settings.repository.company_setup.CompanySetupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author User
 */
@Component
public class CompanySetupComponent {

    @Autowired
    CompanySetupRepository companySetupRepository;

    public CompanySetup companySetup() {

        CompanySetup companySetup = companySetupRepository.getOne(1L);

        return companySetup;
    }

}
