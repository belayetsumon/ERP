/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.branch.controller.transactions;

import com.itgarden.ERP.module.branch.model.transactions.BranchExpense;
import com.itgarden.ERP.module.branch.repository.settings.ExpenseTypeRepository;
import com.itgarden.ERP.module.branch.repositoy.transactions.BranchExpenseRepository;
import com.itgarden.ERP.module.branch.transactions.services.BranchExpenseService;
import com.itgarden.ERP.module.finance_banking.repository.settings.BankAccountsRepository;
import com.itgarden.ERP.module.inventory.model.settings.InventoryLocations;
import com.itgarden.ERP.module.inventory.repository.settings.InventoryLocationsRepository;
import com.itgarden.ERP.module.settings.model.company_setup.TransactionsType;
import com.itgarden.ERP.module.settings.repository.company_setup.TransactionsTypeRepository;
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
@RequestMapping("/branchexpense")
public class BranchExpenseController {

    @Autowired
    TransactionsTypeRepository transactionsTypeRepository;

    @Autowired
    BranchExpenseRepository branchExpenseRepository;

    @Autowired
    ExpenseTypeRepository expenseTypeRepository;

    @Autowired
    BankAccountsRepository bankAccountsRepository;

    @Autowired
    InventoryLocationsRepository inventoryLocationsRepository;
    

    @Autowired
    BranchExpenseService branchExpenseService;

    @RequestMapping(value = {"", "/", "/index"})
    public String index(Model model, BranchExpense branchExpense) {
               
        /// set refe 
        
        branchExpense.setExpenseReference(branchExpenseService.refarance());

        ///   set  transactions Type
        TransactionsType transactionsType = new TransactionsType();

        transactionsType = transactionsTypeRepository.findBySlug("branch-expense");

        model.addAttribute("bankList", bankAccountsRepository.findAll());

        model.addAttribute("expensetypelist", expenseTypeRepository.findAll());

        InventoryLocations inventoryLocations = inventoryLocationsRepository.findByLocationCode("jamuna-2");

        branchExpense.setInventoryLocations(inventoryLocations);

        return "module/branch/transactions/branchexpense";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid BranchExpense branchExpense, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {

            model.addAttribute("bankList", bankAccountsRepository.findAll());

            model.addAttribute("expensetypelist", expenseTypeRepository.findAll());

            InventoryLocations inventoryLocations = inventoryLocationsRepository.findByLocationCode("jamuna-2");

            branchExpense.setInventoryLocations(inventoryLocations);

            return "module/branch/transactions/branchexpense";
        }

        branchExpenseRepository.save(branchExpense);

        redirectAttrs.addFlashAttribute("success_messages ", "Sucessfully save.");

        return "redirect:/branchexpense/index";
    }

    @RequestMapping(value = {"/list"})
    public String list(Model model, BranchExpense branchExpense) {

        model.addAttribute("list", branchExpenseRepository.findAll());

        return "module/branch/report/branch_expense_list";
    }

    @RequestMapping("/edit/{id}")
    public String index(Model model, @PathVariable Long id, BranchExpense branchExpense) {
        model.addAttribute("expenseType", expenseTypeRepository.getOne(id));
        model.addAttribute("list", expenseTypeRepository.findAll());
        return "module/branch/transactions/branchexpense";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, BranchExpense branchExpense, RedirectAttributes redirectAttrs) {
        expenseTypeRepository.deleteById(id);
        return "redirect:/branchexpense/index";
    }
}