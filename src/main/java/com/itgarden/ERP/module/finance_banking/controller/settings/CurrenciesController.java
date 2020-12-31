/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.finance_banking.controller.settings;

import com.itgarden.ERP.module.finance_banking.model.settings.Currencies;
import com.itgarden.ERP.module.finance_banking.repository.settings.CurrenciesRepository;
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
@RequestMapping("/currencies")
public class CurrenciesController {

    @Autowired
    CurrenciesRepository currenciesRepository;

    @RequestMapping("/index")
    public String index(Model model, Currencies currencies) {
        model.addAttribute("currencieslist ", currenciesRepository.findAll());
        return "module/finance_banking/settings/currencies";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid Currencies currencies, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("currencieslist ", currenciesRepository.findAll());
            return "module/finance_banking/settings/currencies";
        }

        currenciesRepository.save(currencies);

        model.addAttribute("success_messages ", "Sucessfully save.");

        return "redirect:/currencies/index";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, Currencies currencies) {

        model.addAttribute("currencies", currenciesRepository.getOne(id));
        model.addAttribute("currencieslist ", currenciesRepository.findAll());

        return "module/finance_banking/settings/currencies";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, Currencies currencies, RedirectAttributes redirectAttrs) {

        currenciesRepository.deleteById(id);

        redirectAttrs.addFlashAttribute("success_messages ", "Sucessfully Delete.");

        return "redirect:/currencies/index";
    }

}
