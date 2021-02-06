/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.controller.inquiries_reports;

import com.itgarden.ERP.module.inventory.model.settings.InventoryLocations;
import com.itgarden.ERP.module.inventory.model.settings.Items;
import com.itgarden.ERP.module.inventory.model.transactions.StockMoves;
import com.itgarden.ERP.module.inventory.repository.settings.InventoryLocationsRepository;
import com.itgarden.ERP.module.inventory.repository.transactions.StockMovesRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/inventoryitemstatus")
public class InventoryItemStatusController {

    @Autowired
    StockMovesRepository stockMovesRepository;

    @Autowired
    InventoryLocationsRepository inventoryLocationsRepository;

    @RequestMapping(value = {"", "/", "/index"})
    public String index(Model model) {

        model.addAttribute("locationsList", inventoryLocationsRepository.findAll());

        List<StockMoves> stockMoves = stockMovesRepository.findAll();

        //    Map<StockMoves, BigDecimal> result ;
//        stockMoves.stream()
//    .collect(Collectors.toMap(StockMoves::getItems, StockMoves::getQty, BigDecimal::add));
        stockMoves.stream().collect(Collectors.groupingBy(StockMoves::getItems, Collectors.mapping(StockMoves::getQty, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        Map<Items, BigDecimal> lists = stockMoves.stream().collect(Collectors.groupingBy(StockMoves::getItems, Collectors.mapping(StockMoves::getQty, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        model.addAttribute("list", lists);

//        Pageable pageable = PageRequest.of(0, 2000, Sort.by("id").descending());
//
//        model.addAttribute("list", stockMovesRepository.findAll(pageable));
        return "module/inventory/inquiriesandreports/inventoryitemstatus";
    }

    @RequestMapping(value = {"/itembylocation"})
    public String itemByLocation(Model model, @RequestParam(name = "inventoryLocations", required = true) Long id) {

        model.addAttribute("locationsList", inventoryLocationsRepository.findAll());

        InventoryLocations inventoryLocations = inventoryLocationsRepository.getOne(id);

        model.addAttribute("location_name", inventoryLocations.getLocationName());

        List<StockMoves> stockMoves = stockMovesRepository.findByInventoryLocations(inventoryLocations);

        //    Map<StockMoves, BigDecimal> result ;
//        stockMoves.stream()
//    .collect(Collectors.toMap(StockMoves::getItems, StockMoves::getQty, BigDecimal::add));
        // stockMoves.stream().collect(Collectors.groupingBy(StockMoves::getItems,Collectors.mapping(StockMoves::getQty, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
        Map<Items, BigDecimal> lists = stockMoves.stream().collect(Collectors.groupingBy(StockMoves::getItems, Collectors.mapping(StockMoves::getQty, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        model.addAttribute("list", lists);

//        Pageable pageable = PageRequest.of(0, 2000, Sort.by("id").descending());
//
//        model.addAttribute("list", stockMovesRepository.findAll(pageable));
        return "module/inventory/inquiriesandreports/inventoryitemstatus_by_location";
    }

}
