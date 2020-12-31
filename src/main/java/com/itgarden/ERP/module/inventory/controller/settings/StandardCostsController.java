/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.controller.settings;

import com.itgarden.ERP.module.inventory.model.settings.Items;
import com.itgarden.ERP.module.inventory.model.settings.StandardCosts;
import com.itgarden.ERP.module.inventory.repository.settings.StandardCostsRepository;
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
@RequestMapping("/standardcosts")
public class StandardCostsController {

    @Autowired
    StandardCostsRepository standardCostsRepository;

    @RequestMapping("/url")
    public String page(Model model) {

        model.addAttribute("attribute", "value");

        return "view.name";
    }

    
    @RequestMapping("/savebyitem/{id}")
    public String saveByItem(Model model, @PathVariable Long id, @Valid StandardCosts standardCosts, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {

        }
        standardCostsRepository.save(standardCosts);
        
        redirectAttrs.addFlashAttribute("success_messages", " Successfully Save.");
        
        return "redirect:/items/details/{id}";
    }
    
    

    @RequestMapping(value = "/delete/{id}")
    @ResponseBody
    public String delete(Model model, @PathVariable Long id, Items items, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("success_messages", " Successfully Delete.");
        standardCostsRepository.deleteById(id);
        return "";
    }
    
    

}
