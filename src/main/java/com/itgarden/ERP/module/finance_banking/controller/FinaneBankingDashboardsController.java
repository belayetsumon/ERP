/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.finance_banking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author User
 */
@Controller
    @RequestMapping("/finanebanking")
public class FinaneBankingDashboardsController {
    
    @RequestMapping("/dashboards")
    public String dashboards(Model model) {
        model.addAttribute("attribute", "value");
        return "view.name";
    }
    
    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("attribute", "value");
        return "module/finance_banking/inquiriesandreports/index";
    }
    
}
