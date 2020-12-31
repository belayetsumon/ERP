/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.settings.controller.company_setup;

import com.itgarden.ERP.module.settings.model.company_setup.Taxes;
import com.itgarden.ERP.module.settings.model.company_setup.TransactionsType;
import com.itgarden.ERP.module.settings.repository.company_setup.TransactionsTypeRepository;
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
@RequestMapping("/transactionstype")
public class TransactionsTypeController {

    @Autowired
    TransactionsTypeRepository transactionsTypeRepository;

    @RequestMapping("/index")
    public String index(Model model, TransactionsType transactionsType) {
        model.addAttribute("transactionslist", transactionsTypeRepository.findAll());
        return "module/settings/company_setup/transactionstype";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid TransactionsType transactionsType, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("transactionslist", transactionsTypeRepository.findAll());
            return "module/settings/company_setup/transactionstype";

        }
        transactionsTypeRepository.save(transactionsType);
        redirectAttrs.addFlashAttribute("success_messages ", "Sucessfully save.");
        return "redirect:/transactionstype/index";
    }

    @RequestMapping("/edit/{id}")
    public String index(Model model, @PathVariable Long id, TransactionsType transactionsType) {
        model.addAttribute("transactionsType", transactionsTypeRepository.getOne(id));
        model.addAttribute("transactionslist", transactionsTypeRepository.findAll());
        return "module/settings/company_setup/transactionstype";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, TransactionsType transactionsType, RedirectAttributes redirectAttrs) {
        transactionsTypeRepository.deleteById(id);
        redirectAttrs.addFlashAttribute("success_messages ", "Sucessfully Delete.");
        return "redirect:/transactionstype/index";
    }

}
