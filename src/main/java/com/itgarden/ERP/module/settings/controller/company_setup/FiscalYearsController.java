/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.settings.controller.company_setup;

import com.itgarden.ERP.module.settings.model.company_setup.FiscalYears;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author User
 */
@Controller
    @RequestMapping("/fiscalyears")
public class FiscalYearsController {
    
    @RequestMapping("/index")
    public String index(Model model, FiscalYears fiscalYears) {
        model.addAttribute("attribute", "value");
       return "module/settings/company_setup/fiscalyears";

    }
    
    
    
}
