/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.controller.settings;

import com.itgarden.ERP.module.fixed_assets.model.settings.FixedAssetsCategories;
import com.itgarden.ERP.module.inventory.model.settings.Units;
import com.itgarden.ERP.module.inventory.repository.settings.UnitsRepository;
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
@RequestMapping("/units")
public class UnitsController {

    @Autowired
    UnitsRepository unitsRepository;

    @RequestMapping("/index")
    public String index(Model model, Units units) {
        model.addAttribute("unitslist", unitsRepository.findAll());
        return "module/inventory/settings/units";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid Units units, BindingResult bindingResult,RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("unitslist", unitsRepository.findAll());
            return "module/inventory/settings/units";
        }
        unitsRepository.save(units);
        model.addAttribute("success_messages ", "Sucessfully save.");
        return "redirect:/units/index";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, Units units) {
        model.addAttribute("units", unitsRepository.getOne(id));
        model.addAttribute("unitslist", unitsRepository.findAll());
        return "module/inventory/settings/units";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, Units units, RedirectAttributes redirectAttrs) {
        unitsRepository.deleteById(id);
        return "redirect:/units/index";
    }

}
