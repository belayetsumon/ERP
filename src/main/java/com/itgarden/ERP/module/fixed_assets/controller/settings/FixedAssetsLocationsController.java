/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.fixed_assets.controller.settings;

import com.itgarden.ERP.module.finance_banking.model.settings.BankAccounts;
import com.itgarden.ERP.module.fixed_assets.model.settings.FixedAssetsLocations;
import com.itgarden.ERP.module.fixed_assets.repository.settings.FixedAssetsLocationsRepository;
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
@RequestMapping("/fixedassetslocations")
public class FixedAssetsLocationsController {

    @Autowired
    FixedAssetsLocationsRepository fixedAssetsLocationsRepository;

    @RequestMapping("/index")
    public String index(Model model, FixedAssetsLocations fixedAssetsLocations) {
        model.addAttribute("fixedassetslocations", fixedAssetsLocationsRepository.findAll());
        return "module/fixed_assets/settings/fixedassetslocations";
    }

    @RequestMapping("/save")
    public String index(Model model, @Valid FixedAssetsLocations fixedAssetsLocations, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("fixedassetslocations", fixedAssetsLocationsRepository.findAll());
            return "module/fixed_assets/settings/fixedassetslocations";
        }
        fixedAssetsLocationsRepository.save(fixedAssetsLocations);
        model.addAttribute("success_messages ", "Sucessfully save.");
        return "redirect:/fixedassetslocations/index";

    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, FixedAssetsLocations fixedAssetsLocations) {

        model.addAttribute("fixedAssetsLocations", fixedAssetsLocationsRepository.getOne(id));
        model.addAttribute("fixedassetslocations", fixedAssetsLocationsRepository.findAll());
        return "module/fixed_assets/settings/fixedassetslocations";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, FixedAssetsLocations fixedAssetsLocations, RedirectAttributes redirectAttrs) {
        fixedAssetsLocationsRepository.deleteById(id);
        return "redirect:/fixedassetslocations/index";
    }

}
