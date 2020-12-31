/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.fixed_assets.controller.transactions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author User
 */
@Controller
  @RequestMapping("/fixedassetsdisposal")
public class FixedAssetsDisposalController {
    
    @RequestMapping(value = {"", "/", "/index"})
    public String page(Model model) {
        model.addAttribute("attribute", "value");
        return "module/fixed_assets/transactions/fixed_assets_disposal";
    }
    
}
