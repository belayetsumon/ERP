/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.transactions.service;

import com.itgarden.ERP.module.inventory.model.transactions.InventoryAdjustments;
import com.itgarden.ERP.module.inventory.model.transactions.InventoryLocationTransfers;
import com.itgarden.ERP.module.inventory.repository.transactions.InventoryLocationTransfersRepository;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class InventoryLocationTransfersService {

    @Autowired
    InventoryLocationTransfersRepository inventoryLocationTransfersRepository;

    public String refarance() {

        InventoryLocationTransfers refe = inventoryLocationTransfersRepository.findTopByOrderByIdDesc();

        Long lastid;

        if (refe != null) {

            lastid = refe.getId() + 1;

        } else {

            lastid = 1l;
        }

        int year = Calendar.getInstance().get(Calendar.YEAR);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%05d", lastid));
        sb.append("/");
        sb.append(year);
        return sb.toString();
    }
    
    
   public boolean referCodeCheck(String iltReference) {

        InventoryLocationTransfers ref = inventoryLocationTransfersRepository.findByIltReference(iltReference);

        if (ref != null) {

            return true;

        } else {
            return false;
        }

    } 
    
    
    
    
}
