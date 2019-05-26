/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.settings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author User
 */
@Controller
 @RequestMapping("/settings")
public class SettingsDashboardsController {
    
    @RequestMapping("/dashboards")
    public String dashboards(Model model) {
        model.addAttribute("attribute", "value");
        return "view.name";
    }
    
    
    
    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("attribute", "value");
        return "module/settings/company_setup/index";
    }
    
}
