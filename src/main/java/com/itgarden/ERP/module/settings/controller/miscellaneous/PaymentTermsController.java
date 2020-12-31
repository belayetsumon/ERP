/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.settings.controller.miscellaneous;

import com.itgarden.ERP.module.settings.model.miscellaneous.PaymentTerms;
import com.itgarden.ERP.module.settings.repository.miscellaneous.PaymentTermsRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("/paymentterms")
public class PaymentTermsController {

    @Autowired
    PaymentTermsRepository paymentTermsRepository;

    @RequestMapping("/index")
    public String page(Model model, PaymentTerms paymentTerms) {
        model.addAttribute("paymenttermslist", paymentTermsRepository.findAll());
        return "module/settings/miscellaneous/paymentterms";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid PaymentTerms paymentTerms, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("paymenttermslist", paymentTermsRepository.findAll());
            return "module/settings/miscellaneous/paymentterms";

        }
        paymentTermsRepository.save(paymentTerms);
        redirectAttrs.addFlashAttribute("success_messages ", "Sucessfully save.");
        return "redirect:/paymentterms/index";
    }

    @RequestMapping("/edit/{id}")
    public String index(Model model, @PathVariable Long id, PaymentTerms paymentTerms) {
        model.addAttribute("paymentTerms", paymentTermsRepository.getOne(id));
        model.addAttribute("paymenttermslist", paymentTermsRepository.findAll());
        return "module/settings/miscellaneous/paymentterms";
    }

    @GetMapping(value = "/delete/{id}")
    @Transactional
    public String delete(Model model, @PathVariable Long id, PaymentTerms paymentTerms, RedirectAttributes redirectAttrs) {
        paymentTermsRepository.deleteById(id);
        redirectAttrs.addFlashAttribute("success_messages ", "Sucessfully Delete.");
        return "redirect:/paymentterms/index";
    }

}
