/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.controller.transactions;

import com.itgarden.ERP.module.sales.model.transactions.SalesOrderEntry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/salesorderentry")
public class SalesOrderEntryController {

    @RequestMapping("/url")
    public String page(Model model) {
        model.addAttribute("attribute", "value");
        return "view.name";
    }

    @RequestMapping("/createorder")
    public String createorder(Model model,SalesOrderEntry salesOrderEntry) {
        model.addAttribute("attribute", "value");
       return "module/sales/transactions/create_order";
    }

}
