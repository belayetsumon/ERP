/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.controller.settings;

import com.itgarden.ERP.module.finance_banking.repository.settings.GlAccountsRepository;
import com.itgarden.ERP.module.inventory.repository.settings.InventoryLocationsRepository;
import com.itgarden.ERP.module.sales.model.enumvalue.SalesGroups;
import com.itgarden.ERP.module.sales.model.enumvalue.SalesTypeName;
import com.itgarden.ERP.module.sales.model.settings.Customers;
import com.itgarden.ERP.module.sales.repository.settings.CreditStatusSetupRepository;
import com.itgarden.ERP.module.sales.repository.settings.CustomerBranchesRepository;
import com.itgarden.ERP.module.sales.repository.settings.CustomersRepository;
import com.itgarden.ERP.module.sales.repository.settings.SalesAreasRepository;
import com.itgarden.ERP.module.sales.repository.settings.SalesPersonsRepository;
import com.itgarden.ERP.module.settings.repository.company_setup.TaxGroupsRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    CustomersRepository customersRepository;

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

    @Autowired
    CreditStatusSetupRepository creditStatusSetupRepository;

    @RequestMapping("/index")
    public String page(Model model) {
        model.addAttribute("attribute", "value");
        return "module/sales/settings/customer_index";
    }

    @RequestMapping("/create")
    public String create(Model model, Customers customers) {

        model.addAttribute("salespersonslist", salesPersonsRepository.findAll());

        model.addAttribute("salesareaslist", salesAreasRepository.findAll());

        model.addAttribute("creditstatuslist", creditStatusSetupRepository.findAll());

        model.addAttribute("salesareasgroup", SalesGroups.values());

        model.addAttribute("salestypename", SalesTypeName.values());

        model.addAttribute("inventorylocationslist", inventoryLocationsRepository.findAll());

        model.addAttribute("taxgroupslist", taxGroupsRepository.findAll());

        model.addAttribute("glaccountslist", glAccountsRepository.findAll());

        model.addAttribute("customerbrancheslist", customerBranchesRepository.findAll());

        return "module/sales/settings/customer_add";

    }

    @RequestMapping("/save")
    public String save(Model model, @Valid Customers customers, BindingResult bindingResult,
            RedirectAttributes redirectAttrs) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("salespersonslist", salesPersonsRepository.findAll());

            model.addAttribute("salesareaslist", salesAreasRepository.findAll());

            model.addAttribute("creditstatuslist", creditStatusSetupRepository.findAll());

            model.addAttribute("salesareasgroup", SalesGroups.values());

            model.addAttribute("salestypename", SalesTypeName.values());

            model.addAttribute("inventorylocationslist", inventoryLocationsRepository.findAll());

            model.addAttribute("taxgroupslist", taxGroupsRepository.findAll());

            model.addAttribute("glaccountslist", glAccountsRepository.findAll());

            model.addAttribute("customerbrancheslist", customerBranchesRepository.findAll());

            return "module/sales/settings/customer_add";
        }
        
        customersRepository.save(customers);

        redirectAttrs.addFlashAttribute("success_messages", " Successfully Save.");

        return "redirect:/customers/index";
    }

}
