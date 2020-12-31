/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.repository.transactions;

import com.itgarden.ERP.module.sales.model.transactions.SalesQuotationItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface SalesQuotationItemRepository extends JpaRepository<SalesQuotationItem, Long> {

    
    
}
