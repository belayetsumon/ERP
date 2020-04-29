/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.settings.controller.company_setup;

import com.itgarden.ERP.module.settings.model.company_setup.TaxGroups;
import com.itgarden.ERP.module.settings.model.enumvalue.YesNo;
import com.itgarden.ERP.module.settings.repository.company_setup.TaxGroupsRepository;
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
@RequestMapping("/taxgroups")
public class TaxGroupsController {

    @Autowired
    TaxGroupsRepository taxGroupsRepository;

    @Autowired
    TaxesRepository taxesRepository;

    @RequestMapping("/index")
    public String index(Model model, TaxGroups taxGroups) {
        model.addAttribute("taxGroupslist", taxGroupsRepository.findAll());
        model.addAttribute("taxlist", taxesRepository.findAll());
        model.addAttribute("yesno", YesNo.values());
        return "module/settings/company_setup/taxgroups";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid TaxGroups taxGroups, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("taxGroupslist", taxGroupsRepository.findAll());
            model.addAttribute("taxlist", taxesRepository.findAll());
            model.addAttribute("yesno", YesNo.values());
            return "module/settings/company_setup/taxgroups";
        }
        taxGroupsRepository.save(taxGroups);
        redirectAttrs.addFlashAttribute("success_messages ", "Sucessfully save.");
        return "redirect:/taxgroups/index";
    }

    @RequestMapping("/edit/{id}")
    public String index(Model model, @PathVariable Long id, TaxGroups taxGroups) {
        model.addAttribute("taxGroups", taxGroupsRepository.getOne(id));
        model.addAttribute("taxGroupslist", taxGroupsRepository.findAll());
        model.addAttribute("taxlist", taxesRepository.findAll());
        model.addAttribute("yesno", YesNo.values());
        return "module/settings/company_setup/taxgroups";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, TaxGroups taxGroups, RedirectAttributes redirectAttrs) {
        taxGroupsRepository.deleteById(id);
        return "redirect:/taxgroups/index";
    }

}
