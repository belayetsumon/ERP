/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.settings.controller.company_setup;

import com.itgarden.ERP.module.finance_banking.repository.settings.GlAccountsRepository;
import com.itgarden.ERP.module.settings.model.company_setup.SystemGeneralGlSetup;
import com.itgarden.ERP.module.settings.model.enumvalue.AccountsType;
import com.itgarden.ERP.module.settings.model.enumvalue.DepreciationPeriod;
import com.itgarden.ERP.module.settings.model.enumvalue.InvoiceIdentification;
import com.itgarden.ERP.module.settings.model.enumvalue.TaxAlgorithm;
import com.itgarden.ERP.module.settings.repository.company_setup.SystemGeneralGlSetupRepository;
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
@RequestMapping("/systemandgeneralglsetup")
public class SystemandGeneralGlSetupController {

    @Autowired
    SystemGeneralGlSetupRepository systemGeneralGlSetupRepository;

    @Autowired
    GlAccountsRepository glAccountsRepository;

    @RequestMapping("/index")
    public String index(Model model, SystemGeneralGlSetup systemGeneralGlSetup) {

        if (systemGeneralGlSetupRepository.findAll().size() == 0 || systemGeneralGlSetupRepository.getOne(1L) == null) {
            systemGeneralGlSetup.setId(1L);
            model.addAttribute("glaccounts", glAccountsRepository.findAll());

            model.addAttribute("accountstypelist", AccountsType.values());

            model.addAttribute("taxalgorithmlist", TaxAlgorithm.values());

            model.addAttribute("invoiceidentificationlist", InvoiceIdentification.values());

            model.addAttribute("depreciationperiodlist", DepreciationPeriod.values());

            return "module/settings/company_setup/systemandgeneralglsetup";

        } else {

            model.addAttribute("systemGeneralGlSetup", systemGeneralGlSetupRepository.getOne(1L));

            model.addAttribute("glaccounts", glAccountsRepository.findAll());

            model.addAttribute("accountstypelist", AccountsType.values());

            model.addAttribute("taxalgorithmlist", TaxAlgorithm.values());

            model.addAttribute("invoiceidentificationlist", InvoiceIdentification.values());

            model.addAttribute("depreciationperiodlist", DepreciationPeriod.values());

            return "module/settings/company_setup/systemandgeneralglsetup";
        }

    }

    @RequestMapping("/save")
    public String save(Model model, @Valid SystemGeneralGlSetup systemGeneralGlSetup, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            systemGeneralGlSetup.setId(1L);
            model.addAttribute("glaccounts", glAccountsRepository.findAll());
            model.addAttribute("accountstypelist", AccountsType.values());
            model.addAttribute("taxalgorithmlist", TaxAlgorithm.values());
            model.addAttribute("invoiceidentificationlist", InvoiceIdentification.values());
            model.addAttribute("depreciationperiodlist", DepreciationPeriod.values());
            return "module/settings/company_setup/systemandgeneralglsetup";
        }

        systemGeneralGlSetupRepository.save(systemGeneralGlSetup);

       redirectAttrs.addFlashAttribute("success_messages", " Successfully Save.");

        return "redirect:/systemandgeneralglsetup/index";
    }

}
