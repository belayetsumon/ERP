/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.controller.settings;

import com.itgarden.ERP.module.sales.model.settings.CreditStatusSetup;
import com.itgarden.ERP.module.sales.model.settings.SalesAreas;
import com.itgarden.ERP.module.sales.repository.settings.SalesAreasRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/salesareas")
public class SalesAreasController {

    @Autowired
    SalesAreasRepository salesAreasRepository;

    @RequestMapping("/index")
    public String index(Model model,SalesAreas salesAreas) {
        model.addAttribute("salesareaslist", salesAreasRepository.findAll());
       return "module/sales/settings/salesareas";
    }

     @RequestMapping("/save")
    public String save(Model model, @Valid SalesAreas salesAreas, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("salesareaslist", salesAreasRepository.findAll());
           return "module/sales/settings/salesareas";
        }
        salesAreasRepository.save(salesAreas);
        redirectAttrs.addFlashAttribute("success_messages", " Successfully Save.");
        return "redirect:/salesareas/index";
    }
    
    
     @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, SalesAreas salesAreas) {
        model.addAttribute("salesAreas", salesAreasRepository.getOne(id));
        model.addAttribute("salesareaslist", salesAreasRepository.findAll());
        return "module/sales/settings/salesareas";
    }
    
    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, SalesAreas salesAreas, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("success_messages", " Successfully Delete.");
        salesAreasRepository.deleteById(id);
        return "redirect:/salesareas/index";
    }
    
    
}
