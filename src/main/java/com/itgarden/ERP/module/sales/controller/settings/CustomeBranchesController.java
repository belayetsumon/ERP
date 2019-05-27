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

import com.itgarden.ERP.module.finance_banking.repository.settings.GlAccountsRepository;
import com.itgarden.ERP.module.inventory.repository.settings.InventoryLocationsRepository;
import com.itgarden.ERP.module.sales.model.enumvalue.SalesGroups;
import com.itgarden.ERP.module.sales.model.settings.CustomerBranches;
import com.itgarden.ERP.module.sales.model.settings.SalesPersons;
import com.itgarden.ERP.module.sales.repository.settings.CustomerBranchesRepository;
import com.itgarden.ERP.module.sales.repository.settings.SalesAreasRepository;
import com.itgarden.ERP.module.sales.repository.settings.SalesPersonsRepository;
import com.itgarden.ERP.module.settings.repository.company_setup.TaxGroupsRepository;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/customebranches")
public class CustomeBranchesController {

	@Autowired
	CustomerBranchesRepository customerBranchesRepository;

	@Autowired
	SalesPersonsRepository salesPersonsRepository;

	@Autowired
	SalesAreasRepository salesAreasRepository;

	@Autowired
	InventoryLocationsRepository inventoryLocationsRepository;

	@Autowired
	TaxGroupsRepository taxGroupsRepository;

	@Autowired
	GlAccountsRepository glAccountsRepository;

	@RequestMapping("/index")
	public String index(Model model, CustomerBranches customerBranches) {

		model.addAttribute("customerbrancheslist", customerBranchesRepository.findAll());

		return "module/sales/settings/customer_branches_index";

	}

	@RequestMapping("/create")
	public String create(Model model, CustomerBranches customerBranches) {

		model.addAttribute("salespersonslist", salesPersonsRepository.findAll());
		
		model.addAttribute("salesareaslist", salesAreasRepository.findAll());
		
		model.addAttribute("salesareasgroup", SalesGroups.values());
		
		model.addAttribute("inventorylocationslist", inventoryLocationsRepository.findAll());
		
		model.addAttribute("taxgroupslist", taxGroupsRepository.findAll());
		
		model.addAttribute("glaccountslist", glAccountsRepository.findAll());
		
		model.addAttribute("customerbrancheslist", customerBranchesRepository.findAll());

		return "module/sales/settings/customer_branches_add";

	}

	@RequestMapping("/save")
	public String save(Model model, @Valid CustomerBranches customerBranches, BindingResult bindingResult,
			RedirectAttributes redirectAttrs) {

		if (bindingResult.hasErrors()) {

			model.addAttribute("salespersonslist", salesPersonsRepository.findAll());
			
			model.addAttribute("salesareaslist", salesAreasRepository.findAll());
			
			model.addAttribute("salesareasgroup", SalesGroups.values());
			
			model.addAttribute("inventorylocationslist", inventoryLocationsRepository.findAll());
			
			model.addAttribute("taxgroupslist", taxGroupsRepository.findAll());
			
			model.addAttribute("glaccountslist", glAccountsRepository.findAll());
			
			model.addAttribute("customerbrancheslist", customerBranchesRepository.findAll());

			return "module/sales/settings/customer_branches_add";
		}
		customerBranchesRepository.save(customerBranches);

		redirectAttrs.addFlashAttribute("success_messages", " Successfully Save.");

		return "redirect:/customebranches/index";
	}

	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable Long id, CustomerBranches customerBranches) {
		
		model.addAttribute("salesPersons", customerBranchesRepository.getOne(id));
		
		model.addAttribute("salespersonslist", salesPersonsRepository.findAll());
		
		model.addAttribute("salesareaslist", salesAreasRepository.findAll());
		
		model.addAttribute("salesareasgroup", SalesGroups.values());
		
		model.addAttribute("inventorylocationslist", inventoryLocationsRepository.findAll());
		
		model.addAttribute("taxgroupslist", taxGroupsRepository.findAll());
		
		model.addAttribute("glaccountslist", glAccountsRepository.findAll());
		
		model.addAttribute("customerbrancheslist", customerBranchesRepository.findAll());

		return "module/sales/settings/customer_branches_add";
	}

	@GetMapping(value = "/delete/{id}")
	public String delete(Model model, @PathVariable Long id, CustomerBranches customerBranches,
			RedirectAttributes redirectAttrs) {
		redirectAttrs.addFlashAttribute("success_messages", " Successfully Delete.");
		customerBranchesRepository.deleteById(id);
		return "redirect:/customebranches/index";
	}

}
