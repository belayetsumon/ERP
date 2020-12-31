/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.controller.settings;

import com.itgarden.ERP.module.inventory.model.settings.ReorderLevels;
import com.itgarden.ERP.module.inventory.model.settings.Units;
import com.itgarden.ERP.module.inventory.repository.settings.ReorderLevelsRepository;
import com.itgarden.ERP.module.inventory.settings.services.InventoryLocationsService;
import com.itgarden.ERP.module.inventory.settings.services.ItemService;
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
@RequestMapping("/reorderlevels")
public class ReorderLevelsController {

    @Autowired
    ReorderLevelsRepository reorderLevelsRepository;

    @Autowired
    ItemService itemService;

    @Autowired
    InventoryLocationsService inventoryLocationsService;

    @RequestMapping("/index")
    public String index(Model model, ReorderLevels reorderLevels) {
        model.addAttribute("rllist", reorderLevelsRepository.findAll());
        model.addAttribute("locList", inventoryLocationsService.activelocationlist());
        model.addAttribute("itemlist", itemService.allActiveItemList(false));
        return "module/inventory/settings/reorderlevels";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid ReorderLevels reorderLevels, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("rllist", reorderLevelsRepository.findAll());
            model.addAttribute("locList", inventoryLocationsService.activelocationlist());
            model.addAttribute("itemlist", itemService.allActiveItemList(false));
            return "module/inventory/settings/reorderlevels";
        }
        reorderLevelsRepository.save(reorderLevels);
        model.addAttribute("success_messages ", "Sucessfully save.");
        return "redirect:/reorderlevels/index";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, ReorderLevels reorderLevels) {
        model.addAttribute("reorderLevels", reorderLevelsRepository.getOne(id));

       model.addAttribute("rllist", reorderLevelsRepository.findAll());

        model.addAttribute("locList", inventoryLocationsService.activelocationlist());

        model.addAttribute("itemlist", itemService.allActiveItemList(false));
        return "module/inventory/settings/reorderlevels";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, ReorderLevels reorderLevels, RedirectAttributes redirectAttrs) {
        reorderLevelsRepository.deleteById(id);
        return "redirect:/reorderlevels/index";
    }

}
