/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.finance_banking.controller.settings;

import com.itgarden.ERP.module.finance_banking.model.enumvalue.ClassType;
import com.itgarden.ERP.module.finance_banking.model.settings.GlAccountGroups;
import com.itgarden.ERP.module.finance_banking.repository.settings.GlAccountClassesRepository;
import com.itgarden.ERP.module.finance_banking.repository.settings.GlAccountGroupsRepository;
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
@RequestMapping("/glaccountgroups")
public class GlAccountGroupsController {

    @Autowired
    GlAccountGroupsRepository glAccountGroupsRepository;
    @Autowired
    GlAccountClassesRepository glAccountClassesRepository;

    @RequestMapping("/index")
    public String index(Model model, GlAccountGroups glAccountGroups) {

        model.addAttribute("glaccountgroups", glAccountGroupsRepository.findAll());

        model.addAttribute("glaccountclasses", glAccountClassesRepository.findAll());

        return "module/finance_banking/settings/glaccountgroups";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid GlAccountGroups glAccountGroups, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("glaccountgroups", glAccountGroupsRepository.findAll());

            model.addAttribute("glaccountclasses", glAccountClassesRepository.findAll());

            return "module/finance_banking/settings/index";
        }

        glAccountGroupsRepository.save(glAccountGroups);
        model.addAttribute("success_messages ", "Sucessfully save.");
        return "redirect:/glaccountgroups/index";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, GlAccountGroups glAccountGroups) {

        model.addAttribute("glAccountClasses", glAccountGroupsRepository.getOne(id));

        model.addAttribute("glaccountgroups", glAccountGroupsRepository.findAll());

        model.addAttribute("glaccountclasses", glAccountClassesRepository.findAll());

        return "module/finance_banking/settings/glaccountgroups";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, GlAccountGroups glAccountGroups, RedirectAttributes redirectAttrs) {
        glAccountGroupsRepository.deleteById(id);
        return "redirect:/glaccountgroups/index";
    }

}
