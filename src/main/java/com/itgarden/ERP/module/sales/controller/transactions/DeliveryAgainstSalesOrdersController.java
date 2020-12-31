/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.controller.transactions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/deliveryagainstsalesorders")
public class DeliveryAgainstSalesOrdersController {
    
     @RequestMapping(value = {"", "/", "/index"})
    public String index(Model model) {
        model.addAttribute("attribute", "value");
        return "module/sales/transactions/deliveryagainstsalesorders";
    }
    
}
