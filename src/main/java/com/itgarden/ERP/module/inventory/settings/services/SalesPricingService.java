/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.settings.services;

import com.itgarden.ERP.module.inventory.model.enumvalue.SalesType;
import com.itgarden.ERP.module.inventory.model.settings.Items;
import com.itgarden.ERP.module.inventory.model.settings.SalesPricing;
import com.itgarden.ERP.module.inventory.repository.settings.SalesPricingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class SalesPricingService {

    @Autowired
    SalesPricingRepository salesPricingRepository;

    public List<SalesPricing> salesTypeFindByItemId(Items item) {

        List<SalesPricing> salesType = salesPricingRepository.findByitem(item);

        return salesType;
    }

    public double pricebyItemAndType(Items item, SalesType salesType) {

        SalesPricing salesPricing = salesPricingRepository.findByItemAndSalesType(item, salesType);

        if (salesPricing == null) {

            return 0.00;
        } else {

            return salesPricing.getPrice();

        }

    }

}
