/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.settings.services;

import com.itgarden.ERP.module.inventory.DTO.ItemDTO;
import com.itgarden.ERP.module.inventory.model.enumvalue.SalesType;
import com.itgarden.ERP.module.inventory.model.settings.Items;
import com.itgarden.ERP.module.inventory.repository.settings.ItemsRepository;
import static java.lang.Integer.parseInt;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class ItemService {

    @Autowired
    ItemsRepository itemsRepository;

    @Autowired

    SalesPricingService salesPricingService;

    // item active and saleable
    public ItemDTO itemByItemCodeAndactiveSales(int itemCode, boolean noSale, boolean itemstatus) {
        ItemDTO itemdto = new ItemDTO();

        Items item = itemsRepository.findByItemCodeAndNoSaleAndItemstatus(itemCode, noSale, itemstatus);
        itemdto.setId(item.getId());
        itemdto.setItemCode(parseInt(item.getItemCode()));
        itemdto.setName(item.getName());
        itemdto.setDescription(item.getDescription());
        return itemdto;
    }

    // item active and saleable
    public ItemDTO itemById(Long id) {

        ItemDTO itemdto = new ItemDTO();

        Items item = itemsRepository.getOne(id);

        itemdto.setId(item.getId());

        itemdto.setItemCode(parseInt(item.getItemCode()));

        itemdto.setName(item.getName());

        itemdto.setDescription(item.getDescription());

        itemdto.setDiscount(item.getDiscount());
        itemdto.setItemTaxTypeName("5");

        itemdto.setUnitsofMeasureName(item.getUnitsofMeasure());

//        set retail price
        if (item.getSalesPrice() != null) {
            itemdto.setRetailPrice(salesPricingService.pricebyItemAndType(item, SalesType.Retail));
        } else {
            itemdto.setRetailPrice(0.00);

        }

//        set Whole Sale Price
        if (item.getSalesPrice() != null) {
            itemdto.setWholesalePrice(salesPricingService.pricebyItemAndType(item, SalesType.Wholesale));
        } else {
            itemdto.setWholesalePrice(0.00);
        }

        //  set purchase price
        if (item.getPurchasingPricing() != null) {
            itemdto.setPurchasePrice(item.getPurchasingPricing().getPrice());
        } else {
            itemdto.setWholesalePrice(0.00);
        }

        if (item.getStandardCosts() != null) {
            itemdto.setPurchasePrice(item.getStandardCosts().getPrice());
        } else {
            itemdto.setWholesalePrice(0.00);
        }

        //  set standerdcoast 
//   return DTO     
        return itemdto;
    }

    // All Active Item
    public List<Items> allActiveItemList(boolean itemstatus) {

        List<Items> itemList = itemsRepository.findByItemstatus(itemstatus);

        return itemList;
    }

    // All Active Item and salable 
    public List<Items> allActiveAndSalableItemList(boolean noSale, boolean itemstatus) {

        List<Items> itemList = itemsRepository.findByNoSaleAndItemstatus(noSale, itemstatus);

        return itemList;
    }

    // All item 
    public Page<Items> allItems() {

        Pageable pageable = new PageRequest(0, 50, Sort.Direction.DESC, "id");

        Page<Items> itemList = itemsRepository.findAll(pageable);

        return itemList;

    }

    // last inserted id
    public Items itemLastInsertedId() {

        Items item = itemsRepository.findTopByOrderByIdDesc();

        return item;

    }
    
    
   // Product Refarance Code  generate 
    
    
    // last inserted id
    public String itemCode() {

        Items item = itemsRepository.findTopByOrderByIdDesc();
        
        Long lastid = item.getId()+1;
        
       // System.out.println(String.format("%06d", lastid));

        return String.format("%06d", lastid);
         

    }
    
    
    
    

}
