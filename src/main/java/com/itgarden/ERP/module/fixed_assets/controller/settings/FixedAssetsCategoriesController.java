/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.fixed_assets.controller.settings;

import com.itgarden.ERP.module.finance_banking.repository.settings.GlAccountsRepository;
import com.itgarden.ERP.module.fixed_assets.model.enulvalue.FixedAssetStutas;
import com.itgarden.ERP.module.fixed_assets.model.settings.FixedAssetsCategories;
import com.itgarden.ERP.module.fixed_assets.repository.settings.FixedAssetsCategoriesRepository;
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
@RequestMapping("/fixedassetscategories")
public class FixedAssetsCategoriesController {

    @Autowired
    FixedAssetsCategoriesRepository fixedAssetsCategoriesRepository;
    @Autowired
    ItemTaxTypesRepository itemTaxTypesRepository;

    @Autowired
    UnitsRepository unitsRepository;

    @Autowired
    GlAccountsRepository glAccountsRepository;

    @RequestMapping("/index")
    public String index(Model model, FixedAssetsCategories fixedAssetsCategories) {
        model.addAttribute("fixedassetscategories", fixedAssetsCategoriesRepository.findAll());
        model.addAttribute("itemtaxtypes", itemTaxTypesRepository.findAll());
        model.addAttribute("units", unitsRepository.findAll());
        model.addAttribute("glaccounts", glAccountsRepository.findAll());
        model.addAttribute("ststus", FixedAssetStutas.values());
        model.addAttribute("yesno", YesNo.values());
        return "module/fixed_assets/settings/fixedassetscategories";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid FixedAssetsCategories fixedAssetsCategories, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("fixedassetscategories", fixedAssetsCategoriesRepository.findAll());
            model.addAttribute("itemtaxtypes", itemTaxTypesRepository.findAll());
            model.addAttribute("units", unitsRepository.findAll());
            model.addAttribute("glaccounts", glAccountsRepository.findAll());
            model.addAttribute("ststus", FixedAssetStutas.values());
            model.addAttribute("yesno", YesNo.values());
            return "module/fixed_assets/settings/fixedassetscategories";
        }
        fixedAssetsCategoriesRepository.save(fixedAssetsCategories);
        model.addAttribute("success_messages ", "Sucessfully save.");
        return "redirect:/fixedassetscategories/index";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, FixedAssetsCategories fixedAssetsCategories) {

        model.addAttribute("fixedAssetsCategories", fixedAssetsCategoriesRepository.getOne(id));
        model.addAttribute("fixedassetscategories", fixedAssetsCategoriesRepository.findAll());
        model.addAttribute("itemtaxtypes", itemTaxTypesRepository.findAll());
        model.addAttribute("units", unitsRepository.findAll());
        model.addAttribute("glaccounts", glAccountsRepository.findAll());
        model.addAttribute("ststus", FixedAssetStutas.values());
        model.addAttribute("yesno", YesNo.values());
        return "module/fixed_assets/settings/fixedassetscategories";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, FixedAssetsCategories fixedAssetsCategories, RedirectAttributes redirectAttrs) {
        fixedAssetsCategoriesRepository.deleteById(id);
        return "redirect:/fixedassetscategories/index";
    }
    

}
