/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.controller.inquiries_reports;

import com.itgarden.ERP.module.inventory.repository.transactions.StockMovesRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/stokmoves")
public class StockMovesController {

    @Autowired
    StockMovesRepository stockMovesRepository;

    @RequestMapping(value = {"", "/", "/index"})
    public String index(Model model) {

        Pageable pageable = PageRequest.of(0, 2000, Sort.by("id").descending());

        model.addAttribute("list", stockMovesRepository.findAll(pageable));

        return "module/inventory/inquiriesandreports/stockmoves";
    }
}
