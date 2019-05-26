/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.finance_banking.controller.settings;

import com.itgarden.ERP.module.finance_banking.model.enumvalue.AccountStatus;
import com.itgarden.ERP.module.finance_banking.model.settings.GlAccounts;
import com.itgarden.ERP.module.finance_banking.repository.settings.GlAccountGroupsRepository;
import com.itgarden.ERP.module.finance_banking.repository.settings.GlAccountsRepository;
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
@RequestMapping("/glaccounts")
public class GlAccountsController {

    @Autowired
    GlAccountsRepository glAccountsRepository;

    @Autowired
    GlAccountGroupsRepository glAccountGroupsRepository;

    @RequestMapping("/index")
    public String index(Model model, GlAccounts glAccounts) {

        model.addAttribute("glaccountgroups", glAccountGroupsRepository.findAll());
        model.addAttribute("accountstatus", AccountStatus.values());

        model.addAttribute("glAaccounts", glAccountsRepository.findAll());

        return "module/finance_banking/settings/glaccounts";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid GlAccounts glAccounts, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("glaccountgroups", glAccountGroupsRepository.findAll());

            model.addAttribute("glAaccounts", glAccountsRepository.findAll());
            model.addAttribute("accountstatus", AccountStatus.values());
            return "module/finance_banking/settings/glaccounts";
        }

        glAccountsRepository.save(glAccounts);
        model.addAttribute("success_messages ", "Sucessfully save.");
        return "redirect:/glaccounts/index";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, GlAccounts glAccounts) {

        model.addAttribute("glAccounts", glAccountsRepository.getOne(id));

        model.addAttribute("glaccountgroups", glAccountGroupsRepository.findAll());
        model.addAttribute("accountstatus", AccountStatus.values());
        model.addAttribute("glAaccounts", glAccountsRepository.findAll());

        return "module/finance_banking/settings/glaccounts";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, GlAccounts glAccounts, RedirectAttributes redirectAttrs) {
        glAccountsRepository.deleteById(id);
        return "redirect:/glaccounts/index";
    }

}
