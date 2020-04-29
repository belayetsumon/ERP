/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.finance_banking.controller.settings;

import com.itgarden.ERP.module.finance_banking.model.enumvalue.AccountStatus;
import com.itgarden.ERP.module.finance_banking.model.enumvalue.AccountType;
import com.itgarden.ERP.module.finance_banking.model.settings.BankAccounts;
import com.itgarden.ERP.module.finance_banking.model.settings.GlAccounts;
import com.itgarden.ERP.module.finance_banking.repository.settings.BankAccountsRepository;
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
@RequestMapping("/bankaccounts")
public class BankAccountsController {

    @Autowired
    BankAccountsRepository bankAccountsRepository;
    @Autowired
    GlAccountsRepository glAccountsRepository;

    @RequestMapping("/index")
    public String index(Model model, BankAccounts bankAccounts) {
        model.addAttribute("accounttype", AccountType.values());
        model.addAttribute("bankaccounts", bankAccountsRepository.findAll());
        model.addAttribute("glaccounts", glAccountsRepository.findAll());

        return "module/finance_banking/settings/bankAccounts";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid BankAccounts bankAccounts, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("accounttype", AccountType.values());
            model.addAttribute("bankaccounts", bankAccountsRepository.findAll());
            model.addAttribute("glaccounts", glAccountsRepository.findAll());
            return "module/finance_banking/settings/bankAccounts";
        }

        bankAccountsRepository.save(bankAccounts);
        model.addAttribute("success_messages ", "Sucessfully save.");
        return "redirect:/bankaccounts/index";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, BankAccounts bankAccounts) {

        model.addAttribute("bankAccounts", bankAccountsRepository.getOne(id));

        model.addAttribute("accounttype", AccountType.values());
        model.addAttribute("bankaccounts", bankAccountsRepository.findAll());
        model.addAttribute("glaccounts", glAccountsRepository.findAll());

        return "module/finance_banking/settings/bankAccounts";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, BankAccounts bankAccounts, RedirectAttributes redirectAttrs) {
        bankAccountsRepository.deleteById(id);
        return "redirect:/bankaccounts/index";
    }

}
