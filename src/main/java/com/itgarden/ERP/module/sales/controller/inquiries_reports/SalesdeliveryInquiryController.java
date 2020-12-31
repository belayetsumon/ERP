/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.controller.inquiries_reports;

import com.itgarden.ERP.module.sales.model.transactions.SalesDelivery;
import com.itgarden.ERP.module.sales.transactions.services.SalesDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/salesdeliveryinquiry")

public class SalesdeliveryInquiryController {

    @Autowired
    SalesDeliveryService salesDeliveryService;

    @RequestMapping(value = {"", "/", "/index"})
    public String index(Model model) {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by("deliveryNo").descending());

        Page<SalesDelivery> alldelivery = salesDeliveryService.allDelivery(pageable);

        model.addAttribute("alldelivery", alldelivery);

        return "module/sales/inquiriesandreports/salesdeliveryinquiry";
    }
}
