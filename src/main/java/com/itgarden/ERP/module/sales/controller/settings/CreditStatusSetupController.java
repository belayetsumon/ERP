/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.controller.settings;

import com.itgarden.ERP.module.inventory.model.settings.InventoryLocations;
import com.itgarden.ERP.module.sales.model.settings.CreditStatusSetup;
import com.itgarden.ERP.module.sales.repository.settings.CreditStatusSetupRepository;
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
@RequestMapping("/creditstatussetup")
public class CreditStatusSetupController {
    
    @Autowired
    CreditStatusSetupRepository creditStatusSetupRepository;
    
    @RequestMapping("/index")
    public String index(Model model,CreditStatusSetup creditStatusSetup) {
        model.addAttribute("creditStatusSetuplist", creditStatusSetupRepository.findAll());
        return "module/sales/settings/creditstatussetup";
    }
    
    
    @RequestMapping("/save")
    public String save(Model model, @Valid CreditStatusSetup creditStatusSetup, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("creditStatusSetuplist", creditStatusSetupRepository.findAll());
            return "module/sales/settings/creditstatussetup";
        }
        creditStatusSetupRepository.save(creditStatusSetup);
        redirectAttrs.addFlashAttribute("success_messages", " Successfully Save.");
        return "redirect:/creditstatussetup/index";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, CreditStatusSetup creditStatusSetup) {
        model.addAttribute("creditStatusSetup", creditStatusSetupRepository.getOne(id));
        model.addAttribute("creditStatusSetuplist", creditStatusSetupRepository.findAll());
        return "module/sales/settings/creditstatussetup";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, CreditStatusSetup creditStatusSetup, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("success_messages", " Successfully Delete.");
        creditStatusSetupRepository.deleteById(id);
        return "redirect:/creditstatussetup/index";
    }

    
}
