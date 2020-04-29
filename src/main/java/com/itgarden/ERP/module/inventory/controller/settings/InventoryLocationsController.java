/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.controller.settings;

import com.itgarden.ERP.module.inventory.model.settings.InventoryLocations;
import com.itgarden.ERP.module.inventory.repository.settings.InventoryLocationsRepository;
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
@RequestMapping("/inventorylocations")
public class InventoryLocationsController {

    @Autowired
    InventoryLocationsRepository inventoryLocationsRepository;

    @RequestMapping("/index")
    public String page(Model model, InventoryLocations inventoryLocations) {
        model.addAttribute("locationlist", inventoryLocationsRepository.findAll());
        return "module/inventory/settings/inventorylocations";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid InventoryLocations inventoryLocations, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("locationlist", inventoryLocationsRepository.findAll());
            return "module/inventory/settings/units";
        }
        inventoryLocationsRepository.save(inventoryLocations);
        redirectAttrs.addFlashAttribute("success_messages", " Successfully Save.");
        return "redirect:/inventorylocations/index";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, InventoryLocations inventoryLocations) {
        model.addAttribute("inventoryLocations", inventoryLocationsRepository.getOne(id));
        model.addAttribute("locationlist", inventoryLocationsRepository.findAll());
        return "module/inventory/settings/inventorylocations";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, InventoryLocations inventoryLocations, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("success_messages", " Successfully Delete.");
        inventoryLocationsRepository.deleteById(id);
        return "redirect:/inventorylocations/index";
    }

}
