/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author User
 */
@Controller
   @RequestMapping("/demo")
public class DemoController {
    
    @RequestMapping("/form")
    public String form(Model model) {
        model.addAttribute("attribute", "value");
        return "demo/form";
    }
    
     @RequestMapping("/report")
    public String report(Model model) {
        model.addAttribute("attribute", "value");
        return "demo/report";
    }
    
    
       @RequestMapping("/settings")
    public String settings(Model model) {
        model.addAttribute("attribute", "value");
        return "demo/settings";
    }
}
