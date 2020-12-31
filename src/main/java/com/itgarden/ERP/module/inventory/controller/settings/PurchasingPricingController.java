/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.controller.settings;

import com.itgarden.ERP.module.inventory.model.enumvalue.SalesType;
import com.itgarden.ERP.module.inventory.model.settings.Items;
import com.itgarden.ERP.module.inventory.model.settings.PurchasingPricing;
import com.itgarden.ERP.module.inventory.repository.settings.PurchasingPricingRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/purchasingpricing")
public class PurchasingPricingController {

    @Autowired
    PurchasingPricingRepository purchasingPricingRepository;

    @RequestMapping("/purchasingpricing")
    public String index(Model model) {
        model.addAttribute("attribute", "value");
        return "view.name";
    }

    @RequestMapping("/savebyitem/{id}")
    public String saveByItem(Model model, @PathVariable Long id, @Valid PurchasingPricing purchasingPricing, BindingResult bindingResult, RedirectAttributes redirectAttrs) {

        if (bindingResult.hasErrors()) {

        }

        purchasingPricingRepository.save(purchasingPricing);

        redirectAttrs.addFlashAttribute("success_messages", " Successfully Save.");

        return "redirect:/items/details/{id}#tab_2-2";
    }

    @RequestMapping(value = "/delete/{id}")
    @ResponseBody
    public String delete(Model model, @PathVariable Long id, Items items, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("success_messages", " Successfully Delete.");
        purchasingPricingRepository.deleteById(id);
        return "";
    }

}
