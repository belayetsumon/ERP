/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.controller.settings;

import com.itgarden.ERP.module.finance_banking.repository.settings.GlAccountsRepository;
import com.itgarden.ERP.module.inventory.model.enumvalue.ItemType;
import com.itgarden.ERP.module.inventory.model.enumvalue.Itemstatus;
import com.itgarden.ERP.module.inventory.model.settings.ItemCategories;
import com.itgarden.ERP.module.inventory.repository.settings.ItemCategoriesRepository;
import com.itgarden.ERP.module.inventory.repository.settings.UnitsRepository;
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
@RequestMapping("/itemcategories")
public class ItemCategoriesController {

    @Autowired
    ItemCategoriesRepository itemCategoriesRepository;

    @Autowired
    ItemTaxTypesRepository itemTaxTypesRepository;

    @Autowired
    UnitsRepository unitsRepository;

    @Autowired
    GlAccountsRepository glAccountsRepository;

    @RequestMapping("/index")
    public String page(Model model, ItemCategories itemCategories) {

        model.addAttribute("itemCategorieslist", itemCategoriesRepository.findAll());
        model.addAttribute("itemtaxtypes", itemTaxTypesRepository.findAll());
        model.addAttribute("itemtypes", ItemType.values());
        model.addAttribute("units", unitsRepository.findAll());
        model.addAttribute("glaccounts", glAccountsRepository.findAll());
        model.addAttribute("ststus", Itemstatus.values());
        model.addAttribute("yesno", YesNo.values());

        return "module/inventory/settings/itemcategories";

    }

    @RequestMapping("/save")
    public String save(Model model, @Valid ItemCategories itemCategories, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("itemCategorieslist", itemCategoriesRepository.findAll());
            model.addAttribute("itemtaxtypes", itemTaxTypesRepository.findAll());
            model.addAttribute("itemtypes", ItemType.values());
            model.addAttribute("units", unitsRepository.findAll());
            model.addAttribute("glaccounts", glAccountsRepository.findAll());
            model.addAttribute("ststus", Itemstatus.values());
            model.addAttribute("yesno", YesNo.values());
            return "module/inventory/settings/itemcategories";
        }
        itemCategoriesRepository.save(itemCategories);
        redirectAttrs.addFlashAttribute("success_messages", " Successfully Save.");
        return "redirect:/itemcategories/index";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, ItemCategories itemCategories) {
        model.addAttribute("itemCategories", itemCategoriesRepository.getOne(id));
        model.addAttribute("itemCategorieslist", itemCategoriesRepository.findAll());
        model.addAttribute("itemtaxtypes", itemTaxTypesRepository.findAll());
        model.addAttribute("itemtypes", ItemType.values());
        model.addAttribute("units", unitsRepository.findAll());
        model.addAttribute("glaccounts", glAccountsRepository.findAll());
        model.addAttribute("ststus", Itemstatus.values());
        model.addAttribute("yesno", YesNo.values());
        return "module/inventory/settings/itemcategories";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, ItemCategories itemCategories, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("success_messages", " Successfully Delete.");
        itemCategoriesRepository.deleteById(id);
        return "redirect:/itemcategories/index";
    }

}
