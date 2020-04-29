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

import com.itgarden.ERP.module.sales.model.settings.SalesPersons;
import com.itgarden.ERP.module.sales.repository.settings.SalesPersonsRepository;



/**
 *
 * @author User
 */
@Controller
@RequestMapping("/salespersons")
public class SalesPersonsController {

	@Autowired
	SalesPersonsRepository salesPersonsRepository;

	@RequestMapping("/index")
	public String index(Model model, SalesPersons salesPersons) {
		model.addAttribute("salespersonslist", salesPersonsRepository.findAll());
		return "module/sales/settings/salespersons";
	}

	@RequestMapping("/save")
	public String save(Model model, @Valid SalesPersons salesPersons, BindingResult bindingResult,
			RedirectAttributes redirectAttrs) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("salespersonslist", salesPersonsRepository.findAll());
			return "module/sales/settings/salespersons";
		}
		salesPersonsRepository.save(salesPersons);
		redirectAttrs.addFlashAttribute("success_messages", " Successfully Save.");
		return "redirect:/salespersons/index";
	}

	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable Long id, SalesPersons salesPersons) {
		model.addAttribute("salesPersons", salesPersonsRepository.getOne(id));
		model.addAttribute("salespersonslist", salesPersonsRepository.findAll());
		return "module/sales/settings/salespersons";
	}

	@GetMapping(value = "/delete/{id}")
	public String delete(Model model, @PathVariable Long id, SalesPersons salesPersons, RedirectAttributes redirectAttrs) {
		redirectAttrs.addFlashAttribute("success_messages", " Successfully Delete.");
		salesPersonsRepository.deleteById(id);
		return "redirect:/salespersons/index";
	}
}
