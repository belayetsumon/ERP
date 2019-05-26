/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.purchases.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/purchase")
public class DashboardsController {

    @RequestMapping("/dashboards")
    public String page(Model model) {
        model.addAttribute("attribute", "value");
        return "view.name";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("attribute", "value");
        return "module/purchases/inquiriesandreports/index";
    }

}
