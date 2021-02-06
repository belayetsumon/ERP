/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.repository.settings;

import com.itgarden.ERP.module.inventory.model.settings.Items;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface ItemsRepository extends JpaRepository<Items, Long> {

    // single item for sale
    Items findByItemCodeAndNoSaleAndItemstatus(int itemcode, boolean noSale, boolean itemstatus);

    // list item for sale 
    List<Items> findByNoSaleAndItemstatus(boolean noSale, boolean itemstatus);

    List<Items> findByItemstatus(boolean itemstatus);

    Page<Items> findAll(Pageable pageable);

    Items findTopByOrderByIdDesc();
    
   Items findByItemCode(String itemCode);

}
