/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.purchases.controller.settings;

import com.itgarden.ERP.module.finance_banking.repository.settings.BankAccountsRepository;
import com.itgarden.ERP.module.finance_banking.repository.settings.GlAccountsRepository;
import com.itgarden.ERP.module.purchases.model.enumvalue.SuppliersStatus;
import com.itgarden.ERP.module.purchases.model.settings.Suppliers;
import com.itgarden.ERP.module.purchases.repository.settings.SuppliersRepository;
import com.itgarden.ERP.module.settings.model.company_setup.TaxGroups;
import com.itgarden.ERP.module.settings.model.enumvalue.YesNo;
import com.itgarden.ERP.module.settings.repository.company_setup.TaxGroupsRepository;
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
@RequestMapping("/suppliers")
public class SuppliersController {

    @Autowired
    SuppliersRepository suppliersRepository;

    @Autowired
    GlAccountsRepository glAccountsRepository;

    @Autowired
    TaxGroupsRepository taxGroupsRepository;

    @Autowired
    BankAccountsRepository bankAccountsRepository;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("supplierslist", suppliersRepository.findAll());
        return "module/purchases/settings/suppliersindex";

    }

    @RequestMapping("/create")
    public String create(Model model, Suppliers suppliers) {
        model.addAttribute("glaccounts", glAccountsRepository.findAll());
        model.addAttribute("status", SuppliersStatus.values());
        model.addAttribute("bankaccount", bankAccountsRepository.findAll());
        model.addAttribute("taxGroup", taxGroupsRepository.findAll());
        model.addAttribute("yesno", YesNo.values());

        return "module/purchases/settings/suppliersadd";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid Suppliers suppliers, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {

            model.addAttribute("glaccounts", glAccountsRepository.findAll());
            model.addAttribute("status", SuppliersStatus.values());
            model.addAttribute("bankaccount", bankAccountsRepository.findAll());
            model.addAttribute("taxGroup", taxGroupsRepository.findAll());
            model.addAttribute("yesno", YesNo.values());
            return "module/purchases/settings/suppliersadd";
        }
        suppliersRepository.save(suppliers);

        redirectAttrs.addFlashAttribute("success_messages", " Successfully Save.");

        return "redirect:/suppliers/index";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, Suppliers suppliers) {
        model.addAttribute("suppliers", suppliersRepository.getOne(id));
        model.addAttribute("glaccounts", glAccountsRepository.findAll());
        model.addAttribute("status", SuppliersStatus.values());
        model.addAttribute("bankaccount", bankAccountsRepository.findAll());
        model.addAttribute("taxGroup", taxGroupsRepository.findAll());
        model.addAttribute("yesno", YesNo.values());
        return "module/purchases/settings/suppliersadd";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, Suppliers suppliers, RedirectAttributes redirectAttrs) {
        suppliersRepository.deleteById(id);
        return "redirect:/suppliers/index";
    }

}
