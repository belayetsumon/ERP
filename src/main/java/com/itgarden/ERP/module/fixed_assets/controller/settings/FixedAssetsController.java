/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.fixed_assets.controller.settings;

import com.itgarden.ERP.module.finance_banking.repository.settings.GlAccountsRepository;
import com.itgarden.ERP.module.fixed_assets.model.enulvalue.DepreciationMethod;
import com.itgarden.ERP.module.fixed_assets.model.enulvalue.FixedAssetStutas;
import com.itgarden.ERP.module.fixed_assets.model.settings.FixedAssets;
import com.itgarden.ERP.module.fixed_assets.model.settings.FixedAssetsCategories;
import com.itgarden.ERP.module.fixed_assets.repository.settings.FixedAssetsCategoriesRepository;
import com.itgarden.ERP.module.fixed_assets.repository.settings.FixedAssetsClassesRepository;
import com.itgarden.ERP.module.fixed_assets.repository.settings.FixedAssetsLocationsRepository;
import com.itgarden.ERP.module.fixed_assets.repository.settings.FixedAssetsRepository;
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
@RequestMapping("/fixedasset")
public class FixedAssetsController {

    @Autowired
    FixedAssetsRepository fixedAssetsRepository;
    @Autowired
    FixedAssetsClassesRepository fixedAssetsClassesRepository;

    @Autowired
    FixedAssetsCategoriesRepository fixedAssetsCategoriesRepository;

    @Autowired
    FixedAssetsLocationsRepository fixedAssetsLocationsRepository;
    @Autowired
    ItemTaxTypesRepository itemTaxTypesRepository;

    @Autowired
    UnitsRepository unitsRepository;

    @Autowired
    GlAccountsRepository glAccountsRepository;

    @RequestMapping("/index")
    public String index(Model model, FixedAssets fixedAssets) {
        model.addAttribute("fixedassets", fixedAssetsRepository.findAll());
        model.addAttribute("fixedassetsclasses", fixedAssetsClassesRepository.findAll());
        model.addAttribute("stutas", FixedAssetStutas.values());
        model.addAttribute("fixedassetscategories", fixedAssetsCategoriesRepository.findAll());
        model.addAttribute("fixedassetslocations", fixedAssetsLocationsRepository.findAll());
        model.addAttribute("itemtaxtypes", itemTaxTypesRepository.findAll());
        model.addAttribute("units", unitsRepository.findAll());
        model.addAttribute("glaccounts", glAccountsRepository.findAll());
        model.addAttribute("depreciationdethod", DepreciationMethod.values());
        model.addAttribute("ststus", FixedAssetStutas.values());
        model.addAttribute("yesno", YesNo.values());
        model.addAttribute("attribute", "value");
        return "module/fixed_assets/settings/fixedassets";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid FixedAssets fixedAssets, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("fixedassets", fixedAssetsRepository.findAll());
            model.addAttribute("fixedassetsclasses", fixedAssetsClassesRepository.findAll());
            model.addAttribute("stutas", FixedAssetStutas.values());
            model.addAttribute("fixedassetscategories", fixedAssetsCategoriesRepository.findAll());
            model.addAttribute("fixedassetslocations", fixedAssetsLocationsRepository.findAll());
            model.addAttribute("itemtaxtypes", itemTaxTypesRepository.findAll());
            model.addAttribute("units", unitsRepository.findAll());
            model.addAttribute("glaccounts", glAccountsRepository.findAll());
            model.addAttribute("depreciationdethod", DepreciationMethod.values());
            model.addAttribute("ststus", FixedAssetStutas.values());
            model.addAttribute("yesno", YesNo.values());
            model.addAttribute("attribute", "value");
            return "module/fixed_assets/settings/fixedassets";

        }
        fixedAssetsRepository.save(fixedAssets);
        model.addAttribute("success_messages ", "Sucessfully save.");
        return "redirect:/fixedasset/index";

    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, FixedAssets fixedAssets) {
        model.addAttribute("fixedAssets", fixedAssetsRepository.getOne(id));
        model.addAttribute("fixedassets", fixedAssetsRepository.findAll());
        model.addAttribute("fixedassetsclasses", fixedAssetsClassesRepository.findAll());
        model.addAttribute("stutas", FixedAssetStutas.values());
        model.addAttribute("fixedassetscategories", fixedAssetsCategoriesRepository.findAll());
        model.addAttribute("fixedassetslocations", fixedAssetsLocationsRepository.findAll());
        model.addAttribute("itemtaxtypes", itemTaxTypesRepository.findAll());
        model.addAttribute("units", unitsRepository.findAll());
        model.addAttribute("glaccounts", glAccountsRepository.findAll());
        model.addAttribute("depreciationdethod", DepreciationMethod.values());
        model.addAttribute("ststus", FixedAssetStutas.values());
        model.addAttribute("yesno", YesNo.values());
        model.addAttribute("attribute", "value");
        return "module/fixed_assets/settings/fixedassets";
    }
    
     @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, FixedAssets fixedAssets, RedirectAttributes redirectAttrs) {
        fixedAssetsRepository.deleteById(id);
        return "redirect:/fixedasset/index";
    }

}
