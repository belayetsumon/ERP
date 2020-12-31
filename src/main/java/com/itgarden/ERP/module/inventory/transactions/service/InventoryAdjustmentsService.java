/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.transactions.service;

import com.itgarden.ERP.module.inventory.repository.transactions.InventoryAdjustmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class InventoryAdjustmentsService {
    
    @Autowired
    InventoryAdjustmentsRepository inventoryAdjustmentsRepository;
    
    
    
    
}
