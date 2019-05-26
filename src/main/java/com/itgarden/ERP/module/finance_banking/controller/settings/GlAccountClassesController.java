/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.finance_banking.controller.settings;

import com.itgarden.ERP.module.finance_banking.model.enumvalue.ClassType;
import com.itgarden.ERP.module.finance_banking.model.settings.GlAccountClasses;
import com.itgarden.ERP.module.finance_banking.repository.settings.GlAccountClassesRepository;
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
@RequestMapping("/glaccountclasses")
public class GlAccountClassesController {

    @Autowired
    GlAccountClassesRepository glAccountClassesRepository;

    @RequestMapping("/glaccountclasses")
    public String index(Model model, GlAccountClasses glAccountClasses) {
        model.addAttribute("attribute", "value");
        model.addAttribute("classtype", ClassType.values());
        model.addAttribute("glAccountClassesList", glAccountClassesRepository.findAll());
        return "module/finance_banking/settings/glaccountclasses";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid GlAccountClasses glAccountClasses, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("classtype", ClassType.values());
            model.addAttribute("glAccountClassesList", glAccountClassesRepository.findAll());
            return "module/finance_banking/settings/glaccountclasses";
        }
        glAccountClassesRepository.save(glAccountClasses);
        model.addAttribute("success_messages ", "Sucessfully save.");
        return "redirect:/glaccountclasses/glaccountclasses";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id,  GlAccountClasses glAccountClasses) {
        model.addAttribute("glAccountClasses", glAccountClassesRepository.getOne(id));
        model.addAttribute("classtype", ClassType.values());
        model.addAttribute("glAccountClassesList", glAccountClassesRepository.findAll());
        return "module/finance_banking/settings/glaccountclasses";
    }
    
     @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id,  GlAccountClasses glAccountClasses, RedirectAttributes redirectAttrs) {
           
        glAccountClassesRepository.deleteById(id);
        return "redirect:/glaccountclasses/glaccountclasses";
    }
    

}
