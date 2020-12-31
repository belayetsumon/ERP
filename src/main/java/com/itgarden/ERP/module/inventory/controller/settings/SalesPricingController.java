/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.controller.settings;

import com.itgarden.ERP.module.inventory.model.enumvalue.SalesType;
import com.itgarden.ERP.module.inventory.model.settings.Items;
import com.itgarden.ERP.module.inventory.model.settings.SalesPricing;
import com.itgarden.ERP.module.inventory.repository.settings.SalesPricingRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/salespricing")
public class SalesPricingController {

    @Autowired
    SalesPricingRepository salesPricingRepository;

    @RequestMapping("/url")
    public String page(Model model) {
        model.addAttribute("attribute", "value");
        return "view.name";
    }

    @RequestMapping("/savebyitem/{id}")
    public String saveByItem(Model model, @PathVariable Long id, @Valid SalesPricing salesPricing, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("salestype", SalesType.values());

        }
        salesPricingRepository.save(salesPricing);

        redirectAttrs.addFlashAttribute("success_messages", " Successfully Save.");

        return "redirect:/items/details/{id}";
    }

    
    @RequestMapping(value="/delete/{id}")
    @ResponseBody
    public String delete(Model model, @PathVariable Long id, Items items, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("success_messages", " Successfully Delete.");
        salesPricingRepository.deleteById(id);
        return "";
    }
}
