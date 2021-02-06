/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.branch.controller.settings;

import com.itgarden.ERP.module.branch.model.settings.ExpenseType;
import com.itgarden.ERP.module.branch.repository.settings.ExpenseTypeRepository;
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
@RequestMapping("/expensetype")
public class ExpenseTypeController {

    @Autowired
    ExpenseTypeRepository expenseTypeRepository;

    @RequestMapping(value = {"", "/", "/index"})
    public String index(Model model, ExpenseType expenseType) {
        model.addAttribute("list", expenseTypeRepository.findAll());
        return "module/branch/settings/expensetype";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid ExpenseType expenseType, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {

            model.addAttribute("list", expenseTypeRepository.findAll());

            return "module/branch/settings/expensetype";
        }
        expenseTypeRepository.save(expenseType);

        redirectAttrs.addFlashAttribute("success_messages ", "Sucessfully save.");

        return "redirect:/expensetype/index";
    }

    @RequestMapping("/edit/{id}")
    public String index(Model model, @PathVariable Long id, ExpenseType expenseType) {
        model.addAttribute("expenseType", expenseTypeRepository.getOne(id));
        model.addAttribute("list", expenseTypeRepository.findAll());
        return "module/branch/settings/expensetype";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, ExpenseType expenseType, RedirectAttributes redirectAttrs) {
        expenseTypeRepository.deleteById(id);
        return "redirect:/expensetype/index";
    }
}
