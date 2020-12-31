/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.settings.controller.miscellaneous;

import com.itgarden.ERP.module.settings.model.miscellaneous.ShippingCompany;
import com.itgarden.ERP.module.settings.repository.miscellaneous.ShippingCompanyRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("/shippingcompany")
public class ShippingCompanyController {
    
    @Autowired
    ShippingCompanyRepository shippingCompanyRepository;

    @RequestMapping("/index")
    public String index(Model model,ShippingCompany shippingCompany) {
      model.addAttribute("shippingcompanylist", shippingCompanyRepository.findAll());
        return "module/settings/miscellaneous/shippingcompany";
    }
    
    @RequestMapping("/save")
    public String save(Model model, @Valid ShippingCompany shippingCompany, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("shippingcompanylist", shippingCompanyRepository.findAll());
             return "module/settings/miscellaneous/shippingcompany";

        }
        shippingCompanyRepository.save(shippingCompany);
        redirectAttrs.addFlashAttribute("success_messages ", "Sucessfully save.");
        return "redirect:/shippingcompany/index";
    }

    @RequestMapping("/edit/{id}")
    public String index(Model model, @PathVariable Long id, ShippingCompany shippingCompany) {
        model.addAttribute("shippingCompany", shippingCompanyRepository.getOne(id));
        model.addAttribute("shippingcompanylist", shippingCompanyRepository.findAll());
         return "module/settings/miscellaneous/shippingcompany";
    }

    @GetMapping(value = "/delete/{id}")
    @Transactional
    public String delete(Model model, @PathVariable Long id, ShippingCompany shippingCompany, RedirectAttributes redirectAttrs) {
        shippingCompanyRepository.deleteById(id);
        redirectAttrs.addFlashAttribute("success_messages ", "Sucessfully Delete.");
        return "redirect:/shippingcompany/index";
    }

}
