/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.finance_banking.controller.inquiries_reports;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author User
 */
@Controller
 @RequestMapping("/chartofaccounts")
public class Chart_Of_AccountsController {
    
     @RequestMapping(value = {"", "/", "/index"})
    public String page(Model model) {
        model.addAttribute("attribute", "value");
        return "module/finance_banking/inquiriesandreports/chartofaccounts";
    }
    
}
