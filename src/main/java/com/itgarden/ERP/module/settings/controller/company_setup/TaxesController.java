/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.settings.controller.company_setup;

import com.itgarden.ERP.module.finance_banking.repository.settings.GlAccountsRepository;
import com.itgarden.ERP.module.settings.model.company_setup.Taxes;
import com.itgarden.ERP.module.settings.repository.company_setup.TaxesRepository;
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
@RequestMapping("/taxes")
public class TaxesController {

    @Autowired
    TaxesRepository taxesRepository;

    @Autowired
    GlAccountsRepository glAccountsRepository;

    @RequestMapping("/index")
    public String index(Model model, Taxes taxes) {
        model.addAttribute("taxeslist", taxesRepository.findAll());
        model.addAttribute("glaccounts", glAccountsRepository.findAll());
        return "module/settings/company_setup/taxes";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid Taxes taxes, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("taxeslist", taxesRepository.findAll());
            model.addAttribute("glaccounts", glAccountsRepository.findAll());
            return "module/settings/company_setup/taxgroups";
        }
        taxesRepository.save(taxes);
        redirectAttrs.addFlashAttribute("success_messages ", "Sucessfully save.");
        return "redirect:/taxes/index";
    }

    @RequestMapping("/edit/{id}")
    public String index(Model model, @PathVariable Long id, Taxes taxes) {
        model.addAttribute("taxes", taxesRepository.getOne(id));
        model.addAttribute("taxeslist", taxesRepository.findAll());
        model.addAttribute("glaccounts", glAccountsRepository.findAll());
        return "module/settings/company_setup/taxes";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, Taxes taxes, RedirectAttributes redirectAttrs) {
        taxesRepository.deleteById(id);
        return "redirect:/taxes/index";
    }

}
