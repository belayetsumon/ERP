/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.fixed_assets.controller.settings;

import com.itgarden.ERP.module.finance_banking.model.settings.BankAccounts;
import com.itgarden.ERP.module.fixed_assets.model.enulvalue.FixedAssetStutas;
import com.itgarden.ERP.module.fixed_assets.model.settings.FixedAssetsClasses;
import com.itgarden.ERP.module.fixed_assets.repository.settings.FixedAssetsClassesRepository;
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
@RequestMapping("/fixedassetsclasses")
public class FixedAssetsClassesControlle {

    @Autowired
    FixedAssetsClassesRepository fixedAssetsClassesRepository;

    @RequestMapping("/index")
    public String index(Model model, FixedAssetsClasses fixedAssetsClasses) {
        model.addAttribute("fixedassetsclasses", fixedAssetsClassesRepository.findAll());
        model.addAttribute("stutas", FixedAssetStutas.values());

        return "module/fixed_assets/settings/fixedassetsclasses";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid FixedAssetsClasses fixedAssetsClasses, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("fixedassetsclasses", fixedAssetsClassesRepository.findAll());
            model.addAttribute("stutas", FixedAssetStutas.values());
            return "module/fixed_assets/settings/fixedassetsclasses";
        }
        fixedAssetsClassesRepository.save(fixedAssetsClasses);
        model.addAttribute("success_messages ", "Sucessfully save.");
        return "redirect:/fixedassetsclasses/index";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, FixedAssetsClasses fixedAssetsClasses) {
        
        model.addAttribute("fixedAssetsClasses", fixedAssetsClassesRepository.getOne(id));
        model.addAttribute("fixedassetsclasses", fixedAssetsClassesRepository.findAll());
        model.addAttribute("stutas", FixedAssetStutas.values());
        return "module/fixed_assets/settings/fixedassetsclasses";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, FixedAssetsClasses fixedAssetsClasses, RedirectAttributes redirectAttrs) {
        fixedAssetsClassesRepository.deleteById(id);
        return "redirect:/fixedassetsclasses/index";
    }

}
