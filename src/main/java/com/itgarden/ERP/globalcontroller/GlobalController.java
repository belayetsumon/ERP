/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.globalcontroller;

import com.itgarden.ERP.module.settings.componant.company_setup.CompanySetupComponent;
import com.itgarden.ERP.module.user.services.UserComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author User
 */
@ControllerAdvice
public class GlobalController {

    @Autowired
    CompanySetupComponent companySetup;

    @Autowired
    UserComponent userInfo;

    @ModelAttribute
    public void companySetup(Model model) {

//        model.addAttribute("company_name", "HELLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLo");
//
//        model.addAttribute("company_name2", companySetup.companySetup().getCompanyName());
    }

}
