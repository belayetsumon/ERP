/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.settings.controller.company_setup;

import com.itgarden.ERP.module.settings.model.company_setup.CompanySetup;
import com.itgarden.ERP.module.settings.repository.company_setup.CompanySetupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author User
 */
@Controller
 @RequestMapping("/companysetup")
public class CompanySetupController {
    
   @Autowired
    CompanySetupRepository companySetupRepository;
    
    @RequestMapping("/index")
    public String index(Model model,CompanySetup companySetup) {
        
        if(companySetupRepository.findAll().size()>0){
        model.addAttribute("companySetup", companySetupRepository.getOne(1l));
        }
        
        return "module/settings/company_setup/companysetup";
    }
    
}
