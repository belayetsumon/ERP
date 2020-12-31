/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Md Belayet Hossain
 */
@Controller
@RequestMapping("/sales")
public class SalesController {

    @RequestMapping("/index")
    public String index(Model model) {
        return "module/sales/report/index";
    }

}
