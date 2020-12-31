/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.controller.settings;

import com.itgarden.ERP.module.finance_banking.repository.settings.GlAccountsRepository;
import com.itgarden.ERP.module.inventory.repository.settings.InventoryLocationsRepository;
import com.itgarden.ERP.module.sales.dto.CustomerDTO;
import com.itgarden.ERP.module.sales.dto.CustomerSearchDto;
import com.itgarden.ERP.module.sales.model.enumvalue.SalesGroups;
import com.itgarden.ERP.module.sales.model.enumvalue.SalesTypeName;
import com.itgarden.ERP.module.sales.model.settings.Customers;
import com.itgarden.ERP.module.sales.repository.settings.CreditStatusSetupRepository;
import com.itgarden.ERP.module.sales.repository.settings.CustomerBranchesRepository;
import com.itgarden.ERP.module.sales.repository.settings.CustomersRepository;
import com.itgarden.ERP.module.sales.repository.settings.SalesAreasRepository;
import com.itgarden.ERP.module.sales.repository.settings.SalesPersonsRepository;
import com.itgarden.ERP.module.sales.settings.services.CustomerService;
import com.itgarden.ERP.module.settings.repository.company_setup.TaxGroupsRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @Autowired
    CustomerService customerService;

    @RequestMapping("/index")
    public String page(Model model) {

        model.addAttribute("customarlist", customersRepository.findAll());

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

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, Customers customers) {

        model.addAttribute("customers", customersRepository.getOne(id));

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

    @RequestMapping("/details/{id}")
    public String details(Model model, @PathVariable Long id, Customers customers) {

        model.addAttribute("customers", customersRepository.getOne(id));

//        model.addAttribute("salespersonslist", salesPersonsRepository.findAll());
//
//        model.addAttribute("salesareaslist", salesAreasRepository.findAll());
//
//        model.addAttribute("creditstatuslist", creditStatusSetupRepository.findAll());
//
//        model.addAttribute("salesareasgroup", SalesGroups.values());
//
//        model.addAttribute("salestypename", SalesTypeName.values());
//
//        model.addAttribute("inventorylocationslist", inventoryLocationsRepository.findAll());
//
//        model.addAttribute("taxgroupslist", taxGroupsRepository.findAll());
//
//        model.addAttribute("glaccountslist", glAccountsRepository.findAll());
//
//        model.addAttribute("customerbrancheslist", customerBranchesRepository.findAll());
        return "module/sales/settings/customer_details";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, Customers customers,
            RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("success_messages", " Successfully Delete.");
        customersRepository.deleteById(id);
        return "redirect:/customers/index";
    }

    ///  json API
    @GetMapping(value = "/customerbyid/{id}", produces = "application/json")
    @ResponseBody
    public CustomerDTO customerById(Model model, @PathVariable Long id) {
        
        CustomerDTO customerinfo = customerService.customerById(id);
              
        return customerinfo;
    }

    // customer search
    @GetMapping(value = "/customersearch", produces = "application/json")
    @ResponseBody
    public List<CustomerSearchDto> customerSearch(Model model, @RequestParam(value = "customer", required = false, defaultValue = "") String nameandcontactname) {

        List<CustomerSearchDto> customerList = customerService.customerSearch(nameandcontactname, nameandcontactname);

        return customerList;
    }

}
