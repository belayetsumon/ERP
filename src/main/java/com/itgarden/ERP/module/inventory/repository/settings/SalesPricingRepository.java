/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.repository.settings;

import com.itgarden.ERP.module.inventory.model.enumvalue.SalesType;
import com.itgarden.ERP.module.inventory.model.settings.Items;
import com.itgarden.ERP.module.inventory.model.settings.SalesPricing;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface SalesPricingRepository extends JpaRepository<SalesPricing, Long> {

    List<SalesPricing> findByitem(Items item);

    SalesPricing findByItemAndSalesType(Items item, SalesType salesType);
}
