/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.settings.controller.company_setup;

import com.itgarden.ERP.module.finance_banking.repository.settings.CurrenciesRepository;
import com.itgarden.ERP.module.settings.model.company_setup.CompanySetup;
import com.itgarden.ERP.module.settings.repository.company_setup.CompanySetupRepository;
import com.itgarden.ERP.module.settings.repository.company_setup.FiscalYearsRepository;
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
@RequestMapping("/companysetup")
public class CompanySetupController {

    @Autowired
    CompanySetupRepository companySetupRepository;

    @Autowired
    FiscalYearsRepository fiscalYearsRepository;

    @Autowired
    CurrenciesRepository currenciesRepository;

    @RequestMapping("/index")
    public String index(Model model, CompanySetup companySetup) {

        if (companySetupRepository.findAll().size() == 0 || companySetupRepository.getOne(1L) == null) {

            companySetup.setId(1L);

            model.addAttribute("currencieslist", currenciesRepository.findAll());

            model.addAttribute("fiscalyearslist", fiscalYearsRepository.findAll());

            return "module/settings/company_setup/companysetup";

        } else {

            model.addAttribute("companySetup", companySetupRepository.getOne(1L));

            model.addAttribute("currencieslist", currenciesRepository.findAll());

            model.addAttribute("fiscalyearslist", fiscalYearsRepository.findAll());
            
            return "module/settings/company_setup/companysetup";

        }
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid CompanySetup companySetup, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {

            companySetup.setId(1L);
            model.addAttribute("currencieslist", currenciesRepository.findAll());

            model.addAttribute("fiscalyearslist", fiscalYearsRepository.findAll());

            return "module/settings/company_setup/companysetup";
        }

        companySetupRepository.save(companySetup);

        redirectAttrs.addFlashAttribute("success_messages ", "Sucessfully save.");

        return "redirect:/companysetup/index";
    }

}
