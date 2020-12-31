/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.controller.transactions;

import com.itgarden.ERP.module.finance_banking.repository.settings.BankAccountsRepository;
import com.itgarden.ERP.module.sales.model.transactions.CustomerPayments;
import com.itgarden.ERP.module.sales.model.transactions.SalesInvoice;
import com.itgarden.ERP.module.sales.repository.settings.CustomersRepository;
import com.itgarden.ERP.module.sales.repository.transactions.CustomerPaymentsRepository;
import com.itgarden.ERP.module.sales.transactions.services.CustomerPaymentsService;
import com.itgarden.ERP.module.settings.model.company_setup.TransactionsType;
import com.itgarden.ERP.module.settings.repository.company_setup.TransactionsTypeRepository;
import javax.servlet.http.HttpSession;
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
@RequestMapping("/customerpayments")
public class CustomerPaymentsController {

    @Autowired
    CustomerPaymentsRepository customerPaymentsRepository;

    @Autowired
    CustomerPaymentsService CustomerPaymentsService;

    @Autowired
    CustomersRepository customersRepository;

    @Autowired
    TransactionsTypeRepository transactionsTypeRepository;

    @Autowired
    BankAccountsRepository bankAccountsRepository;

    @RequestMapping(value = {"", "/", "/index"})
    public String index(Model model, CustomerPayments customerPayments) {

        model.addAttribute("customerlist", customersRepository.findAll());

        model.addAttribute("banklist", bankAccountsRepository.findAll());

        ///   set  transactions Type
        TransactionsType transactionsType = new TransactionsType();

        transactionsType = transactionsTypeRepository.findBySlug("customer-payment");
        customerPayments.setTransactionsType(transactionsType);

        model.addAttribute("attribute", "value");
        return "module/sales/transactions/customerpayments";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid CustomerPayments customerPayments, BindingResult bindingResult, RedirectAttributes redirectAttrs, HttpSession salesItemSession) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("customerlist", customersRepository.findAll());

            model.addAttribute("banklist", bankAccountsRepository.findAll());

            ///   set  transactions Type
            TransactionsType transactionsType = new TransactionsType();

            transactionsType = transactionsTypeRepository.findBySlug("customer-payment");
            customerPayments.setTransactionsType(transactionsType);
            return "module/sales/transactions/customerpayments";
        }

        customerPaymentsRepository.save(customerPayments);

        customerPayments = customerPaymentsRepository.findTopByOrderByCuPaymentIdDesc();

        long pyid = customerPayments.getCuPaymentId();

        redirectAttrs.addAttribute("id", pyid).addFlashAttribute("success_messages", "Successfully Save.");

        return "redirect:/customerpayments/view/{id}";
    }

    @GetMapping(value = "/view/{id}")
    public String view(Model model, @PathVariable Long id, CustomerPayments customerPayments) {

        model.addAttribute("pyid", id);

        return "module/sales/transactions/customerpayments_view";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, CustomerPayments customerPayments, RedirectAttributes redirectAttrs) {

        redirectAttrs.addFlashAttribute("success_messages", " Successfully Delete.");

        customerPaymentsRepository.deleteById(id);

        return "redirect:/customerpayments/index";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable Long id, CustomerPayments customerPayments) {

        model.addAttribute("customerPayments", customerPaymentsRepository.getOne(id));

        return "module/sales/transactions/customerPayments_details";
    }
    
    
    
    @GetMapping(value = "/pdf/{id}")
    public String createpdf(Model model, @PathVariable Long id, SalesInvoice salesInvoice) {

        model.addAttribute("inId", id);

        return "module/sales/transactions/salesqtview";
    }

}
