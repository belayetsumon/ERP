/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.transactions.service;

import com.itgarden.ERP.module.inventory.DTO.ItemStatusDTO;
import com.itgarden.ERP.module.inventory.model.enumvalue.SalesType;
import com.itgarden.ERP.module.inventory.model.settings.InventoryLocations;
import com.itgarden.ERP.module.inventory.model.settings.Items;
import com.itgarden.ERP.module.inventory.model.transactions.StockMoves;
import com.itgarden.ERP.module.inventory.repository.settings.ItemsRepository;
import com.itgarden.ERP.module.inventory.repository.transactions.StockMovesRepository;
import com.itgarden.ERP.module.inventory.settings.services.SalesPricingService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class StockMoveService {

    @Autowired
    StockMovesRepository stockMovesRepository;

    @Autowired

    SalesPricingService salesPricingService;

    @Autowired
    ItemsRepository itemsRepository;

    public List<ItemStatusDTO> globalTemStatus() {

        List<StockMoves> stockMoves = stockMovesRepository.findAll();

        //    Map<StockMoves, BigDecimal> result ;
//        stockMoves.stream()
//    .collect(Collectors.toMap(StockMoves::getItems, StockMoves::getQty, BigDecimal::add));
        // stockMoves.stream().collect(Collectors.groupingBy(StockMoves::getItems, Collectors.mapping(StockMoves::getQty, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
        Map<Items, BigDecimal> list = stockMoves.stream().collect(Collectors.groupingBy(StockMoves::getItems, Collectors.mapping(StockMoves::getQty, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        List<ItemStatusDTO> itemStatusList = new ArrayList<>();

        for (Map.Entry<Items, BigDecimal> entry : list.entrySet()) {

            ItemStatusDTO itemStatusDto = new ItemStatusDTO();

            itemStatusDto.setItemCode(entry.getKey().getItemCode());

            itemStatusDto.setName(entry.getKey().getName());

            itemStatusDto.setQuantity(entry.getValue());

            Items item = itemsRepository.getOne(entry.getKey().getId());

            double rprice = salesPricingService.pricebyItemAndType(item, SalesType.Retail);

            //      set retail price
            if (item.getSalesPrice() != null) {

                itemStatusDto.setRetailPrice(new BigDecimal(rprice));
            } else {
                itemStatusDto.setRetailPrice(BigDecimal.ZERO);
            }

            BigDecimal total = entry.getValue().multiply(new BigDecimal(rprice));

            itemStatusDto.setItemTotal(total);

            itemStatusList.add(itemStatusDto);
        }

        return itemStatusList;

    }

    public List<ItemStatusDTO> byLocationItemStatus(InventoryLocations inventoryLocations) {

        List<StockMoves> stockMoves = stockMovesRepository.findByInventoryLocations(inventoryLocations);

        //    Map<StockMoves, BigDecimal> result ;
//        stockMoves.stream()
//    .collect(Collectors.toMap(StockMoves::getItems, StockMoves::getQty, BigDecimal::add));
        stockMoves.stream().collect(Collectors.groupingBy(StockMoves::getItems, Collectors.mapping(StockMoves::getQty, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        Map<Items, BigDecimal> list = stockMoves.stream().collect(Collectors.groupingBy(StockMoves::getItems, Collectors.mapping(StockMoves::getQty, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        List<ItemStatusDTO> itemStatusList = new ArrayList<>();

        for (Map.Entry<Items, BigDecimal> entry : list.entrySet()) {

            ItemStatusDTO itemStatusDto = new ItemStatusDTO();

            itemStatusDto.setItemCode(entry.getKey().getItemCode());

            itemStatusDto.setName(entry.getKey().getName());

            itemStatusDto.setQuantity(entry.getValue());

            Items item = itemsRepository.getOne(entry.getKey().getId());

            double rprice = salesPricingService.pricebyItemAndType(item, SalesType.Retail);

            //      set retail price
            if (item.getSalesPrice() != null) {

                itemStatusDto.setRetailPrice(new BigDecimal(rprice));
            } else {
                itemStatusDto.setRetailPrice(BigDecimal.ZERO);
            }

            BigDecimal total = entry.getValue().multiply(new BigDecimal(rprice));

            itemStatusDto.setItemTotal(total);

            itemStatusList.add(itemStatusDto);
        }

        return itemStatusList;

    }

    public BigDecimal quantityStatusByLocationAndItem(InventoryLocations inventoryLocations, Items item) {

        List<StockMoves> stockMoves = stockMovesRepository.findByInventoryLocationsAndItems(inventoryLocations, item);

        BigDecimal quantity = new BigDecimal(0);

        if (stockMoves != null) {

            quantity = stockMoves.stream().collect(Collectors.mapping(StockMoves::getQty, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)));
        } else {

            quantity = new BigDecimal(0);
        }
        return quantity;

    }
}
