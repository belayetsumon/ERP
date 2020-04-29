/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.settings.controller.company_setup;

import com.itgarden.ERP.module.fixed_assets.model.settings.FixedAssetsCategories;
import com.itgarden.ERP.module.settings.model.company_setup.ItemTaxTypes;
import com.itgarden.ERP.module.settings.model.enumvalue.YesNo;
import com.itgarden.ERP.module.settings.repository.company_setup.ItemTaxTypesRepository;
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
@RequestMapping("/itemtaxtypes")
public class ItemTaxTypesController {

    @Autowired
    ItemTaxTypesRepository itemTaxTypesRepository;

    @RequestMapping("/index")
    public String index(Model model, ItemTaxTypes itemTaxTypes) {
        model.addAttribute("itemtaxtypes", itemTaxTypesRepository.findAll());
        model.addAttribute("yesno", YesNo.values());
        return "module/settings/company_setup/itemtaxtypes";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid ItemTaxTypes itemTaxTypes, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("itemtaxtypes", itemTaxTypesRepository.findAll());
            model.addAttribute("yesno", YesNo.values());
            return "module/settings/company_setup/itemtaxtypes";
        }
        itemTaxTypesRepository.save(itemTaxTypes);
        model.addAttribute("success_messages ", "Sucessfully save.");
        return "redirect:/itemtaxtypes/index";
    }

    @RequestMapping("/edit/{id}")
    public String index(Model model, @PathVariable Long id, ItemTaxTypes itemTaxTypes) {
        model.addAttribute("itemTaxTypes", itemTaxTypesRepository.getOne(id));
        model.addAttribute("itemtaxtypes", itemTaxTypesRepository.findAll());
        model.addAttribute("yesno", YesNo.values());
        return "module/settings/company_setup/itemtaxtypes";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, ItemTaxTypes itemTaxTypes, RedirectAttributes redirectAttrs) {
        itemTaxTypesRepository.deleteById(id);
        return "redirect:/itemtaxtypes/index";
    }

}
