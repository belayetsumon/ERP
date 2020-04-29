/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.controller.settings;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itgarden.ERP.module.sales.model.enumvalue.SalesTypeName;
import com.itgarden.ERP.module.sales.model.enumvalue.YesNoEnum;
import com.itgarden.ERP.module.sales.model.settings.SalesPersons;
import com.itgarden.ERP.module.sales.model.settings.SalesTypes;
import com.itgarden.ERP.module.sales.repository.settings.SalesTypesRepository;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/salestypes")
public class SalesTypesController {

	@Autowired
	SalesTypesRepository salesTypesRepository;

	@RequestMapping("/index")
	public String index(Model model, SalesTypes salesTypes) {
		model.addAttribute("salestypeslist", salesTypesRepository.findAll());
		model.addAttribute("yesNo", YesNoEnum.values());
		
		model.addAttribute("salestypename", SalesTypeName.values());
		
		return "module/sales/settings/salestypes";
	}

	@RequestMapping("/save")
	public String save(Model model, @Valid SalesTypes salesTypes, BindingResult bindingResult,
			RedirectAttributes redirectAttrs) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("salestypeslist", salesTypesRepository.findAll());
			model.addAttribute("yesNo", YesNoEnum.values());
			model.addAttribute("salestypename", SalesTypeName.values());
			return "module/sales/settings/salestypes";
		}
		salesTypesRepository.save(salesTypes);
		redirectAttrs.addFlashAttribute("success_messages", " Successfully Save.");
		return "redirect:/salestypes/index";
	}

	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable Long id, SalesTypes salesTypes) {
		model.addAttribute("salesTypes", salesTypesRepository.getOne(id));
		model.addAttribute("salestypeslist", salesTypesRepository.findAll());
		model.addAttribute("yesNo", YesNoEnum.values());
		model.addAttribute("salestypename", SalesTypeName.values());
		return "module/sales/settings/salestypes";
	}

	@GetMapping(value = "/delete/{id}")
	public String delete(Model model, @PathVariable Long id, SalesTypes salesTypes, RedirectAttributes redirectAttrs) {
		redirectAttrs.addFlashAttribute("success_messages", " Successfully Delete.");
		salesTypesRepository.deleteById(id);
		return "redirect:/salestypes/index";
	}

}
